<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container">

	<form:form method="POST" modelAttribute="customerForm"
		class="form-signin" id="createProfile">
		<h2 class="form-signin-heading">Create your customer profile</h2>

		<div class="form-group">
			<form:input type="text" id="firstName" path="firstName"
				class="form-control" placeholder="first name" autofocus="true"></form:input>
			<form:errors path="firstName"></form:errors>
		</div>

		<div class="form-group">
			<form:input type="text" id="lastName" path="lastName"
				class="form-control" placeholder="last name" autofocus="true"></form:input>
			<form:errors path="lastName" cssClass="error"></form:errors>
			
		</div>
		<div class="form-group">
			<form:input type="text" id="email" path="email" class="form-control"
				placeholder="email" autofocus="true"></form:input>
			<span style="color: #ff0000">${repEmail}</span>
			<form:errors path="email" cssClass="error"></form:errors>
		</div>

		<div class="form-group">
			<form:input type="text" id="birthDate" path="birthDate"
				class="form-control" placeholder="yyyy-mm-dd"></form:input>
			<form:errors path="birthDate" cssClass="error"></form:errors>
		</div>

		<h2 class="form-signin-heading">Add address information</h2>


		<div class="form-group">
			<form:input type="text" id="street" path="address.street"
				class="form-control" placeholder="street" autofocus="true"></form:input>
			<form:errors path="street" cssClass="error"></form:errors>
		</div>

		<div class="form-group">
			<form:input type="text" id="building" path="address.building"
				class="form-control" placeholder="building" autofocus="true"></form:input>
					<form:errors path="building" cssClass="error"></form:errors>
		</div>

		<div class="form-group">
			<form:input type="text" id="flat" path="address.flat"
				class="form-control" placeholder="apt. number" autofocus="true"></form:input>
					<form:errors path="building" cssClass="error"></form:errors>
		</div>

		<div class="form-group">
			<form:input type="text" id="city" path="address.city"
				class="form-control" placeholder="city" autofocus="true"></form:input>
					<form:errors path="city" cssClass="error"></form:errors>
		</div>

		<div class="form-group">
			<form:input type="text" id="country" path="address.country"
				class="form-control" placeholder="country" autofocus="true"></form:input>
					<form:errors path="country" cssClass="error"></form:errors>
		</div>

		<div class="form-group">
			<form:input type="text" id="zipCode" path="address.zipCode"
				class="form-control" placeholder="zip code" autofocus="true"></form:input>
					<form:errors path="zipCode" cssClass="error"></form:errors>
		</div>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	</form:form>

</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script type="text/javascript">
	$("#createProfile")
			.validate(
					{
						rules : {
							firstName : {
								required : true,

							},
							lastName : {
								required : true,

							},
							email : {
								required : true,
								email : true
							},
							birthDate : {
								required : true,
							},
							"address.street" : {
								required : true,
							},
							"address.building" : {
								required : true,
							},
							"address.flat" : {
								required : true,
							},
							"address.city" : {
								required : true,
							},
							"address.country" : {
								required : true,
							},
							"address.zipCode" : {
								required : true,
								digits : true,
								minlength : 6,
								maxlength : 6

							}
						},
						messages : {
							firstName : {
								required : "Please enter your first name"

							},
							lastName : {
								required : "Please enter your last name"

							},
							email : {
								required : "Please enter your email",
								email : "Hmm... Does not look like an email to me, please try again"
							},
							birthDate : {
								required : "Please enter enter your date of birth"
							},
							"address.street" : {
								required : "Please enter the name of the street"
							},
							"address.building" : {
								required : "Please enter the building number"
							},
							"address.flat" : {
								required : "Please enter the flat number"
							},
							"address.city" : {
								required : "Please enter the name of the city"
							},
							"address.country" : {
								required : "Please enter the name of the country"
							},
							"address.zipCode" : {
								required : "Please enter zip code",
								digits : "This field can only hold digits",
								minlength : "Zipcode should contain 6 digits",
								maxlength : "Zipcode should contain 6 digits"
							}
						}
					});
</script>

</body>
</html>
</body>
</html>