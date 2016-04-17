<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
	<title>Stock Exchange</title>
	<%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
<h1 class="sub-header">${message}</h1>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
		<tr>
			<th>name</th>
			<th>currency</th>
			<th>day high</th>
			<th>day low</th>
		</tr>
		</thead>
		<tbody>
			<tr>
				<td>${stock_name}</td>
				<td>${stock_currency}</td>
				<td>${stock_day_high}</td>
				<td>${stock_day_low}</td>
			</tr>
		</tbody>
	</table>
</div>
<%@ include file="footer.jsp"  %>
</body>
</html>