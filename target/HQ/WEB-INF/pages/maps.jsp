<html>
<head>
	<title>Google Map Page</title>
	<%@ include file="header.jsp"  %>
	<style type="text/css">
		div#map_container{
			width:800px;
			height:600px;
		}
	</style>
	<script type="text/javascript"
			src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

	<script type="text/javascript">
		function loadMap() {
			var latlng = new google.maps.LatLng(46.519653, 6.632273);
			var myOptions = {
				zoom: 16,
				center: latlng,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(document.getElementById("map_container"),myOptions);

			var marker = new google.maps.Marker({
				position: latlng,
				map: map,
				title:"my hometown, Lausanne !"
			});

		}
	</script>
</head>
<body onload="loadMap()">
	<%@ include file="menus.jsp"  %>
	<h1>${message}</h1>
	<input id="pac-input" class="controls" type="text" placeholder="Search Box">
	<div id="map_container"></div>
	<%@ include file="footer.jsp"  %>
</body>
</html>