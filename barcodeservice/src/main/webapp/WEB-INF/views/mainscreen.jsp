<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<html>
  <head>
    <title>Barcode API Admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Language" content="en" />
    
    <!--link rel="shortcut icon" href="../favicon.png" /-->
    <link rel="stylesheet" href="js/theme/reset.css" />
    <link rel="stylesheet" href="js/dijit/themes/claro/claro.css" />
    <link rel="stylesheet" href="js/dojox/grid/resources/claroGrid.css" />
    <link rel="stylesheet" href="js/dojox/grid/resources/Grid.css" />
    <link rel="stylesheet" href="js/theme/main.css" />
    <script type="text/javascript" src="js/dojo/dojo.js" data-dojo-config="parseOnLoad: true"></script>
    <script type="text/javascript">
    
		var dojoConfig = {
		    baseUrl: '.',
		    packages: [
		        'js/dojo',
		        'js/dijit',
		        'js/dojox',
		        'js/app'
		    ]
		};
   
	</script>
    
  </head>
  
  <body class="claro">
	<script>
		require([
		    'dojo/dom',
		    'dojo/_base/window',
		    'app/MainScreen'
		], function (dom, win, MainScreen) {
			var ms = new MainScreen({loggedinuser: 'nmishra'});
		   ms.placeAt(win.body());
		});
	</script>
    
  </body>

</html>
