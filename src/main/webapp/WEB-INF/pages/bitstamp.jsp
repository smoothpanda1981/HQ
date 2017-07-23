<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
	<title>Bitstamp</title>
	<%@ include file="header.jsp"  %>
</head>
<body>
<%@ include file="menus.jsp"  %>
<h1 class="sub-header">${message}</h1>

<div style="text-align: center">
	<strong>${result}</strong> <strong>BTC : ${btc_balance}</strong> <strong>Cash USD : ${usd_available}</strong>
</div>

<div style="text-align: center">
	<strong>depositAmount : ${depositAmount}</strong>
	<strong>buyAmount : ${buyAmount}</strong>
	<strong>sellAmount : ${sellAmount}</strong>
	<strong>withDrawAmount : ${withDrawAmount}</strong>
	<strong>profit : ${profit}</strong>
</div>


<table class="table table-striped">
	<thead>
	<tr>

		<th>buy_date</th>
		<th>buy_price</th>
		<th>buy_btc</th>
		<th>buy_fee</th>
		<th>buy_usd</th>
		<th>sell_date</th>
		<th>sell_price</th>
		<th>sell_btc</th>
		<th>sell_fee</th>
		<th>sell_usd</th>
		<th>Profit & Loss</th>
	</tr>
	</thead>
	<tbody>
	<c:set var="total" value="${0}"/>
	<c:forEach items="${profitsBuySellsList}" var="profitsBuySellsList">
		<tr>
			<td>${profitsBuySellsList.datetime_buy}</td>
			<td><font color="green">${profitsBuySellsList.btc_usd_buy}</font></td>
			<td>${profitsBuySellsList.btc_buy}</td>
			<td>${profitsBuySellsList.fee_buy}</td>
			<td>${profitsBuySellsList.usd_buy}</td>
			<td>${profitsBuySellsList.datetime_sell}</td>
			<td><font color="red">${profitsBuySellsList.btc_usd_sell}</font></td>
			<td>${profitsBuySellsList.btc_sell}</td>
			<td>${profitsBuySellsList.fee_sell}</td>
			<td>${profitsBuySellsList.usd_sell}</td>
			<td><font color="blue">${profitsBuySellsList.profitAndLose}</font></td>
		</tr>
		<c:set var="total" value="${total + profitsBuySellsList.profitAndLose}" />
	</c:forEach>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td>Total :</td>
		<td><font color="#00008b"><c:out value="${total}"/></font></td>
	</tr>
	</tbody>
</table>

<table class="table table-striped">
	<thead>
	<tr>

		<th>buy - #BTC</th>
		<th>buy - price</th>
		<th>sell - price</th>
		<th>sell - #BTC</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${bidsList}" var="bidsList" varStatus="status">
		<tr>
			<td>${bidsList.btcQuantity}</td>
			<td>${bidsList.price}</td>
			<td>${asksList[status.index].price}</td>
			<td>${asksList[status.index].btcQuantity}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="footer.jsp"  %>
</body>
</html>
