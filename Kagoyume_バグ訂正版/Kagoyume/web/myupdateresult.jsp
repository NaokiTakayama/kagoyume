<%@page import="kgym.UserDataDTO"
        import="javax.servlet.http.HttpSession"
        import="kgym.kgymHelper"
        import="kgym.UserData" %>
<%
    UserData update_ud = (UserData)session.getAttribute("update");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>変更完了画面</title>
    </head>
    <body>
        <h1>変更完了画面</h1>
        <h2>--ようこそ<%= ((UserDataDTO)session.getAttribute("loginData")).getName()%>さん！--</h2>
        
        以下の内容に変更しました<br>
        <ul>
            <li><strong>名前:</strong><%= update_ud.getName()%></li><br>
            <li><strong>パスワード:</strong><%= update_ud.getPassword()%></li><br>
            <li><strong>E-mail:</strong><%= update_ud.getMail()%></li><br>
            <li><strong>県名:</strong><%= update_ud.getPrefecture()%></li><br>
            <li><strong>市町村名:</strong><%= update_ud.getCity()%></li><br>
            <li><strong>番地:</strong><%= update_ud.getStreetNumber()%></li><br>
            <li><strong>建物など:</strong><%= update_ud.getBuilding()%></li><br><br>
        </ul>
        <a href="myupdate.jsp?ac=<%= session.getAttribute("ac")%>">変更画面に移動</a><br><br>
        <a href="mydata.jsp?ac=<%= session.getAttribute("ac")%>">マイページに移動</a><br><br>
        <a href="Cart?backPage=myupdateresult&ac=<%= session.getAttribute("ac")%>">カートに移動</a><br><br>
        <%=kgymHelper.getInstance().home()%>
    </body>
</html>
