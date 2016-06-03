<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        .centered-form {
            margin-top: 25px;
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
                            <h3 class="panel-title text-center">Please Register</h3>
                        </div>
                        <div class="panel-body">
                            <form:form id="signupForm" method="post" role="form" modelAttribute="signup">
                                <div class="well well-sm">
                                    <strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <input type="text" name="company" id="company" class="form-control input-sm" placeholder="Company Name" required>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="First Name">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                     </div>

                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name">
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                 </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <input type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address" required>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <input type="text" class="form-control input-sm" id="street" name="street" placeholder="Street address, P.O. box, c/o" required>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <input type="text" class="form-control input-sm" id="city" name="city" placeholder="City" required>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <select class="form-control input-sm" id="state" name="state">
                                                    <option value="VD">Vaud</option>
                                                    <option value="GE">Genève</option>
                                                    <option value="VS">Valais</option>
                                                    <option value="FR">Fribourg</option>
                                                    <option value="NE">Neuchâtel</option>
                                                </select>
                                            </div>
                                        </div>
                                     </div>

                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input type="text" class="form-control input-sm" id="zip" name="zip" placeholder="zip code" required>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                     </div>
                                 </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <input type="text" name="phone" class="form-control input-sm bfh-phone" data-country="CH" placeholder="Phone" required>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password" required>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password" required>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <input type="submit" value="Register" class="btn btn-info btn-block">
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp"  %>
</body>
</html>