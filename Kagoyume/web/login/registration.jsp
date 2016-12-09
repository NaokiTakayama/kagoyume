<%@page import="javax.servlet.http.HttpSession"
        import="kgym.kgymHelper"
        import="kgym.UserData" %>
<%
    UserData regist_ud = new UserData();
    String[] Pref = kgymHelper.getInstance().exPrefnum();
    
    boolean reinput = kgymHelper.getInstance().check_null(request.getParameter("mode"));
    boolean equal_pass = kgymHelper.getInstance().check_null(request.getAttribute("equal_pass"));
    boolean notMatch = kgymHelper.getInstance().check_null(request.getAttribute("notMatch"));
    boolean notMatch_2 = kgymHelper.getInstance().check_null(request.getAttribute("notMatch_2"));
    if(reinput || equal_pass || notMatch || notMatch_2){
        regist_ud = (UserData)session.getAttribute("regist_ud");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新規登録</title>
    </head>
    <body>
        <h1>新規登録画面</h1>
        <%if(equal_pass || notMatch){%>
        <p><font color="#ff0000"><strong>入力されたパスワードは使用できません</strong></font></p>
        <%;}%>
        
        <%if(notMatch_2){%>
        <p><font color="#ff0000"><strong>入力されたパスワードが一致しません</strong></font></p>
        <%;}%>
    <form action="RegistrationConfirm" method="POST">
        <strong>名前:</strong>
        <input type="text" name="name" value="<% if(reinput || equal_pass || notMatch || notMatch_2){out.print(regist_ud.getName());}%>">
        <br><br>

        <strong>パスワード（半角英数字　６文字以上（１文字目に数字、アンダーバーは不可））:</strong><br>
        &emsp;<input type="password" name="password">
        <br><br>
        
        <strong>確認のため、もう一度パスワードを入力して下さい:</strong><br>
        &emsp;<input type="password" name="password_2">
        <br><br>
        
        <strong>E-mail:</strong>
        <input type="text" name="mail" value="<% if(reinput || equal_pass || notMatch || notMatch_2){out.print(regist_ud.getMail());}%>">
        <br><br>
        
        <strong>県名:</strong>　
        <select name="prefecture">
            <option value="">----</option>
            <% for(int i=0; i<47; i++){ %>
            <option value="<%=Pref[i]%>" <% if((reinput || equal_pass || notMatch || notMatch_2) && regist_ud.getPrefecture().equals(Pref[i])){out.print("selected = \"selected\"");}%>>
                <%=Pref[i]%></option>
            <% } %>
        </select>
        <br><br>

        <strong>市区町村名:</strong>
        <input type="text" name="city" value="<% if(reinput || equal_pass || notMatch || notMatch_2){out.print(regist_ud.getCity());}%>">
        <br><br>
        
        <strong>番地:</strong>
        <input type="text" name="street_number" value="<% if(reinput || equal_pass || notMatch || notMatch_2){out.print(regist_ud.getStreetNumber());}%>">
        <br><br>
        
        <strong>建物名など:</strong>
        <input type="text" name="building" value="<% if(reinput || equal_pass || notMatch || notMatch_2){out.print(regist_ud.getBuilding());}%>">
        <br><br>
        
        <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="登録">
    </form>
        <br>
        <a href="<%= session.getAttribute("backPage")%>">戻る</a>
        <br><br>  
        <%=kgymHelper.getInstance().home()%>
    </body>
</html>
