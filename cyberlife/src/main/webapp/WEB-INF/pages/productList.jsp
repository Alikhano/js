<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

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
		</tr>
	</thead>
	<c:forEach items="${products}" var="product">
		<tr>
			<td><img
				src="${pageContext.request.contextPath}/static/images/${product.model}.jpg"
				alt="image" style="width: 50%" /></td>
			<td>${product.model}</td>
			<td>${product.category.catType}</td>
			<td>${product.cons.level}</td>
			<td>${product.description}</td>
			<td>${product.price} USD</td>
			<td><a href="<spring:url value="/editProduct/${product.productId}" />">
				<input type="submit" class="buttons" value="<spring:message text="Edit"/>"/></a>
				 <a><input type="hidden"name="id" value="${product.productId}" />
				</a>
				<a href="<spring:url value="/deleteProduct/${product.productId}" />">
				<input type="submit" class="buttons" value="<spring:message text="Delete"/>" /> 
				<input type="hidden"name="id" value="${product.productId}" />
				</a>
				</td>
		</tr>
	</c:forEach>
</table>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>   
</body>
</html>