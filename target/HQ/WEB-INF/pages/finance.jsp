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
			<c:forEach items="${stockDataList}" var="stockDataList">
			<tr>
				<td>${stockDataList.stockName}</td>
				<td>${stockDataList.stockCurrency}</td>
				<td>${stockDataList.stockDayHigh}</td>
				<td>${stockDataList.stockDayLow}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
		<tr>
			<th>symbol</th>
			<th>price</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${fxDataList}" var="fxDataList">
			<tr>
				<td>${fxDataList.fxSymbol}</td>
				<td>${fxDataList.fxPrice}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="footer.jsp"  %>
</body>
</html>