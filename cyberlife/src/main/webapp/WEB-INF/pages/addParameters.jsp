<%@ include file="/WEB-INF/pages/common/header.jsp" %>


	<form:form action="${pageContext.request.contextPath}/addParameters"
		method="post" modelAttribute="newCategory">

		<form:input type="hidden" path="catId" id="catId"/>

		<div class="reg-container">
			<div class="form-group">
				<label for="model">Category</label>
				<form:errors path="model" cssStyle="color:#ff0000;" />
				<form:input path="model" id="model" class="form-Control" />
			</div>

			<br /> <br /> <input type="submit" value="Submit" class="btn btn-default">
		</div>

	</form:form>

	<a href="<c:url value="/productList" />" class="btn btn-default">Cancel</a>

</body>
</html>