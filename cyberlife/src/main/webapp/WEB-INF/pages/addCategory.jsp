<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="admin-main">
<div id="add-info"></div>
<table id="catTable" class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Available categories
			<th>
		</tr>
	</thead>
	<c:forEach items="${categories}" var="category">
		<tr>
			<th>${category.catType}</th>
		</tr>
	</c:forEach>
</table>


<form:form id="addCat" action="${pageContext.request.contextPath}/admin/addCategory"
	method="post" modelAttribute="newCategory">
	<div class="container">
		<div class="form-group">
			<label for="model">Category</label>
			<form:input path="catType" id="catType" class="form-Control" />
		</div>

		<br /> <br /> <input type="submit" value="Add" 
			class="btn btn-success">
			
			<a href="<c:url value="/productList" />" class="btn btn-danger">Cancel</a>
	</div>
	

</form:form>

</div>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 
<script type="text/javascript">
$(document).ready(function() {
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
	});
});
});
</script>

</body>
</html>