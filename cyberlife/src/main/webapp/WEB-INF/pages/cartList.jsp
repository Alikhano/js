<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div class="col" id="admin-main">
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Image</th>
			<th>Model</th>
			<th>Category</th>
			<th>Consciousness</th>
			<th>Description</th>
			<th>Price</th>
			<th>Actions</th>
			<th>Total Price</th>
		</tr>
	</thead>
	
	<c:forEach items="${cartItems}" var="cartItem">
	<tr>
			<td><img
				src="${pageContext.request.contextPath}/static/images/${cartItem.product.model}.jpg"
				alt="image" style="width: 150px; height: auto"/></td>
			<td>${cartItem.product.model}</td>
			<td>${cartItem.product.category.catType}</td>
			<td>${cartItem.product.cons.level}</td>
			<td>${cartItem.product.description}</td>
			<td>${cartItem.totalPrice} USD</td>
			<td>
				<a href="<spring:url value="/deleteItem/${cartItem.itemId}" />">
				<input type="submit" class="btn btn-danger" value="<spring:message text="Delete"/>" /> 
				<input type="hidden"name="id" value="${cartItem.itemId}" />
				</a>
			</td>
			<td>${cart.grandTotal} USD</td>
			
	</tr>
	</c:forEach>
</table>
</div>
<div>
             <a href="<c:url value = "/myOrder" />" class="btn btn-success">Proceed to order</a>
			 <a href="<c:url value = "/catalogue" />" class="btn btn-secondary">Back</a>
	         
</div>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>   
</body>
</html>