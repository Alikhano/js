<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-wrapper">
<div class="change-container">
<span style="color: #ff0000">${error}</span>
	<form:form action="${pageContext.request.contextPath}/myAccount/updateAccount"
		method="post" modelAttribute="customer" id="updateProfile">
		<input type="hidden" name="customerId" value="${customer.customerId}" />

		<div class="form-group">
			<label for="firstName"><spring:message code="label.firstName" /></label>
			<form:input path="firstName" id="firstName" class="form-Control" />
				<form:errors path="firstName" cssClass="error"></form:errors>
		</div>
		<div class="form-group">
			<label for="lastName"><spring:message code="label.lastName" /></label>
			<form:input path="lastName" id="lastName" class="form-Control" />
				<form:errors path="lastName" cssClass="error"></form:errors>
		</div>
		<div class="form-group">
			<label for="email"><spring:message code="label.email" /></label>
			<form:input path="email" id="email" class="form-Control" />
			<form:errors path="email" cssClass="error"></form:errors>
		</div>
		<div class="form-group">
			<label for="birtDate"><spring:message code="label.dateOfBirth" /></label>
			<form:input path="birthDate" id="birthDate" class="form-Control" />
			<form:errors path="birthDate" cssClass="error"></form:errors>
		</div>


		<br />
		<br />
		<input type="submit" value="<spring:message code="label.changer" />" class="btn btn-default">

		<a href="<c:url value="/myAccount" />" class="btn btn-default"><spring:message code="label.cancel" /></a>


	</form:form>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script type="text/javascript">
	$("#updateProfile")
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
							}
						}
					});
</script>

</body>
</html>