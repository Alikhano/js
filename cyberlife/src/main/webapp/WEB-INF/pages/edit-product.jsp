<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Product EDIT</title>
</head>
<body>

<div class="container-wrapper">
<form:form
	action="${pageContext.request.contextPath}/editProduct"
	method="post" modelAttribute="product">
	
	 <form:input type="hidden" path="productId" id="productId"/>

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
		
		<a
			href="<c:url value="/productList" />"
			class="btn btn-default">Cancel</a>
	</div>

</form:form>

<div class="container">
</div>
</div>

</body>
</html>