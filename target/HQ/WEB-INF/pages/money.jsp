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
<%@ include file="footer.jsp"  %>
</body>
</html>
