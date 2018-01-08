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
				<th>BTC</th>
				<th>ETH</th>
				<th>Cash USD</th>
				<th>Cash EUR</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${btc_balance}</td>
				<td>${eth_balance}</td>
				<td>${usd_available}</td>
				<td>${eur_available}</td>
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
				<th>Deposit USD</th>
				<th>Withdraw USD</th>
				<th>Total USD</th>
				<th>Deposit EUR</th>
				<th>Withdraw EUR</th>
				<th>Total EUR</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${depositUSD}</td>
				<td>${withdrawUSD}</td>
				<td>${totalDepositUSD}</td>
				<td>${depositEUR}</td>
				<td>${withdrawEUR}</td>
				<td>${totalDepositEUR}</td>
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

<div class="col-xs-12">
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th>Total BTC_USD</th>
				<th>Total BTC_EUR</th>
				<th>Total ETH_USD</th>
				<th>Total XRP_USD</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${total_BTC_USD}</td>
				<td>${total_BTC_EUR}</td>
				<td>${total_ETH_USD}</td>
				<td>${total_XRP_USD}</td>
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
				<th>Cash USD</th>
				<th>Total Deposit in USD</th>
				<th>Total BTC in USD</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>${usd_available}</td>
				<td>${totalDeposit}</td>
				<td>${totalBTC}</td>
			</tr>
			</tbody>
		</table>
	</div>
</div>

<br><br><br>
<c:forEach var="entry" items="${map2}">
	<div class="col-xs-4">
		<a href="#entry-key-${entry.key}" data-toggle="collapse"><h3 class="sub-header">${entry.key}</h3></a>
		<div id="entry-key-${entry.key}" class="table-responsive collapse">
			<table class="table">
				<thead>
				<tr>
					<th>Date</th>
					<th>Deposit</th>
					<th>Withdraw</th>
					<th>Buy</th>
					<th>Sell</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="item" varStatus="loop" items="${entry.value}">
					<tr>
						<c:choose>
							<c:when test="${empty item.datetime}">
								<td></td>
							</c:when>
							<c:otherwise>
								<td>${item.datetime}</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${empty item.deposit}">
								<td></td>
							</c:when>
							<c:otherwise>
								<td>${item.deposit}</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${empty item.withdraw}">
								<td></td>
							</c:when>
							<c:otherwise>
								<td>${item.withdraw}</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${empty item.buy}">
								<td></td>
							</c:when>
							<c:otherwise>
								<td>${item.buy}</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${empty item.sell}">
								<td></td>
							</c:when>
							<c:otherwise>
								<td>${item.sell}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</c:forEach>


<div>
	<ul>
		<li>
			${balance_post_data_string}
		</li>
		<li>
			${user_transactions_post_data_string}
		</li>
	</ul>
</div>
<br><br>

<%@ include file="footer.jsp"  %>
</body>
</html>
