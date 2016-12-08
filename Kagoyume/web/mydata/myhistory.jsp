<%@page import="kgym.UserDataDTO"
        import="kgym.Product"
        import="kgym.kgymHelper"
        import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
    ArrayList<Product> recordProduct = (ArrayList<Product>)session.getAttribute("recordProduct");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入履歴</title>
    </head>
    <body>
        <h1>購入履歴</h1>
        <h2>--ようこそ<%= ((UserDataDTO)session.getAttribute("loginData")).getName()%>さん！--</h2>
        <%if(recordProduct.isEmpty()){%> 
            <font color="#ff0000"><strong>購入履歴は存在しません</strong></font>
        <%;}else{%>
        <table border=1>
            <tr>
                <th>画像</th>
                <th>名前</th>
                <th>金額</th>
            </tr>
            <%for(int i=0;i<recordProduct.size();i++){%>
            <tr>
                <td><a href="Item?recordProductNumber=<%= i%>&ac=<%= session.getAttribute("ac")%>">
                        <img src="<%= recordProduct.get(i).getImage()%>"/></a></td>
                <td><a href="Item?recordProductNumber=<%= i%>&ac=<%= session.getAttribute("ac")%>">
                        <%= recordProduct.get(i).getName()%></a></td>
                <td><%= recordProduct.get(i).getPrice()%>円</td>
            </tr>
            <%}%>
            <tr>
                <th></th>
                <th></th>
                <th>総購入金額：<%= kg.totalOfCart(recordProduct)%>円</th>
            </tr>
        </table>   
        <%;}%>
        <br><br>
        <a href="mydata/mydata.jsp?ac=<%= session.getAttribute("ac")%>">マイページに移動</a><br><br>
        <a href="Cart?backPage=mydata/myhistory&ac=<%= session.getAttribute("ac")%>">カートに移動</a><br><br>
    <%=kg.home()%>
    </body>
</html>
