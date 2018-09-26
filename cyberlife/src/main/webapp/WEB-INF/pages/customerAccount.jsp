<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>


<div class="container">
<div class="account-container">
	<div class="row">
		<div class="col-md-5">
			<h3 class="title mb-3">${customer.firstName} ${customer.lastName} </h3>
			<p>
				<strong><i class="fas fa-envelope"></i></strong> ${customer.email}
			</p>
			<p>
				<strong><i class="fas fa-map-marked-alt"></i></strong>
				 ${customer.address.country} </p>
				
				<p>${customer.address.city}</p>
			
				<p>${customer.address.street}, ${customer.address.building}, ${customer.address.flat}</p>
				
				<p><a href="<spring:url value="/myAccount/changeAddress" />" class="btn btn-default"> Edit your address</a></p>
			
			<p><strong><i class="fas fa-birthday-cake"></i></strong> ${customer.birthDate}</p>

			<p>
				<a href="<c:url value = "/" />" class="btn btn-default">Back</a>
				<a href="<spring:url value="/myAccount/changePassword" />" class="btn btn-default">Change password</a>
				<input type="hidden" name="customerId" value="${customer.customerId}" />
				<a href="<spring:url value="/myAccount/updateAccount/${customer.customerId}" />" class="btn btn-default">Edit account</a>
			</p>

		</div>
	</div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>