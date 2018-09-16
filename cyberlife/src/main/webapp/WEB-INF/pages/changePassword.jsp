<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-wrapper">
<form
	action="${pageContext.request.contextPath}/myAccount/changePassword"
	method="post" >
	
		<div class="form-group">
			<label for="oldPassword">Please enter your current password:</label>
			<input type="password" name="oldPassword" id="oldPassword" class="form-Control" />
			<span style="color: #ff0000">${mismatchMsg}</span>
		</div>
		<div class="form-group">
			<label for="newPassword">Please enter new password</label>
			<input type="password" name="newPassword" id="newPassword" class="form-Control" />
		</div>
		<br /> <br /> <input type="submit" value="Change" class="btn btn-default"> 
		
		<a href="<c:url value="/myAccount" />" class="btn btn-default">Cancel</a>


</form>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>