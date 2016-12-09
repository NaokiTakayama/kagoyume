package kgym;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Naoki
 */
public class BuyConfirm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            
            HttpSession hs = request.getSession();
            
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            ArrayList<Product> cartProductList = (ArrayList<Product>) hs.getAttribute("cartProductList");
            
            //すべて購入がチェックされたか場合分け
            if(kgymHelper.getInstance().check_null(request.getParameter("cartNumber_buy_all"))){
                hs.setAttribute("buyList", cartProductList);
                request.getRequestDispatcher("/buyconfirm.jsp").forward(request, response);
            }
            
            //ボタンがチェックされているか確認
            if(!kgymHelper.getInstance().check_null(request.getParameterValues("cartNumber_buy"))){
                request.setAttribute("noCheck", "nocheck");
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }else{
            
            ArrayList<Product> buyList = new ArrayList<>();
            //購入したい番号を取得
            String cartNumber[] = request.getParameterValues("cartNumber_buy");
            for(String c:cartNumber){
                int cartNumbers = Integer.parseInt(c);
                buyList.add(cartProductList.get(cartNumbers));
            }
            
            hs.setAttribute("buyList", buyList);
            
            request.getRequestDispatcher("/buyconfirm.jsp").forward(request, response); 
            }
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセス
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
