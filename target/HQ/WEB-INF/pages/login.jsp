<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
    <style>
        .centered-form {
            margin-top: 120px;
            margin-bottom: 120px;
        }

        .centered-form .panel {
            background: rgba(255, 255, 255, 0.8);
            box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;
        }
    </style>
    <%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
<div>
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title text-center">${message}</h3>
                </div>
                <div class="panel-body">
                    <form:form id="loginForm" method="post" role="form" modelAttribute="login">
                        <div class="form-group">
                            <p class="text-danger"></p>
                        </div>
                        <div class="form-group ">
                            <p class="text-danger">${error_message}</p>
                        </div>
                        <div class="form-group">
                            <form:input path="username" class="form-control input-sm" name="username" placeholder = "Enter your username"/>
                        </div>

                        <div class="form-group">
                            <form:input path="password" class="form-control input-sm" name="password" type="password" placeholder = "Enter your password"/>
                        </div>

                        <input type="submit" class="btn btn-info btn-block" value="Sign in"/>
                    </form:form>
                    <div class="form-group">
                        <label class="glyphicon glyphicon-info-sign"> Don't have an account? </label><a href="/signup"> Register</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"  %>
</body>
</html>