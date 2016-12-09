<%@page import="kgym.UserData"
        import="kgym.kgymHelper"%>
<%
    UserData regist_ud = (UserData)session.getAttribute("regist_ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録完了</title>
    </head>
    <body>
        <h1>登録完了画面</h1>
        <p>登録が完了しました</p>
        <ul>
            <li><strong>名前:</strong><%= regist_ud.getName()%></li><br>
            <li><strong>E-mail:</strong><%= regist_ud.getMail()%></li><br>
            <li><strong>県名:</strong><%= regist_ud.getPrefecture()%></li><br>
            <li><strong>市町村名:</strong><%= regist_ud.getCity()%></li><br>
            <li><strong>番地:</strong><%= regist_ud.getStreetNumber()%></li><br>
            <li><strong>建物など:</strong><%= regist_ud.getBuilding()%></li><br><br>
        </ul>
        <p>以上の内容で登録しました</p>
        <br>
        <%=kgymHelper.getInstance().home()%>
        <br><br><br>
        <a href="registration.jsp">登録画面に戻る</a>
        <br><br>
        <a href="<%= session.getAttribute("backPage")%>">元のページに戻る</a>
        
    </body>
</html>
