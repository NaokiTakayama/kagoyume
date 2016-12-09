<%@page import="kgym.UserDataDTO"
        import="javax.servlet.http.HttpSession"
        import="kgym.kgymHelper"%>
<%
    UserDataDTO login_udd = (UserDataDTO)session.getAttribute("loginData");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除確認画面</title>
    </head>
    <body>
        <h1>削除確認画面</h1>
        <h2>--ようこそ<%= ((UserDataDTO)session.getAttribute("loginData")).getName()%>さん！--</h2>
        以下の内容を削除しますか？<br>
            <ul>
                <li><strong>名前:</strong><%= login_udd.getName()%></li><br>
                <li><strong>E-mail:</strong><%= login_udd.getMail()%></li><br>
                <li><strong>住所:</strong><%= login_udd.getAddress()%></li><br>
                <li><strong>総購入金額:</strong><%= login_udd.getTotal()%></li><br>
                <li><strong>登録日時:</strong><%= login_udd.getNewDate()%></li><br>
            </ul>
        <div style="display:inline-flex">
        <form action="MyDeleteResult" method="GET">
          <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
          <input type="submit" name="delete" value="はい"style="width:100px">
        </form>
          <span style="margin-left:20px">
        <form action="top.jsp" method="GET">
          <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
          <input type="submit" name="delete" value="いいえ"style="width:100px">
        </form>
          </span>
        </div> 
          <br><br>
          <a href="mydata/mydata.jsp?ac=<%= session.getAttribute("ac")%>">マイページに移動</a><br><br>
          <a href="Cart?backPage=mydata/mydelete&ac=<%= session.getAttribute("ac")%>">カートに移動</a><br><br>
          <%=kgymHelper.getInstance().home()%>
    </body>
</html>
