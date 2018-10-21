<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div class="col" id="admin-main">
<span id="statusSpan" style="display:none">${errorOrder}</span>
<table class="table table-striped table-hover" id="orderTable">
	<thead>
		<tr>
			
			<th><spring:message code="label.orderId"/></th>
			<th><spring:message code="label.orderStatus"/></th>
			<th><spring:message code="label.paymentStatus"/></th>
			<th><spring:message code="label.totalPrice"/></th>
			<th>Date</th> 
			 <sec:authorize access="hasRole('ROLE_ADMIN')">
			<th><spring:message code="label.updateOrder"/></th>
			</sec:authorize>
		
		</tr>
	</thead>
	
	<c:forEach items="${orders}" var="order">
	<tr>
			<td>${order.orderId}</td>
			<td>${order.orderStatus}</td>
			<td>${order.paymentStatus}</td>
			<td>${order.orderPrice}</td>
			<td>${order.orderDate}</td>
		     <sec:authorize access="hasRole('ROLE_ADMIN')">
			<td>
			<form:form modelAttribute="updatedOrder">
			<form:input type="hidden" path="orderId" id="orderId" value="${order.orderId}"/>
		        <form:select id ="orderStatus" path="orderStatus">
				<form:option items="${order.orderStatus}" value="order status"
					itemLabel="order status" />
				<form:option value="awaits delivery"/>
				<form:option value="delivered, awaits pickup"/>
				<form:option value="delivered and recieved"/>
			</form:select>
			<form:select id = "paymentStatus" path="paymentStatus">
				<form:option items="${order.paymentStatus}" value="payment status"
					itemLabel="payment status" />
				<form:option value="unpaid"/>
				<form:option value="paid"/>
			</form:select>
				<button type="submit" id="update" class="btn btn-success"><spring:message code="label.updateOrder"/></button>
				<span style="color: #ff0000">${noChangehMsg}</span>
			</form:form>
		
			</td>
			</sec:authorize>
	</tr>
	</c:forEach>
</table>
</div>
<div>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
			 <a  id="editOrder" href="<c:url value = "/admin/stats" />" class="btn btn-secondary"><spring:message code="label.backToAdminHome"/></a>
	       </sec:authorize>
	         <sec:authorize access="!hasRole('ROLE_ADMIN')">
	        <a href="<c:url value = "/myAccount" />" class="btn btn-secondary"><spring:message code="label.backToAccount"/></a>
	        </sec:authorize>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$('#orderTable').DataTable( {
		"searching": false,
		"bLengthChange": false,
	    "pageLength": 10,
	    "order": [[ 0, "desc" ]]
	});
	
	if($('#statusSpan').text().length != 0){
		var span_Text = $('#statusSpan').text();
		swal("Oops", span_Text, "error");
		$('#statusSpan').empty();
	}
	
});
	
</script>	

</body>
</html>