
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
public class Item extends HttpServlet {

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
            
            //Productインスタンス生成
            Product itemProduct = new Product();

            //search.jspからアクセスした場合
            if(kgymHelper.getInstance().check_null(request.getParameter("productNumber"))){
                
                //表の上から何番目かを取得
                int productNumber = Integer.parseInt(request.getParameter("productNumber"));
                
                //searchProductからproductNumber番目のProductインスタンスを取得
                ArrayList<Product> searchProduct = (ArrayList<Product>) hs.getAttribute("searchProduct");
                itemProduct = searchProduct.get(productNumber);
                
                //search.jspからの情報であると送る
                request.setAttribute("fromSearch", "ac1");
            }

            //cart.jspからアクセスした場合
            if(kgymHelper.getInstance().check_null(request.getParameter("cartProductNumber"))){
                
                //表の上から何番目かを取得
                int cartProductNumber = Integer.parseInt(request.getParameter("cartProductNumber"));
                
                //searchProductからproductNumber番目のProductインスタンスを取得
                ArrayList<Product> cartProductList = (ArrayList<Product>) hs.getAttribute("cartProductList");
                itemProduct = cartProductList.get(cartProductNumber);
                
                //cart.jspからの情報であると送る
                request.setAttribute("fromCart", "ac2");
            }

            //myhistory.jspからアクセスした場合
            if(kgymHelper.getInstance().check_null(request.getParameter("recordProductNumber"))){
                
                //表の上から何番目かを取得
                int recordProductNumber = Integer.parseInt(request.getParameter("recordProductNumber"));
                
                //searchProductからproductNumber番目のProductインスタンスを取得
                ArrayList<Product> recordProduct = (ArrayList<Product>) hs.getAttribute("recordProduct");
                itemProduct = recordProduct.get(recordProductNumber);
                
                //myhistory.jspからの情報であると送る
                request.setAttribute("fromHistory", "ac3");
            }
            
                        
            //アイテムをセッションに保存
            hs.setAttribute("itemProduct", itemProduct);
            request.getRequestDispatcher("/item.jsp").forward(request, response); 
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
