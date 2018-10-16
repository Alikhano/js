<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="side-content">
<div class="cons-main">
<span style="color: #ff0000">${error}</span>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Available AI configurations</th>
			<th>Description</th>
		</tr>
	</thead>
	<c:forEach items="${consLevels}" var="cons">
		<tr>
			<td>${cons.level}</td>
			<td><div style='width: 700px'>${cons.description}</div></td>
		</tr>
	</c:forEach>
</table>


	<form:form action="${pageContext.request.contextPath}/admin/addCons"
		method="post" modelAttribute="newCons" id="addCons">


		<div class="container">
			<div class="form-group">
				<form:input path="level" id="level" class="form-Control" placeholder="Level" />
					<form:errors path="level" cssClass="error"></form:errors>
		</div>
		
		<div class="form-group">

								<form:textarea rows="4" cols="50" path="description"
									id="description" class="form-Control" placeholder="Description" />
										<form:errors path="level" cssClass="error"></form:errors>
							</div>

		<input type="submit" value="Add" class="btn btn-success">
			<a href="<c:url value="/productList" />" class="btn btn-danger">Cancel</a>
		</div>

	</form:form>
	
	</div>
	</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#addCons").submit(function(e) {
		 e.preventDefault();

	$.ajax({
		method: 'POST',
		headers: { 
	        'Content-Type': 'application/json' ,
	        'Accept': 'application/json',
        },
		url:'${pageContext.request.contextPath}/admin/addCons',
		data: JSON.stringify({
			level: $("#level").val(),
			description: $("#description").val()
		}),
	}).done( function(data) {
		swal("Success", "New category: " + $("#level").val(), "success");
		})
		.fail( function(error) {
			swal("Oops!","Duplicate entry!", "error");
		});
});
});
</script>

</body>
</html>
