<%@page import="kgym.UserDataDTO"
        import="kgym.Product"
        import="kgym.kgymHelper"
        import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
    ArrayList<Product> buyList = (ArrayList<Product>)session.getAttribute("buyList");
    
    //発送方法が記入されずに戻ってきた場合
    boolean type_chk = kgymHelper.getInstance().check_null(request.getAttribute("type"));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入確認画面</title>
    </head>
    <body>
        <h1>購入確認</h1>
        <h2>--ようこそ<a href="MyData?backPage=cart&ac=<%= session.getAttribute("ac")%>">
                        <%= ((UserDataDTO)session.getAttribute("loginData")).getName()%></a>さん！--</h2>
        <%if(type_chk){%>
        <font color="#ff0000"><strong>発送方法を指定してください</strong></font><br><br>
        <%;}%>
        <table border=1>
            <tr>
                <th>名前</th>
                <th>金額</th>
            </tr>
            <%for(int i=0;i<buyList.size();i++){%>
            <tr>
                <td><a href="Item?productNumber=<%= i%>&ac=<%= session.getAttribute("ac")%>"><%= buyList.get(i).getName()%></a></td>
                <td><%= buyList.get(i).getPrice()%>円</td>
            </tr>
            <%}%>
            <tr>
                <td></td>
                <td>合計金額：<%= kg.totalOfCart(buyList)%>円</td>
            </tr>
        </table>
        <br>
        <form action="BuyComplete" method="POST">
            発送方法:
        <br>
            <% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>"><%=kg.exTypenum(i)%><br>
            <% } %>
        <br>
            
          <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
          <input type="submit" name="YES" value="購入する"style="width:100px">
        </form><br>
        <a href="cart.jsp">カートに戻る</a><br><br>
    <%=kg.home()%>
    <br>
    <br>
        <a href="Login?ac=<%= session.getAttribute("ac")%>">ログアウト</a><br>
    </body>
</html>