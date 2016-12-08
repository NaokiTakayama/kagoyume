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
        名前:<%= update_ud.getName()%><br>
        パスワード:<%= update_ud.getPassword()%><br>
        E-mail:<%= update_ud.getMail()%><br>
        県名:<%= update_ud.getPrefecture()%><br>
        市町村名:<%= update_ud.getCity()%><br>
        番地:<%= update_ud.getStreetNumber()%><br>
        建物など:<%= update_ud.getBuilding()%><br><br>
        <a href="mydata/myupdate.jsp?ac=<%= session.getAttribute("ac")%>">変更画面に移動</a><br><br>
        <a href="mydata/mydata.jsp?ac=<%= session.getAttribute("ac")%>">マイページに移動</a><br><br>
        <a href="Cart?backPage=mydata/myupdateresult&ac=<%= session.getAttribute("ac")%>">カートに移動</a><br>
        <%=kgymHelper.getInstance().home()%>
    </body>
</html>
