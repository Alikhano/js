<%@ include file="/WEB-INF/pages/common/header.jsp"%>
<link href="<c:url value="/static/css/order.css" />" rel="stylesheet">

<div class="row">
	<div class="col-75">
		<div class="container">

			<div class="row">
				<div class="col-50">
					<h3><spring:message code="label.shippingAddress"/></h3>
					<label for="name"><em class="fa fa-user"></em> <strong><spring:message code="label.yourName"/></strong> </label> 
					 <p>${customer.firstName}  ${customer.lastName}</p>
					<!-- display name -->
					<label for="email"><em class="fa fa-envelope"></em> <strong><spring:message code="label.email"/></strong></label>
					<p>${customer.email}</p>
					<!-- display email -->
					<label for="address"><em class="fas fa-address-card"></em>
						<strong>Address</strong></label> <label for="city"> <strong><spring:message code="label.city"/></strong> </label> <p>${customer.address.city}</p>

					<div class="row">
						<div class="col-50">
							<label for="country"><strong><spring:message code="label.country"/></strong></label> ${customer.address.country}
						</div>
						<div class="col-50">
							<label for="zip"><strong><spring:message code="label.zipCode"/></strong></label> ${customer.address.zipCode}
						</div>
					</div>
				</div>

				<div class="col-50">
					<h3>Payment</h3>
					<div class="form-group">
						<label for="paymentType"><spring:message code="label.choosePay"/></label>
						<form:form action="${pageContext.request.contextPath}/myOrder"
							method="post" modelAttribute="newOrder">
							<form:input type="hidden" path="orderId" id="orderId" />
							<form:select path="paymentType">
								<form:option value="cash"><spring:message code="label.cash"/></form:option>
								<form:option value="credit card"><spring:message code="label.card"/></form:option>
							</form:select>
							<input type="submit" value="Submit order" class="btn btn-success">
							<span style="color: #ff0000">${error}</span>
						</form:form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="col-25">
		<div class="container">
			<h4>
				Cart <span class="price" style="color: black"> <em
					class="fa fa-shopping-cart"></em> 
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
				Total <span class="price" style="color: black"><strong>
						${cart.grandTotal} USD </strong></span>
			</p>

		</div>
	</div>


</div>





<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>