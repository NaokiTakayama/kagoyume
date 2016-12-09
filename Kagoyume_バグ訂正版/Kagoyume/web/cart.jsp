<%@page import="kgym.UserDataDTO"
        import="kgym.Product"
        import="kgym.kgymHelper"
        import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
    
    //カートに商品があるか判定
    boolean cart_chk = kg.check_null(session.getAttribute("cartProductList"));
    ArrayList<Product> cartProductList = (ArrayList<Product>) session.getAttribute("cartProductList");
    
    //ボタンにチェックされずに戻されたか判定
    boolean noCheck = kg.check_null(request.getAttribute("noCheck"));
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート画面</title>
    </head>
    <body>
        <h1>カート画面</h1>
        <h2>--ようこそ<a href="MyData?backPage=cart&ac=<%= session.getAttribute("ac")%>">
                        <%= ((UserDataDTO)session.getAttribute("loginData")).getName()%></a>さん！--</h2>
        <%if(noCheck){%>
            <font color="#ff0000"><strong>購入もしくは削除したい商品にチェックしてください</strong></font>
        <%;}%>                
                        
        <%if(cart_chk && !cartProductList.isEmpty()){%>
        <form action="" method="GET">
        <table border=1>
            <tr>
                <th>画像</th>
                <th>名前</th>
                <th>金額</th>
                <th>削除</th>
                <th>購入</th>
            </tr>
            <%for(int i=0;i<cartProductList.size();i++){%>
            <tr>
                <td><a href="Item?cartProductNumber=<%= i%>&ac=<%= session.getAttribute("ac")%>"><img src="<%= cartProductList.get(i).getImage()%>"/></a></td>
                <td><a href="Item?cartProductNumber=<%= i%>&ac=<%= session.getAttribute("ac")%>"><%= cartProductList.get(i).getName()%></a></td>
                <td><%= cartProductList.get(i).getPrice()%>円</td>
                <td>
                    <input type="checkbox" name="cartNumber_del" value="<%= i%>"style="width:100px">削除
                </td>
                <td>
                    <input type="checkbox" name="cartNumber_buy" value="<%= i%>"style="width:100px">購入
                </td>
            </tr>
            <%}%>
            <tr>
                <td></td>
                <td></td>
                <td>合計金額：<%= kg.totalOfCart(cartProductList)%>円</td>
                <td>
                    <input type="checkbox" name="cartNumber_del_all" value="all"style="width:100px">すべて削除
                </td>
                <td>
                    <input type="checkbox" name="cartNumber_buy_all" value="all"style="width:100px">すべて購入
                </td>
            </tr>
        </table>
        <br>
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>" formaction="CartDelete">
            <input type="submit" name="YES" value="削除する"style="width:100px" formaction="CartDelete">
        
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>" formaction="BuyConfirm">
            <input type="submit" name="YES" value="購入する"style="width:100px" formaction="BuyConfirm">
        </form>
        <%;}else{%>    
        <font color="#ff0000"><strong>カート内に商品は存在しません</strong></font>
        <%;}%>
        <br>
        <br>
    <%=kg.home()%>
    <br>
        <a href="<%= session.getAttribute("backPage")%>?ac=<%= session.getAttribute("ac")%>">前のページに戻る</a><br>
        <a href="Login?ac=<%= session.getAttribute("ac")%>">ログアウト</a><br>
       
    </body>
</html>
