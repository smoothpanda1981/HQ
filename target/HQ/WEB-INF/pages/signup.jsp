<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
    <title>Sign Up</title>
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
    <div class="col-md-offset-3 col-md-3">
        <h1 class="sub-header text-left">${message}</h1>
    </div>

    <div>
    <form:form id="loginForm" method="post" role="form" modelAttribute="signup">
        <div class="container" id="container1">
            <div class="row centered-form">
                <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title text-center">Please Register</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    <input type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="First Name">
                                </div>

                                <div class="form-group">
                                    <input type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name">
                                </div>

                                <div class="form-group">
                                    <input type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address">
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password">
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password">
                                        </div>
                                    </div>
                                </div>

                                <input type="submit" value="Register" class="btn btn-info btn-block">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
    </div>
<%@ include file="footer.jsp"  %>
</body>
</html>