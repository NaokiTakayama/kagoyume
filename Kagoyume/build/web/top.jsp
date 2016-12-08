<%@page import="kgym.UserDataDTO"
        import="kgym.kgymHelper"%>
<%
    //ログイン情報の有無を判定
    boolean login_chk = kgymHelper.getInstance().check_null(session.getAttribute("loginData"));
    
    //検索結果の有無で場合分け
    boolean empty_chk = kgymHelper.getInstance().check_null(request.getAttribute("empty"));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>トップ画面</title>
    </head>
        <body>
            <h1>「かごいっぱいのゆめ」にようこそ！</h1><br>
            <%if(login_chk){%>
                <h2>--ようこそ<a href="MyData?backPage=top&ac=<%= session.getAttribute("ac")%>">
                        <%= ((UserDataDTO)session.getAttribute("loginData")).getName()%></a>さん！--</h2>
            <%;}%>    
            <h3>ここでは、商品の検索を行います。</h3><br>
            <%if(empty_chk){%>
                <font color="#ff0000"><strong>該当商品は存在しません</strong></font>
            <%;}%>
            
                <p>キーワードを入力して下さい</p>
                
            <form action="Search" method="GET">
                キーワード検索:
                <input type="text" name="keyword" value="">
                <br><br>

                <input type="hidden" name="ac" value="<%= session.getAttribute("ac")%>">
                <input type="submit" name="btnSubmit" value="検索">
            </form>
                <br><br>
                
                <p>本日は「かごいっぱいのゆめ」にお越しいただきありがとうございます。</p>
                <p>このサイトでは、疑似的なネットショッピングをお楽しみいただけます。</p><br>
                
                <p>まずは、上の検索フォームに購入したい商品のキーワードを入力して下さい。</p>
                <p>関連商品が一覧となって表示されます。</p><br>
                
                <p>また、本サイトに登録していただけますと、実際に商品を購入（実際には購入できませんが・・・）<br>
                することが出来ます。</p>
                <p>ぜひ登録してお楽しみください。</p><br><br>
                <%if(login_chk){%>
                    <a href="Cart?backPage=top&ac=<%= session.getAttribute("ac")%>">カートに移動</a><br><br>
                <%;}else{%>
                    <a href="Login?backPage=top&ac=<%= session.getAttribute("ac")%>">ログイン</a><br><br>
                    <a href="Registration?backPage=top&ac=<%= session.getAttribute("ac")%>">新規登録</a>
                <%;}%>
                <%if(login_chk){%>
                <a href="Login?ac=<%= session.getAttribute("ac")%>">ログアウト</a><br><br>
                <%;}%>
        </body>
    </html>

