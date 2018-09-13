<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>


<div class="container">
	<div class="row">
		<div class="col-md-5">
			<img src="${pageContext.request.contextPath}/static/images/${product.model}.jpg"
				alt="image" style="width: 50%" />
		</div>
		<div class="col-md-5">
			<h3 class="title mb-3">${product.model}</h3>
			<p>${product.description}</p>
			<p>
				<strong>Category</strong>: ${product.category.catType}
			</p>
			<p>
				<strong>Consciousness</strong>: ${product.cons.level}
				<br>
				${product.cons.description}
			</p>
			<p>${product.price} USD</p>

			<p>
			<form:form action="${pageContext.request.contextPath}/viewProduct" method="post" modelAttribute="newCartItem">
			 <form:input path="itemId" type="hidden" name="itemId" value=""/>
			 <%-- <form:input path="productId" type="hidden" name="productId" value="${product.productId}"/> --%>
			 <input type='hidden' id='productId' name='productId' value='${product.productId}'/>
			 <form:input path="quantity" id="quantity" class="form-Control"/>
			 <input type="submit" value="Add to cart" class="btn btn-success">
	         </form:form>
				<a href="<c:url value = "/catalogue" />" class="btn btn-secondary">Back</a>
			</p>

		</div>
	</div>
</div>


<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>