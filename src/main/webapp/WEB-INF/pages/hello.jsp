<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="header.jsp"  %>
</head>
<body>
	<%@ include file="menus.jsp"  %>
	<h1 class="sub-header">${message}</h1>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>path</th>
					<th>methods</th>
					<th>consumes</th>
					<th>produces</th>
					<th>params</th>
					<th>headers</th>
					<th>custom</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${endPoints}" var="endPoint">
				<tr>
					<td>${endPoint.patternsCondition}</td>
					<td>${endPoint.methodsCondition}</td>
					<td>${endPoint.consumesCondition}</td>
					<td>${endPoint.producesCondition}</td>
					<td>${endPoint.paramsCondition}</td>
					<td>${endPoint.headersCondition}</td>
					<td>${empty endPoint.customCondition ? "none" : endPoint.customCondition}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<%@ include file="footer.jsp"  %>
</body>
</html>