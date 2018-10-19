<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="side-content">
<div class="product-main">
	<div class="col" id="admin-main">
		<div class="container">
		<span style="color: #ff0000">${error}</span>
			<form:form
				action="${pageContext.request.contextPath}/admin/addProduct"
				method="post" modelAttribute="newProductDTO"
				enctype="multipart/form-data" id="addProduct">

				<form:input type="hidden" path="productId" id="productId" />

				<div class="container">
					<div class="form-group">
						<label for="model"><spring:message code="label.model"/></label>
						<form:input path="model" id="model" class="form-Control"/>
						<span style="color: #ff0000">${repModel}</span>
					</div>

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
					</div>

					<div class="form-group">
						<label for="cons"><spring:message code="label.description"/></label>
						<form:textarea rows="4" cols="50" path="description"
							id="description" class="form-Control"/>
					</div>

					<div class="form-group">
						<label for="price"><spring:message code="label.price"/></label>
						<form:errors path="price" cssClass="error" />
						<form:input path="price" id="price" class="form-Control" />
					</div>


					<div class="form-group">
						<label for="unitsInStock"><spring:message code="label.unitsInStock"/></label>
						<form:errors path="unitsInStock" cssClass="error" />
						<form:input path="unitsInStock" id="unitsInStock"
							class="form-Control" />
					</div>

					<div class="form-group">
						<label class="control-label" for="file"><spring:message code="label.uploadImage"/></label>
						<input id="file" name="file" type="file" class="form:input-large" />
					</div>

					<input type="submit" value="<spring:message code="label.submit"/>"
						class="btn btn-success"> <a
						href="<c:url value="/admin/productList" />" class="btn btn-danger"><spring:message code="label.cancel"/></a>
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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
	
/* 	function exception()
	{
	    $.ajax({
	    	 headers: {
	             'Content-Type': 'application/json',
	             'Accept': 'application/json'
	         },
	         method: "GET",
	         url: "/admin/addProduct",
	         data: JSON.stringify(data),
	     }).done(function (response) {
	         swal("Good job!", successMessage, "success");
	     }).fail(function (qXHR, textStatus, errorThrown) {
	         var messageError = JSON.parse(qXHR.responseText);
	         swal("Oops..", messageError, "error");
	     });
	    
	    return false;
	} */
</script>
</body>
</html>