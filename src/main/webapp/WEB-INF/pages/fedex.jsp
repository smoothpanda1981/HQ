<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
    <title>CRUD Fedex</title>
    <%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
    <div>
        <h1 class="sub-header text-left">${message}</h1>
    </div>
    <hr>
    <div>
        <h3>Create new Invoice</h3>
        <c:if test = "${not empty saveMessage}">
            <c:out value = "${saveMessage}"/><p>
        </c:if>
        <form:form id="createFedexForm" method="post" action="fedex" role="form" class="form-inline" modelAttribute="createFedex">
            <div class="form-group row">
                <form:label cssClass="col-sm-4 col-form-label" path="invoice_number">invoice_number</form:label>
                <div class="col-sm-8">
                    <form:input cssClass="form-control-plaintext" path="invoice_number" class="form-control" name="invoice_number"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <form:label cssClass="col-sm-4 col-form-label" path="invoice_type">invoice_type</form:label>
                <div class="col-sm-8">
                    <form:input cssClass="form-control-plaintext" path="invoice_type" class="form-control" name="invoice_type"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <form:label cssClass="col-sm-4 col-form-label" path="invoice_date">invoice_date</form:label>
                <div class="col-sm-8">
                    <form:input cssClass="form-control-plaintext" path="invoice_date" class="form-control" name="invoice_date"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <form:label cssClass="col-sm-4 col-form-label" path="due_date">due_date</form:label>
                <div class="col-sm-8">
                    <form:input cssClass="form-control-plaintext" path="due_date" class="form-control" name="due_date"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <form:label cssClass="col-sm-4 col-form-label" path="account_number">account_number</form:label>
                <div class="col-sm-8">
                    <form:select cssClass="form-control-plaintext" path="account_number" class="form-control" name="account_number" items="${accounts}"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <form:label cssClass="col-sm-4 col-form-label" path="invoice_status">invoice_status</form:label>
                <div class="col-sm-8">
                    <form:input cssClass="form-control-plaintext" path="invoice_status" class="form-control" name="invoice_status"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <form:label cssClass="col-sm-4 col-form-label" path="paid">paid</form:label>
                <div class="col-sm-8">
                    <form:input cssClass="form-control-plaintext" path="paid" class="form-control" name="paid"/>
                </div>
            </div>
            <br>
            <div class="form-group row">
                <form:label cssClass="col-sm-4 col-form-label" path="currency">currency</form:label>
                <div class="col-sm-8">
                    <form:input cssClass="form-control-plaintext" path="currency" class="form-control" name="currency"/>
                </div>
            </div>
            <br>
            <input type="submit" class="btn btn-primary" value="Create"/>
        </form:form>
    </div>
    <hr>
    <div>
        <h3>List</h3>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th>invoice no</th>
                    <th>invoice type</th>
                    <th>invoice date</th>
                    <th>due date</th>
                    <th>account no</th>
                    <th>invoice status</th>
                    <th>paid</th>
                    <th>currency</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${fedexList}" var="fedexList">
                    <tr>
                        <td>${fedexList.invoice_number}</td>
                        <td>${fedexList.invoice_type}</td>
                        <td>${fedexList.invoice_date}</td>
                        <td>${fedexList.due_date}</td>
                        <td>${fedexList.account_number}</td>
                        <td>${fedexList.invoice_status}</td>
                        <td>${fedexList.paid}</td>
                        <td>${fedexList.currency}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
<%@ include file="footer.jsp"  %>
</body>
</html>