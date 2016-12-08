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
public class Search extends HttpServlet {

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
            
            /*
            * 　top.jspからアクセスチェック用パラメータ"ac"を持たずに
            *　アクセスする可能性があるため、セッションに"ac"の値が格納
            *　されているか否かで場合分け
            */
            if(hs.getAttribute("ac") == null){
                hs.setAttribute("ac", (int) (Math.random() * 1000));
            }else{
                String accesschk = request.getParameter("ac");
                if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                    throw new Exception("不正なアクセスです");
                }
            }
            
            //検索キーワードを取得
            String keyword = new String(request.getParameter("keyword").getBytes("ISO_8859_1"), "UTF-8");
            if(keyword == null || keyword.length() == 0){
                request.getRequestDispatcher("/top.jsp").forward(request, response); 
            }
            
            //検索キーワードを使って、商品を取得
            ArrayList<Product> searchProduct = YahooAPI.getInstance().searchForBuy(keyword);
            
            //商品が存在しなければ、top.jspに戻す
            if(searchProduct == null){
                request.setAttribute("empty1", "REINPUT");
                request.getRequestDispatcher("/top.jsp").forward(request, response); 
            }
            
            //取得した商品がnullの場合は、top.jspに戻す
            if(searchProduct.get(0).getName() == null){
                request.setAttribute("empty", "REINPUT");
                request.getRequestDispatcher("/top.jsp").forward(request, response); 
            }
            
            hs.setAttribute("searchProduct", searchProduct);
            request.getRequestDispatcher("/search.jsp").forward(request, response); 
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
