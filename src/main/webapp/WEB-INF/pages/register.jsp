	<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container login-main">

	<form:form id="regForm" method="POST" modelAttribute="userForm"
		class="form-signin">

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<h2 class="form-heading"><spring:message code="label.createProfile" /></h2>
				<hr>
			</div>
		</div>


		<form:input type="hidden" path="userId" id="userId" />

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.username" var="tusername" />
					<form:input id="username" type="text" path="username"
						class="form-control" placeholder="${tusername}" autofocus="true"></form:input>
					<span style="color: #ff0000">${repUsername}</span>

				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
				<spring:message code="label.password" var="tpassword"/>
					<form:input id="password" type="password" path="password"
						class="form-control" placeholder="${tpassword}"></form:input>

				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="from-group">
				<spring:message code="label.repeatPassword" var="tRepeatPassword" />
					<input type="password" id="repeatPassword" name="repeatPassword"
						class="form-control" placeholder="${tRepeatPassword }">
				</div>
			</div>
		</div>
		<div class="row"  style="padding-top: 1rem">
		<div class="col-md-3"></div>
			<div class="col-md-6">
				<input type="submit" value="<spring:message code="label.register" />" class="btn btn-success">
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
	$("#regForm").validate({
		rules : {
			username : {
				required : true,

			},
			password : {
				required : true,

			},
			password : {
				required : true,
			},
			repeatPassword : {
				equalTo : "#password"
			}
		},
		messages : {
			username : {
				required : "Please enter username"
			},

			password : {
				required : "Please enter password"
			},

			repeatPassword : {
				equalTo : "Repeated password does not match"
			}
		}
	});
</script>

</body>
</html>
</body>
</html>