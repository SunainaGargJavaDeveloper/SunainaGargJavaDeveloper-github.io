<%@ include file="./include.jsp"%>

<Head><title>moreOrder</title></Head>
<body>
<H3>Order info:</H3>
Customer FirstName:${sessionScope.Order.getCustomer().getFirstName() }<br>

Customer LastName:${sessionScope.Order.getCustomer().getLastName() }<br>
Items added:<br>
<c:forEach var="curItem" items="${sessionScope.Order.getItems()}">
 item_id ${curItem.getItem_id()}  : quantity ${curItem.getQuantity()}<br>
 </c:forEach>
 <br>
 <form action="./processOrderMore" method="post">
 <table><tr><td>Item_id </td><td>Quantity </td></tr>
<tr><td><input type="text" name="item_id"></td>
<td><input type="text" name="quantity"></td>
</tr>

<tr> <td><input type="submit" value="More" name="more"></td></tr>
<tr> <td><input type="submit" value="Process Order" name="ProcessOrder"></td></tr>
</table>
 
</form>
</body>