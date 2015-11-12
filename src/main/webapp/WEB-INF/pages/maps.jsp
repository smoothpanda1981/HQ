<html>
<head>
	<title>Google Map Page</title>
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
	<h1>${message}</h1>
	<div id="map_container"></div>
</body>
</html>