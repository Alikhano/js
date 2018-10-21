<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container">
<span style="color: #ff0000">${error}</span>
	<form:form method="POST" modelAttribute="customerForm"
		class="form-signin" id="createProfile">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
			<br>
				<h2 class="form-heading"><spring:message code="label.createProfile"/></h2>
				<hr>
			</div>
		</div>
		
			<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.firstName" var="tFirstName"/>
					<form:input type="text" id="firstName" path="firstName"
				class="form-control" placeholder="${tFirstName}" autofocus="true"></form:input>
			

				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.lastName" var="tLastName"/>
					<form:input type="text" id="lastName" path="lastName"
				class="form-control" placeholder="${tLastName}" autofocus="true"></form:input>
			

				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.email" var="tEmail"/>
					<form:input type="text" id="email" path="email" class="form-control"
				placeholder="${tEmail}" autofocus="true"></form:input>
			<span style="color: #ff0000">${repEmail}</span>
		
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.birthDate" var="tBirthDate"/>
					<form:input type="text" id="birthDate" path="birthDate"
				class="form-control" placeholder="${tBirthDate}"></form:input>
			
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<h2 class="form-heading"><spring:message code="label.addAddress"/></h2>
				<hr>
			</div>
		</div>

<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.street" var="tAddress"/>
				<form:input type="text" id="street" path="address.street"
				class="form-control" placeholder="${tAddress}" autofocus="true"></form:input>
		
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.building" var="tBuilding"/>
				<form:input type="text" id="building" path="address.building"
				class="form-control" placeholder="${tBuilding}" autofocus="true"></form:input>
				

				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.flat" var="tFlat"/>
			<form:input type="text" id="flat" path="address.flat"
				class="form-control" placeholder="${tFlat}" autofocus="true"></form:input>
					

				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.city" var="tCity"/>
			<form:input type="text" id="city" path="address.city"
				class="form-control" placeholder="${tCity}" autofocus="true"></form:input>
				

				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.country" var="tCountry"/>
				<form:input type="text" id="country" path="address.country"
				class="form-control" placeholder="${tCountry}" autofocus="true"></form:input>
				
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.zipCode" var="tZip"/>
			<form:input type="text" id="zipCode" path="address.zipCode"
				class="form-control" placeholder="${tZip}" autofocus="true"></form:input>
					

				</div>
			</div>
		</div>

			<div class="row"  style="padding-top: 1rem">
		<div class="col-md-3"></div>
			<div class="col-md-6">
				<input type="submit" value="<spring:message code="label.register"/>" class="btn btn-success">
			</div>
		</div>
	
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
								min: 1,
							},
							"address.flat" : {
								required : true,
								min: 1,
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
								required : "Please enter the building number",
								min: "Check for negative values"
							},
							"address.flat" : {
								required : "Please enter the flat number",
								min: "Check for negative values"
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