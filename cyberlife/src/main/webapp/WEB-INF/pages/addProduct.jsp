<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="side-content">
<div class="product-main">
	<div class="col" id="admin-main">
		<div class="container">
			<form:form
				action="${pageContext.request.contextPath}/admin/addProduct"
				method="post" modelAttribute="newProductDTO"
				enctype="multipart/form-data" id="addProduct">

				<form:input type="hidden" path="productId" id="productId" />

				<div class="container">
					<div class="form-group">
						<form:errors path="model" cssStyle="color:#ff0000;" />
						<form:input path="model" id="model" class="form-Control" placeholder="Model"/>
						<span style="color: #ff0000">${repModel}</span>
					</div>

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
						
						<form:textarea rows="4" cols="50" path="description"
							id="description" class="form-Control" placeholder="Description"/>
					</div>

					<div class="form-group">
						<label for="price">Price</label>
						<form:input path="price" id="price" class="form-Control" />
					</div>


					<div class="form-group">
						<label for="unitsInStock">Units In Stock</label>
						<form:input path="unitsInStock" id="unitsInStock"
							class="form-Control" />
					</div>

					<div class="form-group">
						<label class="control-label" for="file">Upload model image</label>
						<input id="file" name="file" type="file" class="form:input-large" />
					</div>

					<input type="submit" value="Add product"
						class="btn btn-success"> <a
						href="<c:url value="/admin/productList" />" class="btn btn-danger">Cancel</a>
				</div>

			</form:form>
		</div>
	</div>
	</div>
</div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script type="text/javascript">
	$("#addProduct").validate({
		rules : {
			"model" : {
				required : true,

			},
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
				number : true,
				min : 1
			},
			"unitsInStock" : {
				required : true,
				digits : true,
				min : 1
			},
			"file" : {
				required : true,
				extension : "png|jpg"
			}
		},
		messages : {
			"model" : {
				required : "Please enter the model name"

			},
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
				required : "Please enter the price",
				number : "Entered value should be a number",
				min : "Cannot be zero"
			},
			"unitsInStock" : {
				required : "Please enter the number of units in stock",
				digits : "Entered value should contain only digits",
				min : "Cannot be zero"
			},
			"file" : {
				required : "Please add image",
				extension : "Uploaded file should be an image"
			}
		}
	});
</script>
</body>
</html>