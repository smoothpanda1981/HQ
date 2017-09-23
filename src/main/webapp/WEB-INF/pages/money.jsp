<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
	<title>Money</title>
	<%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
<h1 class="sub-header">
	${message} --- profit :
		<c:if test="${profitAmount >= 0}">
			<font style="color: green">${profitAmount}</font>
		</c:if>
		<c:if test="${profitAmount < 0}">
			<font style="color: red">${profitAmount}</font>
		</c:if>
</h1>

<div style="text-align: left">
	<strong>${result}</strong>
	<strong>BTCUSD Last : ${ticker_btcusd_last}</strong><br>
	<strong>BTCEUR Last : ${ticker_btceur_last}</strong><br>
	<strong>BTC : ${btc_balance}</strong><br>
	<strong>ETH : ${eth_balance}</strong><br>
	<strong>Cash USD : ${usd_available}</strong><br>
	<strong>Cash EUR : ${eur_available}</strong><br>
</div>

<div style="text-align: left">
	<strong>depositAmount USD : ${depositAmountUsd}</strong><br>
	<strong>depositAmount EUR : ${depositAmountEur}</strong><br>
	<strong>buyAmount : ${buyAmount}</strong><br>
	<strong>sellAmount : ${sellAmount}</strong><br>
	<strong>withDrawAmount USD : ${withDrawAmountUsd}</strong><br>
	<strong>withDrawAmount EUR : ${withDrawAmountEur}</strong><br>
</div>


<div class="col-xs-6">
	<h2 class="sub-header">Bitcoin Status</h2>
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>Bitcoin Available</th>
				<th>Ethereum Available</th>
				<th>USD Cash</th>
				<th>EUR Cash</th>
				<th>Profit</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td>${btc_balance}</td>
					<td>${eth_balance}</td>
					<td>${usd_available}</td>
					<td>${eur_available}</td>
					<c:if test="${profitAmount >= 0}">
						<td style="color: green">${profitAmount}</td>
					</c:if>
					<c:if test="${profitAmount < 0}">
						<td style="color: red">${profitAmount}</td>
					</c:if>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="col-xs-6">
	<h2 class="sub-header">Profit Status</h2>
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>ID</th>
				<th>Profit</th>
				<th>Creation Date</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${bitstampProfit.id}</td>
				<td>${bitstampProfit.profit}</td>
				<td>${bitstampProfit.creationDate}</td>
			</tr>
			</tbody>
		</table>
	</div>
</div>
<%--<div style="text-align: left">--%>
	<%--<strong>Total of Fedex Billing : ${fedexBillingTotal}</strong>--%>
	<%--<strong>4% (then 49%) : ${fourPercent}</strong>--%>
<%--</div>--%>
<%@ include file="footer.jsp"  %>
</body>
</html>
