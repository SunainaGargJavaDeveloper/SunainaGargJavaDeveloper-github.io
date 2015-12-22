<%@ include file="./include.jsp"%>
<html>
<head>
	<title>Categories of items</title>
</head>
<body>
<H1>Items Available</H1>
<hr>

<table border>
<tr><th>Product Id</th>
<th>Product name</th>
<th>Quantity</th>
<th>Amount($)</th>
<th>Sizes Available</th></tr>
<c:forEach var="curproduct" items="${categoryProducts}">
 <tr><td>${curproduct.getItem_id()}</td>
 <td>${curproduct.getItem_name()}</td>
 <td>${curproduct.getQuantity()}</td>
 <td>${curproduct.getAmount()}</td>
 <td>${curproduct.getSize()}</td></tr>
	
 
</c:forEach>
</table>
 
</body>