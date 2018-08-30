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

<form:form action="${pageContext.request.contextPath}/product/edit"
		method="post" modelAttribute="product">
		
		<form:hidden path="id" value="${product.id}" />

		<label for="model">Model</label>
		<form:input path="model" id="model" />

		<br>

		<label for="category">Category</label>
		<form:select path="category" name="category">
			<form:option value="housekeeping">housekeeping</form:option>
			<form:option value="medical staff">medical staff</form:option>
			<form:option value="elderly care">elderly care</form:option>
		</form:select>

		<br>

		<label for="description">Description</label>
		<form:input path="description" id="description" />

		<br>

		<label for="unitsInstock">Units in stock</label>
		<form:input path="unitsInStock" id="unitsInStock" />

		<br>

		<label for="price">Price</label>
		<form:input path="price" id="price" />

		<input type="submit" class="buttons" name="Submit" value="Update product" />

</form:form>


</body>
</html>