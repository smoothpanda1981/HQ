<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
    <%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
    <div class="col-md-offset-3 col-md-3">
        <h1 class="sub-header text-left">${message}</h1>
    </div>

<div>
    <form id="loginForm" method="post" role="form">
        <div class="form-group row">
            <h6 class="col-md-offset-3 col-md-3 text-danger">${error_message}</h6>
        </div>
        <div class="form-group row">
            <label for="username" class="col-md-3 form-control-label text-right">Username</label>
            <div class="col-md-3">
                <input id="username" class="form-control" name="username" placeholder = "Enter your username"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-md-3 form-control-label text-right">Password</label>
            <div class="col-md-3">
                <input id="password" class="form-control" name="password" type="password" placeholder = "Enter your password"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-offset-3 col-md-3">
                <input type="submit" class="btn btn-secondary" value="Sign in"/>
            </div>
        </div>
    </form>
</div>
<%@ include file="footer.jsp"  %>
</body>
</html>