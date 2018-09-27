<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="col" id="admin-main">
<table class="table table-striped table-hover" id="admin-catalogue">
	<thead>
		<tr>
			<th>Image</th>
			<th>Model</th>
			<th>Category</th>
			<th>Consciousness</th>
			<th>Units in stock</th>
			<th>Price</th>
			<th>Actions</th>
		</tr>
	</thead>
	<c:forEach items="${products}" var="product">
		<tr>
			<td><img
				src="${pageContext.request.contextPath}/static/images/${product.model}.jpg"
				alt="image" style="width: 150px; height: auto" /></td>
			<td>${product.model}</td>
			<td>${product.category.catType}</td>
			<td>${product.cons.level}</td>
			<td>${product.unitsInStock}</td>
			<td>${product.price} USD</td>
			<td><a href="<spring:url value="/admin/editProduct/${product.productId}" />">
				<input type="submit" class="buttons" value="<spring:message text="Edit"/>"/></a>
				 <a><input type="hidden"name="id" value="${product.productId}" />
				</a>
				<a href="<spring:url value="/admin/deleteProduct/${product.productId}" />">
				<input type="submit" class="buttons" value="<spring:message text="Delete"/>" /> 
				<input type="hidden"name="id" value="${product.productId}" />
				</a>
				</td>
		</tr>
	</c:forEach>
</table>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>  
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	var table = $('#admin-catalogue').DataTable( {
		"searching": false,
		"bLengthChange": false,
	    "pageLength": 5	
	});
	
});
</script>    
</body>
</html>