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
		method="post" modelAttribute="newCons">

		<form:input type="hidden" path="consId" id="consId"/>

		<div class="container">
			<div class="form-group">
				<label for="model">Level</label>
				<form:errors path="level" cssStyle="color:#ff0000;" />
				<form:input path="level" id="level" class="form-Control" />
		</div>
		
		<div class="form-group">
								<label for="description">Description</label>
								<form:textarea rows="4" cols="50" path="description"
									id="description" class="form-Control" />
							</div>

			<br /> <br /> <input type="submit" value="Submit" class="btn btn-default">
			<a href="<c:url value="/productList" />" class="btn btn-default">Cancel</a>
		</div>

	</form:form>
	
	</div>
	</div>
	</div>

</body>
</html>
