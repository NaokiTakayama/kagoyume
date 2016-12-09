<%@page import="kgym.UserDataDTO"
        import="kgym.UserData"
        import="java.util.ArrayList"
        import="kgym.kgymHelper"%>
<%
    kgymHelper kg = kgymHelper.getInstance();
    UserData ud = (UserData)session.getAttribute("update");
    String[] Pref = kg.getInstance().exPrefnum();
    
    //入力欄に空欄があるかチェック
    boolean empty_chk = false;
    if((request.getAttribute("mode1") != null)){
        empty_chk = true;
    }
    
    //入力内容が全く同じかチェック
    boolean equal_chk = false;
    if((request.getAttribute("mode2") != null)){
        equal_chk = true;
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>更新画面</title>
    </head>
    <body>
        <h1>更新画面</h1>
        <h2>--ようこそ<%= ((UserDataDTO)session.getAttribute("loginData")).getName()%>さん！--</h2>
        
    <%--入力内容に空欄があるかで場合分け--%>
    <%if(empty_chk){%>
            <font color="#ff0000"><strong>入力欄に空欄があります。</strong></font>
            <br><br>
    <%;}%>    
    
    <%--入力内容が全く同じかで場合分け--%>
    <%if(equal_chk){%>
            <font color="#ff0000"><strong>入力内容に変化がありません。</strong></font>
            <br><br>
    <%;}%>
    
    <form action="MyUpdateResult" method="POST">
        <strong>名前:</strong>
        <input type="text" name="name" value="<%= ud.getName()%>">
        <br><br>
        
        <strong>パスワード:</strong>
        <input type="text" name="password" value="<%= ud.getPassword()%>">
        <br><br>
        
        <strong>E-mail:</strong>
        <input type="text" name="mail" value="<%= ud.getMail()%>">
        <br><br>
        
        <strong>県名:</strong>　
        <select name="prefecture">
            <option value="">----</option>
            <% for(int i=0; i<47; i++){ %>
            <option value="<%=Pref[i]%>" <% if(ud.getPrefecture().equals(Pref[i])){out.print("selected = \"selected\"");}%>>
                <%=Pref[i]%></option>
            <% } %>
        </select>
        <br><br>

        <strong>市区町村名:</strong>
        <input type="text" name="city" value="<%= ud.getCity()%>">
        <br><br>
        
        <strong>番地:</strong>
        <input type="text" name="street_number" value="<%= ud.getStreetNumber()%>">
        <br><br>
        
        <strong>建物名など:</strong>
        <input type="text" name="building" value="<%= ud.getBuilding()%>">
        <br><br>
        
        <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="変更内容を適用">
    </form>
        <br><br>
        <a href="mydata/mydata.jsp?ac=<%= session.getAttribute("ac")%>">マイページに移動</a><br><br>
        <a href="Cart?backPage=mydata/myupdate&ac=<%= session.getAttribute("ac")%>">カートに移動</a><br><br>
        <%=kg.home()%>
    </body>
</html>
