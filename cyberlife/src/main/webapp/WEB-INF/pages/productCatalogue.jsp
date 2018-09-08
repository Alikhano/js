<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Photo Thumb</th>
			<th>Product Name</th>
			<th>Category</th>
			<th>Consciousness</th>
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
			<td>${product.price} USD</td>
			<td><a href="<spring:url value="/viewProduct/${product.productId}" />">
				<input type="submit" class="buttons" value="<spring:message text="View"/>"/></a>
				 <a><input type="hidden"name="id" value="${product.productId}" />
				</a>
				</td>
		</tr>
	</c:forEach>
</table>



<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>   
</body>
</html>