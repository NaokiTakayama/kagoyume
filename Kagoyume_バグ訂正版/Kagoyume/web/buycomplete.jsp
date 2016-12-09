<%@page import="kgym.UserDataDTO"
        import="kgym.kgymHelper"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ご購入ありがとうございました</title>
    </head>
    <body>
        <h1>ご購入ありがとうございました</h1>
        <h2>--ようこそ<a href="MyData?backPage=buycomplete&ac=<%= session.getAttribute("ac")%>">
                        <%= ((UserDataDTO)session.getAttribute("loginData")).getName()%></a>さん！--</h2>
        <br>
        <p>ぜひまた、ご利用ください</p>
        <br>
        <a href="cart.jsp">カートに戻る</a><br>
        <br>
        <%=kg.home()%>
        <br><br>
            <a href="Login?ac=<%= session.getAttribute("ac")%>">ログアウト</a><br>
    </body>
</html>
