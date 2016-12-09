
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
public class BuyComplete extends HttpServlet {

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
            
            //発送方法を取得し、指定されていなければ元のページに戻る
            String type_str = request.getParameter("type");
            if(type_str == null || type_str.length()==0){
                request.setAttribute("type","REINPUT");
                request.getRequestDispatcher("/buyconfirm.jsp").forward(request, response);
            }
            int type = Integer.parseInt(type_str);
                    
            //購入商品のリストをセッションから取得した後、セッション内から消去
            ArrayList<Product> buyList = (ArrayList<Product>)hs.getAttribute("buyList");
            hs.removeAttribute("buyList");
        
            //ログインデータの取得
            UserDataDTO loginData = (UserDataDTO)hs.getAttribute("loginData");
            
            //userIDの取得
            int userID = loginData.getUserID();
            
            //buyListのサイズだけ、以下の作業を繰り返す
            for(Product pro:buyList){
                
                //BuyDataインスタンスに情報を格納
                BuyData bd = new BuyData();
                bd.setUserID(userID);
                bd.setItemCode(pro.getProductID());
                bd.setType(type);
                
                //BuyDataDTOインスタンスに情報を格納
                BuyDataDTO bdd = new BuyDataDTO();
                bd.BD2DTOMapping(bdd);
                
                //データベース(buy_t)に情報を挿入する
                BuyDataDAO.getInstance().insert_buy(bdd);
            }
            
            //データベース(user_t)の購入合計金額を更新する
                int total = kgymHelper.getInstance().totalOfCart(buyList);
                loginData.totalSum(total);
                UserDataDAO.getInstance().totalUpdate(loginData);
                
            //カート内の商品を削除    
                hs.removeAttribute("cartProductList");
            
            request.getRequestDispatcher("/buycomplete.jsp").forward(request, response); 
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
