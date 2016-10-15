<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title></title>
	<style>
		.header {
			color: #36A0FF;
			font-size: 27px;
			padding: 10px;
		}

		.bigicon {
			font-size: 35px;
			color: #36A0FF;
		}
	</style>
	<%@ include file="header.jsp"  %>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<%@ include file="menus.jsp"  %>

<h1 class="sub-header">${message}</h1>

<form:form id="searchForm" method="post" action="keepass" role="form" class="form-inline" modelAttribute="seacheKeepass">
	<div class="form-group">
		<form:label path="searchField">Search : </form:label>
		<form:input path="searchField" class="form-control" name="searchField"/>
	</div>
	<button type="button" class="btn btn-default" aria-label="Left Align">
		<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
	</button>
	<input type="submit" class="btn btn-primary" value="Search"/>
</form:form>

<%@ include file="footer.jsp"  %>
</body>
</html>