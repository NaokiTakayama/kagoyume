package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kgym.UserDataDTO;
import kgym.kgymHelper;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');

    //ログイン情報の有無を判定
    boolean login_chk = kgymHelper.getInstance().check_null(session.getAttribute("loginData"));
    
    //検索結果の有無で場合分け
    boolean empty_chk = kgymHelper.getInstance().check_null(request.getAttribute("empty"));

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("            <title>トップ画面</title>\n");
      out.write("    </head>\n");
      out.write("        <body>\n");
      out.write("            <h1>「かごいっぱいのゆめ」にようこそ！</h1><br>\n");
      out.write("            ");
if(login_chk){
      out.write("\n");
      out.write("                <h2>--ようこそ<a href=\"MyData?backPage=top&ac=");
      out.print( session.getAttribute("ac"));
      out.write("\">\n");
      out.write("                        ");
      out.print( ((UserDataDTO)session.getAttribute("loginData")).getName());
      out.write("</a>さん！--</h2>\n");
      out.write("            ");
;}
      out.write("    \n");
      out.write("            <h3>ここでは、商品の検索を行います。</h3><br>\n");
      out.write("            ");
if(empty_chk){
      out.write("\n");
      out.write("                <font color=\"#ff0000\"><strong>該当商品は存在しません</strong></font>\n");
      out.write("            ");
;}
      out.write("\n");
      out.write("            \n");
      out.write("                <p>キーワードを入力して下さい</p>\n");
      out.write("                \n");
      out.write("            <form action=\"Search\" method=\"GET\">\n");
      out.write("                キーワード検索:\n");
      out.write("                <input type=\"text\" name=\"keyword\" value=\"\">\n");
      out.write("                <br><br>\n");
      out.write("\n");
      out.write("                <input type=\"hidden\" name=\"ac\" value=\"");
      out.print( session.getAttribute("ac"));
      out.write("\">\n");
      out.write("                <input type=\"submit\" name=\"btnSubmit\" value=\"検索\">\n");
      out.write("            </form>\n");
      out.write("                <br><br>\n");
      out.write("                \n");
      out.write("                <p>本日は「かごいっぱいのゆめ」にお越しいただきありがとうございます。</p>\n");
      out.write("                <p>このサイトでは、疑似的なネットショッピングをお楽しみいただけます。</p><br>\n");
      out.write("                \n");
      out.write("                <p>まずは、上の検索フォームに購入したい商品のキーワードを入力して下さい。</p>\n");
      out.write("                <p>関連商品が一覧となって表示されます。</p><br>\n");
      out.write("                \n");
      out.write("                <p>また、本サイトに登録していただけますと、実際に商品を購入（実際には購入できませんが・・・）<br>\n");
      out.write("                することが出来ます。</p>\n");
      out.write("                <p>ぜひ登録してお楽しみください。</p><br><br>\n");
      out.write("                ");
if(login_chk){
      out.write("\n");
      out.write("                    <a href=\"Cart?backPage=top&ac=");
      out.print( session.getAttribute("ac"));
      out.write("\">カートに移動</a><br><br>\n");
      out.write("                ");
;}else{
      out.write("\n");
      out.write("                    <a href=\"Login?backPage=top&ac=");
      out.print( session.getAttribute("ac"));
      out.write("\">ログイン</a><br><br>\n");
      out.write("                    <a href=\"Registration?backPage=top&ac=");
      out.print( session.getAttribute("ac"));
      out.write("\">新規登録</a>\n");
      out.write("                ");
;}
      out.write("\n");
      out.write("                ");
if(login_chk){
      out.write("\n");
      out.write("                <a href=\"Login?ac=");
      out.print( session.getAttribute("ac"));
      out.write("\">ログアウト</a><br><br>\n");
      out.write("                ");
;}
      out.write("\n");
      out.write("        </body>\n");
      out.write("    </html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
