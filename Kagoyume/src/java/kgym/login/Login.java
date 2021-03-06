package kgym.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kgym.kgymHelper;

/**
 *
 * @author Naoki
 */
public class Login extends HttpServlet {

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
        try{
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
            
            //ログイン情報を持っていた場合は、ログアウト
            if(kgymHelper.getInstance().check_null(hs.getAttribute("loginData"))){
                
                //セッションを全て削除
                hs.invalidate();
            
                request.getRequestDispatcher("/login/logout.jsp").forward(request, response); 
            }
            
            /*
            * 　ログイン後に元のページに戻るために、前のページ情報を送ってもらい、
            *　セッションに格納する
            */
            String backPage = (String)request.getParameter("backPage") + ".jsp";
            hs.setAttribute("backPage", backPage);
            
            request.getRequestDispatcher("/login/login.jsp").forward(request, response); 
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
