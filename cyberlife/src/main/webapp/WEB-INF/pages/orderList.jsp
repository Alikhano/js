<%@ include file="/WEB-INF/pages/common/header.jsp"%>
<div class="col" id="admin-main">
	<span id="statusSpan" style="display: none">${errorOrder}</span>
	<table class="table table-striped table-hover" id="orderTable">
		<thead>
			<tr>

				<th><spring:message code="label.orderId" /></th>
				<th><spring:message code="label.orderStatus" /></th>
				<th><spring:message code="label.paymentStatus" /></th>
				<th><spring:message code="label.totalPrice" /></th>
				<th>Date</th>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<th><spring:message code="label.updateOrder" /></th>
				</sec:authorize>

			</tr>
		</thead>

		<c:forEach items="${orders}" var="order">
			<tr class="resultRow">
				<td class="orderInfo">${order.orderId}</td>
				<td class="orderInfo">${order.orderStatus}</td>
				<td class="orderInfo">${order.paymentStatus}</td>
				<td class="orderInfo">${order.orderPrice}</td>
				<td class="orderInfo">${order.orderDate}</td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<td><form id="updateOrder" action="${pageContext.request.contextPath}/admin/orderStatus">
							<input type="hidden" name="orderId" id="orderId"
								value="${order.orderId}" />
							<select id="orderStatus" name="orderStatus">
								<option value="order status"
									label="order status" />
								<option value="awaits delivery" label="awaits delivery" />
								<option value="delivered, awaits pickup" label="delivered, awaits pickup"/>
								<option value="delivered and recieved" label="delivered and recieved" />
							</select>
							<select id="paymentStatus" name="paymentStatus">
								<option
									value="payment status" label="payment status" />
								<option value="unpaid" label="unpaid" />
								<option value="paid" label="paid" />
							</select>
							<input class="update" type="submit" value="<spring:message code="label.updateOrder" />" class="btn btn-success">
							<span style="color: #ff0000">${noChangehMsg}</span>
						</form></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
</div>
<div>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a id="editOrder" href="<c:url value = "/admin/stats" />"
			class="btn btn-secondary"><spring:message
				code="label.backToAdminHome" /></a>
	</sec:authorize>
	<sec:authorize access="!hasRole('ROLE_ADMIN')">
		<a href="<c:url value = "/myAccount" />" class="btn btn-secondary"><spring:message
				code="label.backToAccount" /></a>
	</sec:authorize>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
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
	
	$(".update").click(function(e) {
		e.preventDefault();
		
		var orderId = $(this).closest('form').find('input[name=orderId]').val();

		$.ajax({
			type : 'POST',
			 headers: {
		            'Accept': 'application/json'
			 },
			data: {
				'orderId' : $(this).closest('form').find('input[name=orderId]').val(),
				'orderStatus' :  $(this).closest('form').find('select[name=orderStatus]').val(),
				'paymentStatus' :  $(this).closest('form').find('select[name=paymentStatus]').val()
			},
			url : '${pageContext.request.contextPath}/admin/orderStatus',
			async: false
		}).done(function(data) {
				var product = data;
				console.log("recieved response");
				append_json(product);
				swal({
					title : "Success",
					text : "ID of updated order:  " + orderId,
					icon : "success"
				}).then(function() {
					location.reload()
				});
		}).fail(function (qXHR, textStatus, errorThrown) {
				swal("Oops","No updates after order completion", "error");
		});
	});	
		

    function append_json(data) {
    	
	    var table = document.getElementById('orderTable');
	    $('.resultRow').remove();

	    $.each(data, function(index, object) {
	    	var tr = document.createElement('tr');
			tr.className = "resultRow";
		    console.log("displaying orders");
		    var tablePart ='<sec:authorize access="hasRole(\'ROLE_ADMIN\')"> <td><form id="updateOrder" action="${pageContext.request.contextPath}/admin/orderStatus"> <input type="hidden" name="orderId" id="orderId" value="' + object.orderId + '" /> <select id="orderStatus" name="orderStatus"> <option value="order status" label="order status" /> <option value="awaits delivery" label="awaits delivery" /> <option value="delivered, awaits pickup" label="delivered, awaits pickup"/> <option value="delivered and recieved" label="delivered and recieved" /> </select> <select id="paymentStatus" name="paymentStatus"> <option value="payment status" label="payment status" /> <option value="unpaid" label="unpaid" /> <option value="paid" label="paid" /> </select><input class="update" type="submit" value="<spring:message code="label.updateOrder" />" class="btn btn-success"> </form></td> </sec:authorize>';
			tr.innerHTML = '<td class="orderInfo">' + object.orderId +'</td>' + '<td class="orderInfo">'
					+ object.orderStatus + '</td>' + '<td class="orderInfo">' + object.paymentStatus
					+ '</td>' + '<td class="orderInfo">' + object.orderPrice + " USD" + '</td>'
					+ '<td class="orderInfo">' + object.orderDate + '</td>' + tablePart;
					
			table.appendChild(tr);
		});
	    

		
	};
});

	
</script>

</body>
</html>