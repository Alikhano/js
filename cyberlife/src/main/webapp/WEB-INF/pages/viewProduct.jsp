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
				<${product.cons.description}
			</p>
			<p>${product.price} USD</p>

			<p>
				<a href="<c:url value = "${url}" />" class="btn btn-default">Back</a>
				<a href="#" class="btn btn-default"><span class="glyphicon glyphicon-shopping-cart"></span> Order Now</a> 
				<a href="<spring:url value="/cart" />" class="btn btn-default"><span class="glyphicon glyphicon-hand-right"></span> View Cart</a>
			</p>

		</div>
	</div>
</div>


<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>