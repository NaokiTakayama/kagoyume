<%@page import="kgym.UserDataDTO"
        import="kgym.Product"
        import="kgym.kgymHelper"
        import="javax.servlet.http.HttpSession"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
    Product itemProduct = (Product)session.getAttribute("itemProduct");
    
    //ログイン情報の有無を判定
    boolean login_chk = kgymHelper.getInstance().check_null(session.getAttribute("loginData"));
    
    //どこからアクセスしてきたのか判定
    boolean fromSearch = kg.check_null(request.getAttribute("fromSearch"));
    boolean fromCart = kg.check_null(request.getAttribute("fromCart"));
    boolean fromHistory = kg.check_null(request.getAttribute("fromHistory"));
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>アイテム詳細ページ</title>
        <style type="text/css">
            #a-box{
                float:left;
                width:350px;
            }
            #c-box{
                float:right;
                width:350px;
            }
        </style>
    </head>
    <body>
        <h1>アイテム詳細</h1>
        <%if(login_chk){%>
            <h2>--ようこそ<a href="MyData?backPage=item&ac=<%= session.getAttribute("ac")%>">
                        <%= ((UserDataDTO)session.getAttribute("loginData")).getName()%></a>さん！--</h2>
        <%;}%>
        
            <div id="a-box">
                <img src="<%= itemProduct.getImgLarge()%>"/>
            </div>

            <div id="b-box">
                <strong>商品名：</strong><br>
                &emsp;<%= itemProduct.getName()%> <br><br>
                
                <strong>商品説明：</strong><br>
                &emsp;<%= itemProduct.getDescription()%><br><br>
                
                <Strong>価格：</strong><br>
                &emsp;<%= itemProduct.getPrice()%>円 <br><br>
            
        
        <br>
        <div id="c-box">
            <a href="Add?ac=<%= session.getAttribute("ac")%>">カートに追加</a><br><br>
        <%if(fromSearch){%>
            <a href="search.jsp">商品一覧画面に戻る</a><br>
        <%;}%>
        <%if(fromCart){%>
            <a href="cart.jsp">カート画面に戻る</a><br>
        <%;}%>
        <%if(fromHistory){%>
            <a href="myhistory.jsp">購入履歴に戻る</a><br>
        <%;}%>
        <br>
        <%if(!login_chk){%>
            <a href="Login?backPage=item&ac=<%= session.getAttribute("ac")%>">ログイン</a><br><br>
            <a href="Registration?backPage=item&ac=<%= session.getAttribute("ac")%>">新規登録</a><br><br>
        <%;}%>
        <%if(login_chk){%>
            <a href="Cart?backPage=item&ac=<%= session.getAttribute("ac")%>">カートに移動</a><br><br>
        <%;}%>    
        <%=kg.home()%><br><br>
        <%if(login_chk){%>    
            <a href="Login?ac=<%= session.getAttribute("ac")%>">ログアウト</a><br>
        <%;}%>
        
        </div>
            </div>
    </body>
</html>
