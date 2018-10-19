<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-wrapper">

<span style="color: #ff0000">${error}</span>
	<form:form
		action="${pageContext.request.contextPath}/admin/editProduct"
		method="post" modelAttribute="product" id="editProduct">

		<form:input type="hidden" path="productId" id="productId" />
		<form:input type="hidden" path="model" id="model" />

		<div class="reg-container">

			<div class="form-group">
				<label for="category"><spring:message code="label.category"/></label>
				<form:select path="category.catId">
					<form:options items="${categoryDTOList}" itemValue="catId"
						itemLabel="catType" />
				</form:select>
			</div>
			<div class="form-group">
				<label for="cons"><spring:message code="label.consciousness"/></label>
				<form:select path="cons.consId">
					<form:options items="${consDTOList}" itemValue="consId"
						itemLabel="level" />
				</form:select>
				<%-- <form:errors path="cons.consId" cssClass="error"></form:errors> --%>
			</div>


			<div class="form-group">
				<label for="description"><spring:message code="label.description"/></label>
				<form:textarea rows="4" cols="50" path="description"
					id="description" class="form-Control" />
				<%-- <form:errors path="description" cssClass="error"></form:errors> --%>
			</div>

			<div class="form-group">
				<label for="price"><spring:message code="label.price"/></label>
				<form:input path="price" id="price" class="form-Control" />
				<%-- <form:errors path="price" cssClass="error" /> --%>

			</div>


			<div class="form-group">
				<label for="unitsInStock"><spring:message code="label.unitsInStock"/></label>
				<form:input path="unitsInStock" id="unitsInStock"
					class="form-Control" />
				<%-- <form:errors path="unitsInStock" cssClass="error" /> --%>
			</div>



			<br /> <br /> <input type="submit" value="<spring:message code="label.submit"/>"
				class="btn btn-default"> <a
				href="<c:url value="/admin/productList" />" class="btn btn-default"><spring:message code="label.cancel"/></a>
		</div>

	</form:form>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script type="text/javascript">
	$("#editProduct").validate({
		rules : {

			"category.catId" : {
				required : true,

			},
			"cons.consId" : {
				required : true,
			},
			"description" : {
				required : true,
			},
			"price" : {
				required : true,
			},
			"unitsInStock" : {
				required : true,
				number : true
			},

		},
		messages : {

			"category.catId" : {
				required : "Please choose category"

			},
			"cons.consId" : {
				required : "Please choose AI config",
			},
			"description" : {
				required : "Please add product description"
			},
			"price" : {
				required : "Please enter the price"
			},
			"unitsInStock" : {
				required : "Please enter the number of units in stock",
				number : "Entered value should be a number"
			}
		}
	});
</script>
</body>
</html>