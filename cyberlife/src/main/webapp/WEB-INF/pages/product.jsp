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
<title>Product CRUD</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/product"
		method="post" modelAttribute="product">

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

		<input type="submit" class="buttons" name="Submit" value="Add product" />

	</form:form>

	<br>

	<table>
		<thead>
			<tr>
				<th align="center" valign="middle" width="100">Model</th>
				<th align="center" valign="middle" width="100">Category</th>
				<th align="center" valign="middle" width="100">Description</th>
				<th align="center" valign="middle" width="100">Units in stock</th>
				<th align="center" valign="middle" width="100">Price</th>
			</tr>
		</thead>


		<c:forEach items="${listProduct}" var="product">
			<td align="center" valign="middle">${product.model}</td>
			<td align="center" valign="middle">${product.category}</td>
			<td align="center" valign="middle">${product.description}</td>
			<td align="center" valign="middle">${product.unitsInStock}</td>
			<td align="center" valign="middle">${product.price} USD</td>
			<td>
				<a href="<spring:url value="product/edit/${product.id}" />">
				<input type="submit" class="buttons" value="<spring:message text="Edit"/>"/></a>
				<a href="<spring:url value="/product/delete/${product.id}" />">
				<input type="submit" class="buttons" value="<spring:message text="Delete"/>" /> <input type="hidden"name="id" value="${course.id}" />
				</a>
			</td>
		</c:forEach>


	</table>

</body>
</html>