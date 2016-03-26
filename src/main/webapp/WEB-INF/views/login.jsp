<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/login" method="POST" id="login-user">
        Login: <input type="text" value="" name="login"><br>
        <%--<div>Password must have at least 1<br>--%>
             <%--number and be between 6 and 30 characters long</div>--%>
        Password: <input type="password" value="" name="pass"><br>
        <input type="submit" value="Send">
    </form>
</div>