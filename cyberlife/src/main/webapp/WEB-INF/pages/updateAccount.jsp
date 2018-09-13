<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-wrapper">
<form:form
	action="${pageContext.request.contextPath}/updateAccount"
	method="post" modelAttribute="customer">
	<input type="hidden" name="customerId" value="${customer.customerId}" />

		<div class="form-group">
			<label for="firstName">First name</label>
			<form:input path="firstName" id="firstName" class="form-Control" />
		</div>
		<div class="form-group">
			<label for="lastName">Last name</label>
			<form:input path="lastName" id="lastName" class="form-Control" />
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<form:input path="email" id="email" class="form-Control" />
		</div>
		<div class="form-group">
			<label for="birtDate">Date of birth</label>
			<form:input path="birthDate" id="birthDate" class="form-Control" />
		</div>


		<br /> <br /> <input type="submit" value="Update" class="btn btn-default"> 
		
		<a href="<c:url value="/myAccount" />" class="btn btn-default">Cancel</a>


</form:form>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>