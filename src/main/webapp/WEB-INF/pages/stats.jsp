<%@ include file="/WEB-INF/pages/common/admin-side.jsp"%>

<div class="col" id="admin-main">
<span id="statusSpan" style="display:none">${successOrder}</span>
	<div class="row">
		<div class="col">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th><spring:message code="label.topModels" /></th>
					</tr>
				</thead>
				<c:forEach items="${topProduct}" var="product">
					<tr>
						<td>${product.model}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th><spring:message code="label.topCustomers" /></th>
					</tr>
				</thead>
				<c:forEach items="${topCustomer}" var="customer">
					<tr>
						<td>${customer.firstName} ${customer.lastName}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th><spring:message code="label.MonthlyRevenue" /></th>
					</tr>
				</thead>
					<c:forEach items="${monthlyRev}" var="entry">
					<tr>
						<td>${entry.key} - ${entry.value} USD</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col">
		<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th><spring:message code="label.weeklyRevenue" /></th>
					</tr>
				</thead>
					<tr>
					<td>${weeklyNo} ${weeklyRev}</td>
   					</tr>
			</table></div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {	
	   if($('#statusSpan').text().length != 0){
		var span_Text = $('#statusSpan').text();
		swal("Success", span_Text, "success");
		$('#statusSpan').empty();
	};
	});
	</script>
</body>
</html>