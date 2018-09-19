<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-wrapper">
<form:form
	action="${pageContext.request.contextPath}/admin/editProduct"
	method="post" modelAttribute="product">
	
	 <form:input type="hidden" path="productId" id="productId"/>
	  <form:input type="hidden" path="model" id="model"/>

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
			<form:textarea rows="4" cols="50" path="description" id="description"
				class="form-Control" />
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



		<br /> <br /> <input type="submit" value="Submit" class="btn btn-default"> 
		
		<a href="<c:url value="/productList" />" class="btn btn-default">Cancel</a>
	</div>

</form:form>

<div class="container">
</div>
</div>

<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>