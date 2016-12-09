<%@page import="kgym.UserDataDTO"
        import="kgym.Product"
        import="kgym.kgymHelper"
        import="javax.servlet.http.HttpSession"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
    Product itemProduct = (Product)session.getAttribute("itemProduct");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート追加確定画面</title>
    </head>
    <body>
        <h1>カート追加確定</h1>
        <h2>--ようこそ<a href="MyData?backPage=add&ac=<%= session.getAttribute("ac")%>">
                    <%= ((UserDataDTO)session.getAttribute("loginData")).getName()%></a>さん！--</h2>
        <table border=1>
            <tr>
                <th>名前</th>
                <th>金額</th>
            </tr>
            <tr>
                <td><a href="item.jsp?ac=<%= session.getAttribute("ac")%>"><%= itemProduct.getName()%></a></td>
                <td><%= itemProduct.getPrice()%>円</td>
            </tr>
        </table>
            <br><br>
        <a href="search.jsp">商品一覧に戻る</a>
        <br><br>
        <a href="item.jsp">商品詳細に戻る</a>
        <br><br>
        <a href="Cart?backPage=add&ac=<%= session.getAttribute("ac")%>">カートに移動</a>
        <br><br>
        <%=kg.home()%>
        <br><br>
            <a href="Login?ac=<%= session.getAttribute("ac")%>">ログアウト</a><br>
        
    </body>
</html>
