<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
	<title>Money2</title>
	<%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
<%--<h1 class="sub-header">--%>
	<%--${message} --- BTC profit (USD) :--%>
		<%--<c:if test="${profitAmount >= 0}">--%>
			<%--<font style="color: green">${profitAmount}</font>--%>
		<%--</c:if>--%>
		<%--<c:if test="${profitAmount < 0}">--%>
			<%--<font style="color: red">${profitAmount}</font>--%>
		<%--</c:if>--%>
<%--</h1>--%>

<div class="col-xs-12">
	<h2 class="sub-header">Bitcoin Status</h2>
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>Deposit USD</th>
				<th>Deposit EUR</th>
				<th>Withdraw USD</th>
				<th>Withdraw EUR</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${depositUSD}</td>
				<td>${depositEUR}</td>
				<td>${withdrawUSD}</td>
				<td>${withdrawEUR}</td>
			</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="col-xs-12">
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>buy BTC_USD</th>
				<th>buy BTC_EUR</th>
				<th>buy ETH_USD</th>
				<th>buy XRP_USD</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${buyBTC_USD}</td>
				<td>${buyBTC_EUR}</td>
				<td>${buyETH_USD}</td>
				<td>${buyXRP_USD}</td>
			</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="col-xs-12">
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>sell BTC_USD</th>
				<th>sell BTC_EUR</th>
				<th>sell ETH_USD</th>
				<th>sell XRP_USD</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${sellBTC_USD}</td>
				<td>${sellBTC_EUR}</td>
				<td>${sellETH_USD}</td>
				<td>${sellXRP_USD}</td>
			</tr>
			</tbody>
		</table>
	</div>
</div>


<%@ include file="footer.jsp"  %>
</body>
</html>
