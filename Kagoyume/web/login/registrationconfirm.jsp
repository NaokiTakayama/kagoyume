<%@page import="javax.servlet.http.HttpSession"
        import="kgym.kgymHelper"
        import="kgym.UserData" %>
<%
    kgymHelper kg = kgymHelper.getInstance();
    UserData regist_ud = (UserData)session.getAttribute("regist_ud");
    
    //空欄の判定
    boolean empty_chk = kg.check_null(request.getAttribute("empty"));
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録確認</title>
    </head>
    <body>
        <h1>登録確認画面</h1>
        <%if(empty_chk){%>
            <%= kg.chkinput(regist_ud.chkproperties())%><br>
        <%;}else{%>
            以下の内容で登録しますか？<br>
            <ul>
                <li><strong>名前:</strong><%= regist_ud.getName()%></li><br>
                <li><strong>E-mail:</strong><%= regist_ud.getMail()%></li><br>
                <li><strong>県名:</strong><%= regist_ud.getPrefecture()%></li><br>
                <li><strong>市町村名:</strong><%= regist_ud.getCity()%></li><br>
                <li><strong>番地:</strong><%= regist_ud.getStreetNumber()%></li><br>
                <li><strong>建物など:</strong><%= regist_ud.getBuilding()%></li><br><br>
            </ul>
            <form action="RegistrationComplete" method="POST">
              <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
              <input type="submit" name="YES" value="登録する"style="width:110px">
            </form><br>
        <%;}%>
          <a href="login/registration.jsp?mode=REINPUT&ac=<%= session.getAttribute("ac")%>">新規登録画面に戻る</a>
          <br><br>
          <%=kg.home()%>
    </body>
</html>
