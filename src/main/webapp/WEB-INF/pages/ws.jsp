<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="header.jsp"  %>
</head>
<body>
	<h1>${message}</h1>

	<c:forEach items="${urlList}" var="url">
		<p><a href="<c:out value="${url}"/>"><c:out value="${url}"/></a></p>
	</c:forEach>

	<%@ include file="footer.jsp"  %>
</body>
</html>