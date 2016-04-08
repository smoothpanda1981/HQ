<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
    <%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
<h1 class="sub-header">${message}</h1>
<font color="red">${error_message}</font>

<div>
    <form id="loginForm" method="post" role="form">
        <div class = "form-group">
            <label path="username">Username</label>
            <input id="username" name="username" placeholder = "Enter your username"/>
        </div>
        <div class = "form-group">
            <label path="password">Password</label>
            <input id="password" name="password" type="password" placeholder = "Enter your password"/>
        </div>
        <div class = "form-group">
            <input type="submit" value="Sign in"/>
        </div>
    </form>
</div>
<%@ include file="footer.jsp"  %>
</body>
</html>