<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-wrapper">
<div class="change-container">
	<form id="changePass"
		action="${pageContext.request.contextPath}/myAccount/changePassword"
		method="post">

		<div class="form-group">
			<label for="oldPassword"><spring:message code="label.enterCurrentPassword"/>: </label>
			<input type="password" name="oldPassword" id="oldPassword"
				class="form-Control" /> <span style="color: #ff0000">${mismatchMsg}</span>
		</div>
		<div class="form-group">
			<label for="newPassword"><spring:message code="label.enterNewPassword"/></label>:  <input
				type="password" name="newPassword" id="newPassword"
				class="form-Control" />
		</div>
		<div class="form-group">
			<label for="repeatPassword"><spring:message code="label.repeatPassword"/></label>: <input
				type="password" name="repeatPassword" id="repeatPassword"
				class="form-Control" />
		</div>
		<br /> <br /> <input id="submit-change" type="submit" value="<spring:message code="label.change"/>"
			class="btn btn-default"> <a
			 id="back-account" href="<c:url value="/myAccount" />" class="btn btn-default"><spring:message code="label.cancel"/></a>


	</form>
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
	$("#changePass").validate({
		rules : {
			oldPassword : {
				required : true

			},
			newPassword : {
				required : true
			},
			repeatPassword : {
				equalTo : "#newPassword"
			}
		},
		messages : {
			oldPassword : {
				required : "Please enter old password"
			},
			newPassword : {
				required : "Please enter new password"
			},
			repeatPassword : {
				equalTo : "Repeated new password does not match"
			}
		}
	});
</script>

</body>
</html>