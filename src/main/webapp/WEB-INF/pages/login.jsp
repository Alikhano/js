<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container login-main">

	<form method="POST" action="${pageContext.request.contextPath}/login"
		class="form-horizontal">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<h2 class="form-heading"><spring:message code="label.login"/></h2>
				<span>${error}</span> 
				<span>${message}</span> 
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
						<div class="input-group-addon" style="width: 2.6rem">
							<em class="fa fa-at"></em>
						</div>
				
						<input id ="username" name="username" type="text"
							class="form-control" placeholder="<spring:message code="label.username"/>" />
					</div>

				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="form-group">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
						<div class="input-group-addon" style="width: 2.6rem">
							<em class="fa fa-key"></em>
						</div>
						<input id="password" name="password" type="password" class="form-control"
							placeholder="<spring:message code="label.password"/>" /> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

					</div>
				</div>
			</div>
		</div>
		<div class="row"  style="padding-top: 1rem">
		<div class="col-md-3"></div>
			<div class="col-md-6">
			<button id="login-button" class="btn btn-success" type="submit"><em class="fa fa-sign-in"></em><spring:message code="label.login"/></button>
				<a class="btn btn-link" href="${pageContext.request.contextPath}/registration"><spring:message code="label.createProfile"/></a>
			</div>
		</div>

	</form>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>