<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="admin-main">
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Available AI configurations
			<th>
		</tr>
	</thead>
	<c:forEach items="${consLevels}" var="cons">
		<tr>
			<th>${cons.level}</th>
			<th>${cons.description}</th>
		</tr>
	</c:forEach>
</table>


	<form:form action="${pageContext.request.contextPath}/admin/addCons"
		method="post" modelAttribute="newCons" id="addCons">


		<div class="container">
			<div class="form-group">
				<label for="model">Level</label>
				<form:input path="level" id="level" class="form-Control" />
		</div>
		
		<div class="form-group">
								<label for="description">Description</label>
								<form:textarea rows="4" cols="50" path="description"
									id="description" class="form-Control" />
							</div>

			<br /> <br /> <input type="submit" value="Add" class="btn btn-success">
			<a href="<c:url value="/productList" />" class="btn btn-danger">Cancel</a>
		</div>

	</form:form>
	
	</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 
<script type="text/javascript">
$(document).ready(function() {
	$("#addCons").submit(function(e) {
		 e.preventDefault();

	$.ajax({
		type: 'POST',
		headers: { 
	        'Content-Type': 'application/json' 
	    },
		url:'${pageContext.request.contextPath}/admin/addCons',
		data: JSON.stringify({
			level: $("#level").val(),
			description: $("#description").val()
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
