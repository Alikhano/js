<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="col" id="admin-main">
	<span id="statusSpan" style="display: none">${statusOnDelete}</span>
	<table class="table table-striped table-hover" id="admin-catalogue">
		<thead>
			<tr>
				<th><spring:message code="label.photoThumb" /></th>
				<th><spring:message code="label.model" /></th>
				<th><spring:message code="label.category" /></th>
				<th><spring:message code="label.consciousness" /></th>
				<th><spring:message code="label.unitsInStock" /></th>
				<th><spring:message code="label.price" /></th>
				<th><spring:message code="label.actions" /></th>
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
				</a></td>
				<td>${product.category.catType}</td>
				<td>${product.cons.level}</td>
				<td>${product.unitsInStock}</td>
				<td>${product.price}USD</td>
				<td><a
					href="<spring:url value="/admin/editProduct/${product.productId}" />">
						<input type="submit" class="buttons"
						value="<spring:message text="Edit"/>" />
				</a> <a><input type="hidden" name="id" value="${product.productId}" />
				</a> <a
					href="<spring:url value="/admin/deleteProduct/${product.productId}" />">
						<input type="submit" class="buttons"
						value="<spring:message text="Delete"/>" /> <input type="hidden"
						name="id" value="${product.productId}" /> <input type="hidden"
						name="model" value="${product.model}" /> <input type="hidden"
						name="status" value="${product.model}" />
				</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		var table = $('#admin-catalogue').DataTable({
			"searching" : false,
			"bLengthChange" : false,
			"pageLength" : 5
		});

		if ($('#statusSpan').text() != '') {
			var span_Text = $('#statusSpan').text();
			console.log(span_Text);
			swal("Oops", span_Text, "error");
			$('#statusSpan').empty();
		}

	}

	);
</script>
</body>
</html>