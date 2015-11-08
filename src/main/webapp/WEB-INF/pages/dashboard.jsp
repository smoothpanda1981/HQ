<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<body>
	<h1>${message}</h1>
	<br>
	<c:choose>
		<c:when test="${connection_status == 'OK'}">
			mysql : <img src="http://icons.iconarchive.com/icons/dryicons/aesthetica-2/128/database-up-icon.png">
			<br />
		</c:when>
		<c:otherwise>
			mysql : <img src="http://icons.iconarchive.com/icons/dryicons/aesthetica-2/128/database-down-icon.png">
			<br />
		</c:otherwise>
	</c:choose>
	<br>
	<c:forEach items="${tableList}" var="table">
	Item <c:out value="${table}"/><p>
		</c:forEach>
</body>
</html>