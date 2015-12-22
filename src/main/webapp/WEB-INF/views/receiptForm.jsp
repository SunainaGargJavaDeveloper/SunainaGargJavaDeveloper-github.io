<%@ include file="./include.jsp"%>
<head>
<title>Receipt</title>
</head>
<body>
<h1>Receipt for order_no ${FinalOrder.getOrder_no()}</h1>
<p> Customer First Name:  ${FinalOrder.getCustomer().getFirstName()}<br>
 Customer Last Name:  ${FinalOrder.getCustomer().getLastName()}<br>
 Items Purchased:<br>
 <c:forEach var="curItem" items="${FinalOrder.getItems()}">
 ${curItem.getItem_name()} :${curItem.getQuantity()}<br>
 </c:forEach>
 Total Cost: ${FinalOrder.getTotal()}
 
</body>