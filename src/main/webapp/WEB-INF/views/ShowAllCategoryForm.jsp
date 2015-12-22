<%@ include file="./include.jsp"%>
<html>
<head>
	<title>Categories of items</title>
</head>
<body>
<H1>Category of Items</H1>
<hr>
<ul id="nav">
<c:forEach var="curcategory" items="${categories}">
 <li><a href="${context}/ShowAllCategoryProducts?category=${curcategory}">${curcategory}</a> </li>
	
 
</c:forEach>
 </ul>
</body>