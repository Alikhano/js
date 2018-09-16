<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-wrapper">
<form:form
	action="${pageContext.request.contextPath}/myAccount/changeAddress"
	method="post" modelAttribute="address">
	
	<form:input type="hidden" path="addressId" id="addressId" />
	
		<div class="form-group">
			<label for="country">Country</label>
			<form:input path="country" id="country" class="form-Control" />
		</div>
		<div class="form-group">
			<label for="city">City</label>
			<form:input path="city" id="city" class="form-Control" />
		</div>
		<div class="form-group">
			<label for="zipCode">Zip code</label>
			<form:input path="zipCode" id="zipCode" class="form-Control" />
		</div>
		<div class="form-group">
			<label for="street">Street</label>
			<form:input path="street" id="street" class="form-Control" />
		</div>
		<div class="form-group">
			<label for="building">Building</label>
			<form:input path="building" id="building" class="form-Control" />
		</div>
		<div class="form-group">
			<label for="flat">Flat</label>
			<form:input path="flat" id="flat" class="form-Control" />
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