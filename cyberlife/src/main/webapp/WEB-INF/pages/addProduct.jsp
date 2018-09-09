<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

		<div class="col" id="admin-main">
			<h1>Add Product</h1>
			<div class="container-wrapper">
				<div class="container">
					<form:form action="${pageContext.request.contextPath}/admin/addProduct"
						method="post" modelAttribute="newProductDTO"
						enctype="multipart/form-data">

						<form:input type="hidden" path="productId" id="productId" />

						<div class="container">
							<div class="form-group">
								<label for="model">Model</label>
								<form:errors path="model" cssStyle="color:#ff0000;" />
								<form:input path="model" id="model" class="form-Control" />
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

							<div class="form-group">
								<label class="control-label" for="file">Upload model
									image</label> <input id="file" name="file" type="file"
									class="form:input-large" />
							</div>

							<br /> <br /> 
							<input type="submit" value="Submit" class="btn btn-default"> 
							<a href="<c:url value="/productList" />" class="btn btn-default">Cancel</a>
						</div>

					</form:form>

				</div>
			</div>
		</div>
	</div>
</div>


<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>