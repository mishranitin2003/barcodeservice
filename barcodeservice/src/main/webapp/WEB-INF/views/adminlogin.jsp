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
    <script type="text/javascript" src="js/dojo/dojo.js"></script>
    <script type="text/javascript">
    
    	var dojoConfig = {
		    async: true,
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
  	
    <c:set var="csrfParamName">
    	<c:out value="${_csrf.parameterName}"/>
    </c:set>
    
    <c:set var="csrf">
    	<c:out value="${_csrf.token}"/>
    </c:set>
    
    <%
    	String pn = "'" + pageContext.getAttribute("csrfParamName") + "'";
    	String pv = "'" + pageContext.getAttribute("csrf") + "'";
    %>
    
    <script>
		require([
		    'dojo/dom',
		    'app/LoginDialog',
		    'dojo/domReady!'
		], function (dom, LoginDialog, MainScreen) {
		    var loginDialog = new LoginDialog();
		    loginDialog.csrfParam = <%= pn %>;
		    loginDialog.csrf = <%= pv %>;
		    loginDialog.show();
		});
	</script>
    
  </body>

</html>
