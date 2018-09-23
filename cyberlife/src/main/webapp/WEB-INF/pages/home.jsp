<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="jumbotron bg">
	<div class="container">
		<div class="jumb-text">
			<br>
			<h1 class="display-4">Get yours today!</h1>
			<br>
			<p class="lead">Cyberlife is the #1 android manufacturer in the
				world. We are here to provide you the latest AI and robotics
				technologies.</p>
			<hr class="my-4">
			<p class="lead">We offer a wide variety of android models to suit
				your needs in every aspect of your private or business life.</p>
			<p class="lead">
				<a class="btn btn-primary btn-lg"
					href="<c:url value="/catalogue" />" role="button">Catalogue</a>
			</p>
			<br>
		</div>
	</div>
	
<form action="${pageContext.request.contextPath}/home" method="POST">
<input type="hidden" name="cartId" value="<c:out value="${cartId}"/>" />
</form>


</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	

</body>
</html>