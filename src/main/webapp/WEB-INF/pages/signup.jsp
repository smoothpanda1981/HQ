<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        .centered-form {
            margin-top: 10px;
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
                                        <form:input type="text" path="company" id="company" class="form-control input-sm" placeholder="Company Name" required="required" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <form:input type="text" path="username" id="username" class="form-control input-sm" placeholder="Username" required="required" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <form:input type="text" path="first_name" id="first_name" class="form-control input-sm" placeholder="First Name" required="required" />
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                     </div>

                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <form:input type="text" path="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name" required="required" />
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                 </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <form:input type="email" path="email" id="email" class="form-control input-sm" placeholder="Email Address" required="required" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <form:input type="text" class="form-control input-sm" id="street" path="street" placeholder="Street address, P.O. box, c/o" required="required" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <form:input type="text" class="form-control input-sm" id="city" path="city" placeholder="City" required="required" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <form:select class="form-control input-sm" id="state" path="state" required="required">
                                                    <option value="ZZ">State</option>
                                                    <option value="VD">Vaud</option>
                                                    <option value="GE">Genève</option>
                                                    <option value="VS">Valais</option>
                                                    <option value="FR">Fribourg</option>
                                                    <option value="NE">Neuchâtel</option>
                                                    <option value="ZZ">Autres</option>
                                                </form:select>
                                            </div>
                                        </div>
                                     </div>

                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <form:input type="text" class="form-control input-sm" id="zip" path="zip" placeholder="zip code" required="required" />
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                     </div>
                                 </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <form:input type="text" path="phone" class="form-control input-sm bfh-phone" data-country="CH" placeholder="Phone" required="required" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <form:input type="password" path="password" id="password" class="form-control input-sm" placeholder="Password" required="required" />
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <form:input type="password" path="password_confirmation" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password" required="required" />
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