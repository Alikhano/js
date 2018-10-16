<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>
<table class="table table-striped table-hover" id="catalogue">
	<thead>
		<tr>
			<th>Photo Thumb</th>
			<th>Product Name</th>
			<th>Category</th>
			<th>Consciousness</th>
			<th>Quantity</th>
			<th>Price</th>

		</tr>
	</thead>
	<c:forEach items="${products}" var="product">
		<tr>
			<td><img
				src="${pageContext.request.contextPath}/static/images/${product.model}.jpg"
				alt="image" style="width: 150px; height: auto" /></td>
			<td><a
				href="http://localhost:8080/cyberlife/viewProduct/${product.productId}">
					<c:out value="${product.model}" />
			</a> <input type="hidden" name="id" value="${product.productId}" /></td>
			<td>${product.category.catType}</td>
			<td>${product.cons.level}</td>
			<td>${product.unitsInStock}</td>
			<td>${product.price} USD</td>

		</tr>
	</c:forEach>
</table>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#catalogue').DataTable({
			"searching" : false,
			"bLengthChange" : false,
			"pageLength" : 5
		});

	});
</script>
</body>
</html>