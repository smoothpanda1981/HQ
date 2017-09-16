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
<h1 class="sub-header">${message}</h1>

<div style="text-align: left">
	<strong>${result}</strong> <strong>BTC : ${btc_balance}</strong> <strong>Cash USD : ${usd_available}</strong>
</div>

<div style="text-align: left">
	<strong>depositAmount : ${depositAmount}</strong>
	<strong>buyAmount : ${buyAmount}</strong>
	<strong>sellAmount : ${sellAmount}</strong>
	<strong>withDrawAmount : ${withDrawAmount}</strong>
	<strong>profit : ${profitAmount}</strong>
</div>


<div>
	<table class="table table-bordered table-inverse">
		<thead>
		<tr>
			<th>Bitcoin Available</th>
			<th>Ethereum Available</th>
			<th>USD Cash</th>
			<th>EUR Cash</th>
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


<%--<div style="text-align: left">--%>
	<%--<strong>Total of Fedex Billing : ${fedexBillingTotal}</strong>--%>
	<%--<strong>4% (then 49%) : ${fourPercent}</strong>--%>
<%--</div>--%>
<%@ include file="footer.jsp"  %>
</body>
</html>
