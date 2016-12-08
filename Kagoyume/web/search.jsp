<%@page import="kgym.UserDataDTO"
        import="kgym.Product"
        import="kgym.kgymHelper"
        import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
    ArrayList<Product> productList = (ArrayList<Product>)session.getAttribute("searchProduct");
    
    //ログイン情報の有無を判定
    boolean login_chk = kgymHelper.getInstance().check_null(session.getAttribute("loginData"));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>検索結果画面</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <%if(login_chk){%>
            <h2>--ようこそ<a href="MyData?backPage=search&ac=<%= session.getAttribute("ac")%>">
                        <%= ((UserDataDTO)session.getAttribute("loginData")).getName()%></a>さん！--</h2>
        <%;}%>
        <br><br>
        <%=kg.home()%><br><br>
        <%if(login_chk){%>
            <a href="Cart?backPage=search&ac=<%= session.getAttribute("ac")%>">カートに移動</a><br><br>
        <%;}else{%>
            <a href="Login?backPage=search&ac=<%= session.getAttribute("ac")%>">ログイン</a><br><br>
            <a href="Registration?backPage=search&ac=<%= session.getAttribute("ac")%>">新規登録</a><br><br>
        <%;}%>
        <%if(login_chk){%>
        <a href="Login?ac=<%= session.getAttribute("ac")%>">ログアウト</a><br><br>
        <%;}%>
        <table border=1>
            <tr>
                <th>画像</th>
                <th>名前</th>
                <th>金額</th>
            </tr>
            <%for(int i=0;i<productList.size();i++){%>
            <tr>
                <td><a href="Item?productNumber=<%= i%>&ac=<%= session.getAttribute("ac")%>"><img src="<%= productList.get(i).getImage()%>"/></a></td>
                <td><a href="Item?productNumber=<%= i%>&ac=<%= session.getAttribute("ac")%>"><%= productList.get(i).getName()%></a></td>
                <td><%= productList.get(i).getPrice()%>円</td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
