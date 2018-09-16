<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div class="col" id="admin-main">
<table class="table table-striped table-hover">
	<thead>
		<tr>
			
			<th>Order status</th>
			<th>Payment status</th>
			<th>Total price</th>
			<th>Update order</th>
		
		</tr>
	</thead>
	
	<c:forEach items="${orders}" var="order">
	<tr>
			<td>${order.orderStatus}</td>
			<td>${order.paymentStatus}</td>
			<td>${order.cart.grandTotal}</td>
			<td>
			<form:form action="${pageContext.request.contextPath}/orderList" method="post" modelAttribute="updatedOrder">
			<form:input type="hidden" path="orderId" id="orderId" value="${order.orderId}"/>
		        <form:select path="orderStatus">
				<form:option items="${order.orderStatus}" value="orderStatus"
					itemLabel="orderStatus" />
				<form:option value="awaits delivery"/>
				<form:option value="delivered, awaits pickup"/>
				<form:option value="delivered and recieved"/>
			</form:select>
			<form:select path="paymentStatus">
				<form:option items="${order.paymentStatus}" value="paymentStatus"
					itemLabel="paymentStatus" />
				<form:option value="unpaid"/>
				<form:option value="paid"/>
			</form:select>
			<input type="submit" value="Update order" class="btn btn-success">
			</form:form>
			</td>
			
	
			
	</tr>
	</c:forEach>
</table>
</div>
<div>
        
			 <a href="<c:url value = "/admin/admin-home" />" class="btn btn-secondary">Back to admin home</a>
	         
</div>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>   
</body>
</html>