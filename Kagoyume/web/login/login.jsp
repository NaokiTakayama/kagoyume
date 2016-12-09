<%@page import="kgym.UserDataDTO"
        import="kgym.kgymHelper"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
    boolean login_not = kg.check_null(request.getAttribute("login_not"));
    
    String session_chk = kg.session_chk((String)session.getAttribute("backPage"));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン画面</title>
    </head>
    <body>
        <h1>ログイン画面</h1>
        <%if(login_not){%>
            <font color="#ff0000"><strong>ログインできません</strong></font>
        <%;}%>
        <form action="LoginProcess" method="POST">
            <ul>    
                <li><strong>ユーザ名</strong> &nbsp;：<input type="text" name="name" value=""><br><br></li>
                <li><strong>パスワード</strong> &nbsp;（半角）：<input type="password" name="password" value=""><br><br></li>
                <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
                <input type="submit" value="ログイン">
            </ul>
        </form>
                <br>
        &emsp;&emsp;<a href="Registration?backPage=top&ac=<%= session.getAttribute("ac")%>">新規登録</a><br><br>
        &emsp;&emsp;<a href="<%= session_chk%>">戻る</a><br><br>
        &emsp;&emsp;<%=kg.home()%>
    </body>
</html>
