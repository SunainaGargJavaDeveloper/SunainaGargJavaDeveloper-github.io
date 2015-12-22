<%@ include file="./include.jsp"%>
<head>

</head>
<body>
<form action="./processOrder" method="post">
<h1>Enter Customer Information</h1>
<table>
<tr><td>Customer First Name </td>
<td><input type="text" name="firstName"></td></tr>
<tr>
<tr><td>Customer Last Name </td>
<td><input type="text" name="lastName"></td></tr>
</table>
<h1>Enter Order Information</h1>
<table>
<tr><td>Item_id </td><td>Quantity </td></tr>
<tr><td><input type="text" name="item_id"></td>
<td><input type="text" name="quantity"></td>
</tr>

<tr> <td><input type="submit" value="More" name="more"></td></tr>
<tr> <td><input type="submit" value="Process Order" name="ProcessOrder"></td></tr>
</table>
</form>
</body>