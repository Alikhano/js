<%@ include file="/WEB-INF/pages/common/header.jsp"%>
<link href="<c:url value="/static/css/order.css" />" rel="stylesheet">

<div class="row">
	<div class="col-75">
		<div class="container">

			<div class="row">
				<div class="col-50">
					<h3>Shipping Address</h3>
					<label for="name"><i class="fa fa-user"></i> Your name </label> <label
						for="firstName">First Name</label> ${customer.firstName} <label
						for="lastName">Last Name</label> ${customer.lastName}
					<!-- display name -->
					<label for="email"><i class="fa fa-envelope"></i> Email</label>
					${customer.email}
					<!-- display email -->
					<label for="address"><i class="fas fa-address-card"></i>
						Address</label> <label for="city"> City</label> ${customer.address.city}

					<div class="row">
						<div class="col-50">
							<label for="country">Country</label> ${customer.address.country}
						</div>
						<div class="col-50">
							<label for="zip">Zip code</label> ${customer.address.zipCode}
						</div>
					</div>
				</div>

				<div class="col-50">
					<h3>Payment</h3>
					<div class="form-group">
						<label for="paymentType">Choose how you would like to pay</label>
						<form:form action="${pageContext.request.contextPath}/myOrder"
							method="post" modelAttribute="newOrder">
							<form:input type="hidden" path="orderId" id="orderId" />
							<form:select path="paymentType">
								<form:option value="cash">Cash on delivery</form:option>
								<form:option value="credit card">Credit card</form:option>
							</form:select>
							<input type="submit" value="Add to cart" class="btn btn-success">
							<span style="color: #ff0000">${noStockMsg}</span>
						</form:form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="col-25">
		<div class="container">
			<h4>
				Cart <span class="price" style="color: black"> <i
					class="fa fa-shopping-cart"></i> <b></b>
				</span>
				<c:forEach items="${cartItems}" var="cartItem">
					<p>
						${cartItem.product.model} <span class="price">
							${cartItem.totalPrice} USD</span>
					</p>
				</c:forEach>
			</h4>
			<!-- for each cart item -->
			<hr>
			<p>
				Total <span class="price" style="color: black"><b>
						${cart.grandTotal} USD </b></span>
			</p>

		</div>
	</div>


</div>






<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>