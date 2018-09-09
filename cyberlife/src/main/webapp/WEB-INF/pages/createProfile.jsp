<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container">

	<form:form method="POST" modelAttribute="customerForm"
		class="form-signin">
		<h2 class="form-signin-heading">Create your customer profile</h2>

		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:input type="text" path="firstName" class="form-control"
				placeholder="firstName" autofocus="true"></form:input>
			<form:errors path="firstName"></form:errors>
		</div>

		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:input type="text" path="lastName" class="form-control"
				placeholder="lastName" autofocus="true"></form:input>
			<form:errors path="lastName"></form:errors>
		</div>
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:input type="text" path="email" class="form-control"
				placeholder="email" autofocus="true"></form:input>
			<form:errors path="email"></form:errors>
		</div>

		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:input type="birthDate" path="birthDate" class="form-control"
				placeholder="birthDate"></form:input>
			<form:errors path="birthDate"></form:errors>
		</div>

		<h2 class="form-signin-heading">Add address information</h2>

		<div class="form-group">
			<label for="street">Street Name</label>
			<form:input path="address.street" id="street" class="form-Control" />
		</div>

		<div class="form-group">
			<label for="flatr">Apartment Number</label>
			<form:input path="address.flat" id="flat" class="form-Control" />
		</div>

		<div class="form-group">
			<label for="city">City</label>
			<form:input path="address.city" id="city" class="form-Control" />
		</div>

		<div class="form-group">
			<label for="city">Building</label>
			<form:input path="address.building" id="building"
				class="form-Control" />
		</div>

		<div class="form-group">
			<label for="country">Country</label>
			<form:input path="address.country" id="country" class="form-Control" />
		</div>

		<div class="form-group">
			<label for="zipCode">Zipcode</label>
			<form:input path="address.zipCode" id="zipCode" class="form-Control" />
		</div>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	</form:form>

</div>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
</body>
</html>