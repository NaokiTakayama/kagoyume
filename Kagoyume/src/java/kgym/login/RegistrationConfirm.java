package kgym.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kgym.UserData;

/**
 *
 * @author Naoki
 */
public class RegistrationConfirm extends HttpServlet {

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
            
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                    throw new Exception("不正なアクセスです");
            }         
                      
            //送信データを、UserDataインスタンスに格納
            UserData regist_ud = new UserData();
            regist_ud.setName(request.getParameter("name"));
            regist_ud.setPassword(request.getParameter("password"));
            regist_ud.setMail(request.getParameter("mail"));
            regist_ud.setPrefecture(request.getParameter("prefecture"));
            regist_ud.setCity(request.getParameter("city"));
            regist_ud.setStreetNumber(request.getParameter("street_number"));
            regist_ud.setBuilding(request.getParameter("building"));
            
            hs.setAttribute("regist_ud", regist_ud);
            
            /*
            * 　記入欄に空欄があるかどうかを、chkpropertiesメソッドで判定して、場合分け
            */
            if(!(regist_ud.chkproperties().isEmpty())){
                request.setAttribute("empty", "REINPUT");
                request.getRequestDispatcher("/login/registrationconfirm.jsp").forward(request, response);
            }
            
            //passwordが条件に合致するか判定
            if(!regist_ud.getPassword().matches("[a-zA-Z]\\w{5,}")){
                request.setAttribute("notMatch", "REINPUT");
                request.getRequestDispatcher("/login/registration.jsp").forward(request, response);
            } 
            
            //２種類のパスワードが合致するか
            String password_2 = (String)request.getParameter("password_2");
            if(!password_2.equals(regist_ud.getPassword())){
                request.setAttribute("notMatch_2", "REINPUT");
                request.getRequestDispatcher("/login/registration.jsp").forward(request, response);
            }
            
            request.getRequestDispatcher("/login/registrationconfirm.jsp").forward(request, response);
            
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
