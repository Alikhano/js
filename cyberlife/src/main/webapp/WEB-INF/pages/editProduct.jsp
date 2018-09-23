<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-wrapper">
	<form:form
		action="${pageContext.request.contextPath}/admin/editProduct"
		method="post" modelAttribute="product" id="editProduct" >

		<form:input type="hidden" path="productId" id="productId"/>
		<form:input type="hidden" path="model" id="model" />

		<div class="reg-container">

			<div class="form-group">
				<label for="category">Category</label>
				<form:select path="category.catId">
					<form:options items="${categoryDTOList}" itemValue="catId"
						itemLabel="catType" />
				</form:select>
			</div>

			<div class="form-group">
				<label for="cons">Consciousness</label>
				<form:select path="cons.consId">
					<form:options items="${consDTOList}" itemValue="consId"
						itemLabel="level" />
				</form:select>
			</div>


			<div class="form-group">
				<label for="description">Description</label>
				<form:textarea rows="4" cols="50" path="description"
					id="description" class="form-Control" />
			</div>

			<div class="form-group">
				<label for="price">Price</label>
				<form:errors path="price" cssStyle="color:#ff0000;" />
				<form:input path="price" id="price" class="form-Control" />
			</div>


			<div class="form-group">
				<label for="unitsInStock">Units In Stock</label>
				<form:errors path="unitsInStock" cssStyle="color:#ff0000;" />
				<form:input path="unitsInStock" id="unitsInStock"
					class="form-Control" />
			</div>



			<br /> <br /> <input type="submit" value="Submit"
				class="btn btn-default"> <a
				href="<c:url value="/productList" />" class="btn btn-default">Cancel</a>
		</div>

	</form:form>

	<div class="container"></div>
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