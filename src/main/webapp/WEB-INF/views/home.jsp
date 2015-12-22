<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<html>
<head>
	<title>Shopper Shop Home Page</title>
	<link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />
</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
<h1>Welcome to Shopper Shop</h1>
<img src="${context}/resources/images/Shopper1.jpg" width="400" height="400">
<br>
<br>
<br>
</center>
</div>
</body>
</html>
