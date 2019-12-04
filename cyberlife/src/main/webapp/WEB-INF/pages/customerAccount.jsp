<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>


<div class="container">
<div class="account-container">
	<div class="row">
		<div class="col-md-5">
			<h3 class="title mb-3">${customer.firstName} ${customer.lastName} </h3>
			<p>
				<strong><em class="fas fa-envelope"></em></strong> ${customer.email}
			</p>
			<p>
				<strong><em class="fas fa-map-marked-alt"></em></strong>
				 ${customer.address.country} </p>
				
				<p>${customer.address.city}</p>
			
				<p>${customer.address.street}, ${customer.address.building}, ${customer.address.flat}</p>
				
				<p><a id="edit-address" href="<spring:url value="/myAccount/changeAddress" />" class="btn btn-default"> <spring:message code="label.editAddress"/></a></p>
			
			<p><strong><em class="fas fa-birthday-cake"></em></strong> ${customer.birthDate}</p>

			<p>
				<a id="change-password" href="<spring:url value="/myAccount/changePassword" />" class="btn btn-default"><spring:message code="label.changePssword"/></a>
				<input type="hidden" name="customerId" value="${customer.customerId}" />
				<a id="edit-account" href="<spring:url value="/myAccount/updateAccount/${customer.customerId}" />" class="btn btn-default"><spring:message code="label.editAccount"/></a>
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