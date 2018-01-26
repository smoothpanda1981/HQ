<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html>
<head>
	<title>${message}</title>
	<%@ include file="header.jsp"  %>

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data1 = google.visualization.arrayToDataTable(${data1});
            var data2 = google.visualization.arrayToDataTable(${data2});

            var options1 = {
                title: 'Bitcoin Price',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var options2 = {
                title: 'Bitcoin Volume',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart1 = new google.visualization.LineChart(document.getElementById('curve_chart1'));
            var chart2 = new google.visualization.LineChart(document.getElementById('curve_chart2'));

            chart1.draw(data1, options1);
            chart2.draw(data2, options2);


        }
	</script>
</head>
<body>
<%@ include file="menus.jsp"  %>
<h2 class="sub-header">${message}</h2>
<div class="col-xs-6">
	<div id="curve_chart1"></div>
	<div id="curve_chart2"></div>
</div>
<div class="col-xs-6">

</div>
<%@ include file="footer.jsp"  %>
</body>
</html>
