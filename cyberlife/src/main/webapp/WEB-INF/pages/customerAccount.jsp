<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>


<div class="container">
<h2>Your account details</h2>
	<div class="row">
		<div class="col-md-5">
			<h3 class="title mb-3">${customer.firstName} ${customer.lastName} </h3>
			<p>
				<strong>Email</strong>: ${customer.email}
			</p>
			<p>
				<strong>Address</strong>
				 ${customer.address.country}
				<br>
				${customer.address.city}
				<br>
				${customer.address.street}, ${customer.address.building}, ${customer.address.flat}
				<br>
				<a href="<spring:url value="/changeAddress" />" class="btn btn-default"><span class="glyphicon glyphicon-hand-right"></span> Edit your address</a>
			</p>
			<p>${customer.birthDate}</p>

			<p>
				<a href="<c:url value = "/" />" class="btn btn-default">Back</a>
				<a href="<spring:url value="/changePassword" />" class="btn btn-default">Change password</a>
				<input type="hidden" name="customerId" value="${customer.customerId}" />
				<a href="<spring:url value="/updateAccount/${customer.customerId}" />" class="btn btn-default">Edit account</a>
			</p>

		</div>
	</div>
</div>


<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>