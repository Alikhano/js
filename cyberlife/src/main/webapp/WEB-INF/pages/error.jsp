<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div class="jumbotron bg">
	<div class="container">
		<div class="jumb-text">
			<br>
			<h1 class="display-4"><spring:message code="label.oops"/></h1>
			<br>
			<p class="lead">
			 <sec:authorize access="hasRole('ROLE_ADMIN')">
				<a class="btn btn-primary btn-lg"
					href="<c:url value="/admin/admin-home" />" role="button"><spring:message code="label.backToHome"/></a>
			</sec:authorize>
					 <sec:authorize access="!hasRole('ROLE_ADMIN')">
				<a class="btn btn-primary btn-lg"
					href="<c:url value="/" />" role="button"><spring:message code="label.backToHome"/></a>
					</sec:authorize>
			</p>
			<br>
		</div>
	</div>
	</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>