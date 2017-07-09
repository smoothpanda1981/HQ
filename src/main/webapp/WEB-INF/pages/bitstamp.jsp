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

<h3>${result}</h3>

<h3>BTC : ${btc_balance}</h3> <h3>Cash USD : ${usd_available}</h3>

<table class="table table-striped">
	<thead>
	<tr>

		<th>buy - #BTC</th>
		<th>buy - price</th>
		<th>sell - price</th>
		<th>buy - #BTC</th>
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