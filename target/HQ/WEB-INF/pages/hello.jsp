<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="header.jsp"  %>
</head>
<body>
	<%@ include file="menus.jsp"  %>
	<h1 class="col-md-12 sub-header">${message}</h1>
	<div class="col-md-12">
		<c:forEach items="${endPoints}" var="endPoint">
			<c:set var="string1" value="${endPoint.patternsCondition}"/>
			<c:set var="string2" value="${fn:replace(string1, '[', '')}" />
			<c:set var="string3" value="${fn:replace(string2, ']', '')}" />
			<c:set var="string4" value="${endPoint.methodsCondition}" />
			<c:choose>
				<c:when test="${fn:contains(string4, 'GET')}">
					<button type="button" class="btn btn-info btn-sm"><a href="${requestUrl}${string3}">${endPoint.patternsCondition}</a></button>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
	<div class="col-md-12"></div>
	<div class="table-responsive col-md-12">
		<table class="table table-striped table-bordered">
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
					<c:set var="string1" value="${endPoint.patternsCondition}"/>
					<c:set var="string2" value="${fn:replace(string1, '[', '')}" />
					<c:set var="string3" value="${fn:replace(string2, ']', '')}" />
					<c:set var="string4" value="${endPoint.methodsCondition}" />
				<tr>
					<c:choose>
						<c:when test="${fn:contains(string4, 'GET')}">
							<td><a href="${requestUrl}${string3}">${endPoint.patternsCondition}</a></td>
						</c:when>
						<c:otherwise>
							<td>${endPoint.patternsCondition}</td>
						</c:otherwise>
					</c:choose>
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