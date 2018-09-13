<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="admin-main">
<table class="table table-striped table-hover">
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


<form:form action="${pageContext.request.contextPath}/admin/addCategory"
	method="post" modelAttribute="newCategory">

	<form:input type="hidden" path="catId" id="catId" />

	<div class="container">
		<div class="form-group">
			<label for="model">Category</label>
			<form:errors path="catType" cssStyle="color:#ff0000;" />
			<form:input path="catType" id="catType" class="form-Control" />
		</div>

		<br /> <br /> <input type="submit" value="Add"
			class="btn btn-success">
			
			<a href="<c:url value="/productList" />" class="btn btn-danger">Cancel</a>
	</div>
	

</form:form>

</div>

</div>
</div>

</body>
</html>