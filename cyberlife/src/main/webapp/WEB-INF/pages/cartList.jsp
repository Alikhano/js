<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div class="col" id="admin-main">
<span id="statusSpan" style="display: none">${status}</span>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th><spring:message code="label.image"/></th>
			<th><spring:message code="label.model"/></th>
			<th><spring:message code="label.category"/></th>
			<th><spring:message code="label.consciousness"/></th>
			<th><spring:message code="label.description"/></th>
			<th><spring:message code="label.price"/></th>
			<th><spring:message code="label.actions"/></th>
		</tr>
	</thead>
	
	<c:forEach items="${cartItems}" var="cartItem">
	<tr>
	
			<td><img
				src="${pageContext.request.contextPath}/static/images/${cartItem.product.model}.jpg"
				alt="image" style="width: 150px; height: auto"/></td>
			<td>${cartItem.product.model}</td>
			<td>${cartItem.product.category.catType}</td>
			<td>${cartItem.product.cons.level}</td>
			<td>${cartItem.product.description}</td>
			<td>${cartItem.totalPrice} USD</td>
			<td>
				<a href="<spring:url value="/deleteItem/${cartItem.itemId}" />">
				<input type="submit" class="btn btn-danger" value="<spring:message text="Delete"/>" /> 
				<input type="hidden"name="id" value="${cartItem.itemId}" />
				</a>
			</td>
			</tr>
	</c:forEach>
	
</table>

<p><strong><spring:message code="label.totalPrice"/>: ${cart.grandTotal} USD</strong></p>
</div>
<div>
             <a href="<c:url value = "/myOrder" />" class="btn btn-success"><spring:message code="label.proceedToOrder"/></a>
			 <a href="<c:url value = "/catalogue" />" class="btn btn-secondary"><spring:message code="label.back"/></a>
	         
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>  
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	if ($('#statusSpan').text() != '') {
		var span_Text = $('#statusSpan').text();
		console.log(span_Text);
		swal("Oops", span_Text, "error");
		$('#statusSpan').empty();
	}

}

);
</script> 
</body>
</html>