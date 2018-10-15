<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="side-content">
<div class="category-main row">

<div class="col">
	<span style="color: #ff0000">${error}</span>
<table id="catTable" class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Available categories</th>
		</tr>
	</thead>
	<c:forEach items="${categories}" var="category">
		<tr>
			<td>${category.catType}</td>
		</tr>
	</c:forEach>
</table>
</div>

<div class="col">
<form:form id="addCat" action="${pageContext.request.contextPath}/admin/addCategory"
	method="post" modelAttribute="newCategory">
	<div class="container">
		<div class="form-group">
			<form:input path="catType" id="catType" class="form-Control" placeholder="Category" />
		</div>

		<input type="submit" value="Add" 
			class="btn btn-success">
		
			
			<a href="<c:url value="/productList" />" class="btn btn-danger">Cancel</a>
	</div>
	

</form:form>
</div>
</div>
</div>
</div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$('#catTable').DataTable( {
		"searching": false,
		"bLengthChange": false,
	    "pageLength": 5		
	});
	
	$("#addCat").submit(function(e) {
		 e.preventDefault();
	
	
	var catType = $("#catType").val();

	$.ajax({
		type: 'POST',
		url:'${pageContext.request.contextPath}/admin/addCategory',
		data: JSON.stringify({
			catType: $("#catType").val()
		}),
        contentType: 'application/json',
        success: function(data) {
			location.reload();
		},
		error: function(error) {
			location.href="error.jsp";
		}
	});
});
});

</script>

</body>
</html>