<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
    <title>Sign Up</title>
    <%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
    <div class="col-md-offset-3 col-md-3">
        <h1 class="sub-header text-left">${message}</h1>
    </div>

    <div>
    <form:form id="loginForm" method="post" role="form" modelAttribute="signup">
        <div class="form-group row">
            <p class="col-md-3 text-danger"></p>
        </div>
        <div class="form-group row">
            <p class="col-md-offset-3 col-md-3 text-danger">${error_message}</p>
        </div>
        <div class="form-group row">
            <form:label path="firstname" class="col-md-3 form-control-label text-right">First Name</form:label>
            <div class="col-md-3">
                <form:input path="firstname" class="form-control" name="firstname" placeholder = "Enter your first name"/>
            </div>
        </div>
        <div class="form-group row">
            <form:label path="lastname" class="col-md-3 form-control-label text-right">Last Name</form:label>
            <div class="col-md-3">
                <form:input path="lastname" class="form-control" name="lastname" placeholder = "Enter your last name"/>
            </div>
        </div>
        <div class="form-group row">
            <form:label path="firstname" class="col-md-3 form-control-label text-right">First Name</form:label>
            <div class="col-md-3">
                <form:input path="firstname" class="form-control" name="firstname" placeholder = "Enter your first name"/>
            </div>
        </div>
        <div class="form-group row">
            <form:label path="lastname" class="col-md-3 form-control-label text-right">Last Name</form:label>
            <div class="col-md-3">
                <form:input path="lastname" class="form-control" name="lastname" placeholder = "Enter your last name"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-offset-3 col-md-3">
                <input type="submit" class="btn btn-primary" value="Sign in"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-offset-3 col-md-3">
                <a class="glyphicon glyphicon-info-sign" href="/singup"> Don't have an account ? Register</a>
            </div>
        </div>
    </form:form>
    </div>
<%@ include file="footer.jsp"  %>
</body>
</html>