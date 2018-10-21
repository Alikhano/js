<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/common/header.jsp"%>
<link href="<c:url value="/static/css/cardPayment.css" />"
	rel="stylesheet">

<div class="container login-main">
	<div id="card-success" class="hidden">
		<i class="fa-fa-check"></i>
		<p><spring:message code="label.paymentSuccess"/></p>
	</div>
	<div id="form-errors" class="hidden">
		<i class="fa-fa-exclamation-triangle"></i>
		<p id="card-error"><spring:message code="label.cardError"/></p>
	</div>

	<div class="container">
		<div class="row ml-2 mr-2">
			<div class="col-12 col-md-4 offset-md-4">
				<div class="card">
					<div class="card-header">
						<div class="row ml-2 mr-2">
							<h3 class="text-xs-center"><spring:message code="label.paymentDetails"/></h3>
						</div>
					</div>
					<form action="${pageContext.request.contextPath}/myOrder/cardPayment" method="POST" role="form" id="cardForm">
					<div id="image-container">
						<span class="ml-2" id="amount"><spring:message code="label.paying"/>: <strong>${total}</strong></span> 
						<span id="card-image" class="mr-2"> </span>
					</div>
					<div class="card-block">
						
							<div class="row ml-2 mr-2 mt-2">
								<div class="col-12">
									<div class="form-group">
										<label><spring:message code="label.cardNumber"/></label>
										<div class="input-group">
											<span class="input-group-addon mr-2"><span
												class="fa fa-credit-card"></span></span> <input required id="card-number" type="text"
												class="form-control" placeholder="1234 5678 1011 1112" />
										</div>
									</div>
								</div>
							</div>
							<div class="row ml-2 mr-2">
								<div class="col-7 col-md-7">
									<div class="form-group">
										<label><spring:message code="label.expirationDate"/></label>
										<div class="input-group">
											<span class="input-group-addon mr-2"><span
												class="fa fa-calendar"></span></span> <input required  id="date" type="text"
												class="form-control" placeholder="MM/YY" pattern="([0-9]{2}[/]?){2}" />
										</div>
									</div>
								</div>
								<div class="col-5 col-md-5 float-xs-right">
									<div class="form-group">
										<label><spring:message code="label.cvc"/></label>
										<div class="input-group">
											<span class="input-group-addon mr-2"><span
												class="fa fa-unlock-alt"></span></span> <input required id="cvc" type="text" 
												class="form-control" placeholder="XXX" pattern=".{3,3}" title="3 characters" />
										</div>

									</div>
								</div>
							</div>
							<div class="row ml-2 mr-2">
								<div class="col-12">
									<div class="form-group">
										<label><spring:message code="label.cardOwner"/></label>
										<div class="input-group">
											<span class="input-group-addon mr-2"><span
												class="fa fa-user-circle"></span></span> <input required type="text"
												class="form-control" placeholder="Jonh Doe" pattern="/^[a-zA-Z\s]*$/" />
										</div>
									</div>
								</div>
							</div>
					
					</div>
					<div class="card-footer">
						<div class="row">
							<div class="col-12">
								<input type="submit" value="<spring:message code="label.submit"/>" class="btn btn-success btn-lg btn-block">
							</div>
						</div>
					</div>
						</form>
				</div>
			</div>
		</div>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/cardPayment.js"></script>


</body>
</html>