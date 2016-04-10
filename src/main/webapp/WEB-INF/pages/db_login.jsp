<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
    <title>CRUD Login</title>
    <%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
    <div>
        <h1 class="sub-header text-left">${message}</h1>
    </div>
    <hr>
    <div>
        <h3>Create new login</h3>
        <h6 class="text-danger">${create_login_message}</h6>
        <form:form id="createloginForm" method="post" action="login" role="form" class="form-inline" modelAttribute="createLogin">
            <div class="form-group">
                <form:label path="username">Username</form:label>
                <form:input path="username" class="form-control" name="username"/>
            </div>
            <div class="form-group">
                <form:label path="password">Password</form:label>
                <form:input path="password" class="form-control" name="password" type="password"/>
            </div>
            <input type="submit" class="btn btn-primary" value="Create"/>
        </form:form>
    </div>
    <hr>
    <div>
        <h3>Update existing login</h3>
        <h6 class="text-danger">${update_login_message}</h6>
        <form:form id="updateloginForm" method="PUT" action="login" role="form" class="form-inline" modelAttribute="updateLogin">
            <div class="form-group">
                <form:label path="current_username">Current Username</form:label>
                <form:input path="current_username" class="form-control" name="current_username"/>
            </div>
            <div class="form-group">
                <form:label path="current_password">Curent Password</form:label>
                <form:input path="current_password" class="form-control" name="current_password" type="password"/>
            </div>
            <div class="form-group">
                <form:label path="new_username">New Username</form:label>
                <form:input path="new_username" class="form-control" name="new_username"/>
            </div>
            <div class="form-group">
                <form:label path="new_password">New Password</form:label>
                <form:input path="new_password" class="form-control" name="new_password" type="password"/>
            </div>
            <input type="hidden" name="_method" value="PUT"/>
            <input type="submit" class="btn btn-primary" value="Update"/>
        </form:form>
    </div>
    <hr>
    <div>
        <h3>Delete existing login</h3>
        <h6 class="text-danger">${delete_login_message}</h6>
        <form:form id="deleteloginForm" method="DELETE" action="login" role="form" class="form-inline" modelAttribute="deleteLogin">
            <div class="form-group">
                <form:label path="delete_username">Username</form:label>
                <form:input path="delete_username" class="form-control" name="username"/>
            </div>
            <div class="form-group">
                <form:label path="delete_password">Password</form:label>
                <form:input path="delete_password" class="form-control" name="password" type="password"/>
            </div>
            <input type="hidden" name="_method" value="PUT"/>
            <input type="submit" class="btn btn-primary" value="Delete"/>
        </form:form>
    </div>
<%@ include file="footer.jsp"  %>
</body>
</html>