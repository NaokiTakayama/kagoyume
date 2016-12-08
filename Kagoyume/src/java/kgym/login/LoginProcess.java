package kgym.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kgym.UserData;
import kgym.UserDataDAO;
import kgym.UserDataDTO;

/**
 *
 * @author Naoki
 */
public class LoginProcess extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try{
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            
            HttpSession hs = request.getSession();
            
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                    throw new Exception("不正なアクセスです");
            }
            
            //送信データを、UserDataインスタンスに格納
            UserData login_ud = new UserData();
            login_ud.setName(request.getParameter("name"));
            login_ud.setPassword(request.getParameter("password"));
            
            //UserDataDTOインスタンスに上のUserDataインスタンスを格納
            UserDataDTO loginData = new UserDataDTO();
            login_ud.UD2DTOMapping(loginData);
            
            //検索処理をし、UserDataDTOインスタンスに代入
            UserDataDTO search_login = UserDataDAO.getInstance().login(loginData);
            
            /*
            * 　search_loginがきちんと得られたかどうかで場合分け
            *　 取得出来ていなければ、ログインページに戻る
            */
            if(search_login == null){
                request.setAttribute("login_not","REINPUT");
                request.getRequestDispatcher("/login/login.jsp").forward(request, response); 
            }else{
                //ログインデータをセッションに格納
                hs.setAttribute("loginData",search_login);
                
                /*
                * 　ログイン前のページに戻るため、セッションからbackPageを取得。
                * 　その後、セッションを削除
                */
                String backPage = (String)hs.getAttribute("backPage");
                if(!backPage.equals("Add")){
                    backPage = "/" + backPage;
                }
                hs.removeAttribute("backPage");
                request.getRequestDispatcher(backPage).forward(request, response); 
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
