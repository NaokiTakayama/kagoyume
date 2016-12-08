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
        <title>マイページ</title>
    </head>
    <body>
        <h1>マイページ</h1>
        <h2>--ようこそ<%= ((UserDataDTO)session.getAttribute("loginData")).getName()%>さん！--</h2>
            <ul>
                <li>名前:<%= login_udd.getName()%></li><br>
                <li>パスワード:<%= login_udd.getPassword()%></li><br>
                <li>E-mail:<%= login_udd.getMail()%></li><br>
                <li>住所:<%= login_udd.getAddress()%></li><br>
                <li>総購入金額:<%= login_udd.getTotal()%></li><br>
                <li>登録日時:<%= login_udd.getNewDate()%></li><br>
            </ul>
            <form action="MyHistory" method="GET">
              <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
              <input type="submit" name="history" value="購入履歴"style="width:100px">
            </form><br>
            <form action="MyUpdate" method="GET">
              <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
              <input type="submit" name="update" value="登録内容の変更"style="width:120px">
            </form><br>
            <form action="MyDelete" method="GET">
              <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
              <input type="submit" name="delete" value="登録内容の削除"style="width:120px">
            </form>
        
          <br><br>
          <%=kgymHelper.getInstance().home()%>
          <br><br>
          <a href="Cart?backPage=mydata/mydata&ac=<%= session.getAttribute("ac")%>">カートに移動</a>
          <br><br>
          <a href="<%= session.getAttribute("backPage")%>?ac=<%= session.getAttribute("ac")%>">元のページに戻る</a>
          <br><br>
          <a href="Login?ac=<%= session.getAttribute("ac")%>">ログアウト</a><br>
    </body>
    
</html>
