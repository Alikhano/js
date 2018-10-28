<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<br>
<br>
<div class="container">

	<form class="justify-content-center" id="searchForm"
		action="${pageContext.request.contextPath}/searchProduct">
		<input class="search" name="model" id="model" value="model"> 
			<select class="search" id="categorySearch"
			name="categorySearch">
			<option selected value="any">any</option>
			<c:forEach var="category" items="${categoryDTOList}">
				<option value="${category.catType}">
					<c:out value="${category.catType}" />
				</option>
			</c:forEach>

		</select> <select  class="search" id="consLevelSearch" name="consLevelSearch">
			<option selected value="any">any</option>
			<c:forEach var="cons" items="${consDTOList}">
				<option value="${cons.level}">
					<c:out value="${cons.level}" />
				</option>
			</c:forEach>
		</select> <input class="search" id="fromPrice" name="fromPrice" type="text" placeholder="0.0" />

		<input class="search" id="toPrice" name="toPrice" type="text" placeholder="0.0" />

		<input type="submit" value="<spring:message code="label.search" />" class="btn btn-success">

	</form>

	<br>
	<div class="container">
		<table id="searchTable" class="table table-striped table-hover">
			<thead>
				<tr>
						<th><spring:message code="label.model" /></th>
			
				<th><spring:message code="label.category" /></th>
				<th><spring:message code="label.consciousness" /></th>
				<th><spring:message code="label.description" /></th>
				<th><spring:message code="label.stock" /></th>
				<th><spring:message code="label.price" /></th>
				</tr>
			</thead>

		</table>
	</div>
</div>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">
	
</script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#searchForm").validate({
			rules : {
				"fromPrice" : {
					number : true,
					min : 0

				},
				"toPrice" : {
					number : true,
					min : 50
				}

			},
			messages : {
				"fromPrice" : {
					number : "value should be a number",
					min : "should be at least 10 USD"

				},
				"toPrice" : {
					number : "value should be a number",
					min : "should be at least 100 USD"

				}
			}
		});
		$("#searchForm").submit(function(e) {
			e.preventDefault();

			
			var search = {
				"model" : $("#model").val(),
				"category" : $("#categorySearch").val(),
				"consLevel" : $("#consLevelSearch").val(),
				"fromPrice" : $("#fromPrice").val(),
				"toPrice" : $("#toPrice").val()

			}
			
			if (search.toPrice < 0 || search.fromPrice < 0) {
				swal("Price ranges should be over 0 USD!");
			}

			$.ajax({
				type : 'POST',
				 headers: {
			            'Content-Type': 'application/json',
			            'Accept': 'application/json'
			        },
				data : JSON.stringify(search),
				url : '${pageContext.request.contextPath}/searchProduct',
				async: false
			}).done(function(data) {
					var product = data;
					if (!$.isArray(data) || !data.length) {
						swal("Oops, we found nothing. Try again!");
					} else {
						append_json(product);
					}
				}).fail(function (qXHR, textStatus, errorThrown) {
					swal("Oops", "Check your input!", "error");
				});
			
		});
	});

	function append_json(data) {
		var table = document.getElementById('searchTable');
		$('.resultRow').remove();

		$.each(data, function(index, object) {
			var tr = document.createElement('tr');
			tr.className = "resultRow";
			for ( var x in object) {
				var id = object.productId;
				var link = "<a href='http://localhost:8080/cyberlife/viewProduct/" + object.productId + "'>" + object.model +"</a>";
				tr.innerHTML = '<td>' + link +'</td>' + '<td>'
						+ object.category + '</td>' + '<td>' + object.cons
						+ '</td>' + '<td>' + object.description + '</td>'
						+ '<td>' + object.unitsInStock + '</td>' + '<td>'
						+ object.price + " USD" + '</td>';
			}

			table.appendChild(tr);
		})
	};
</script>
</body>
</html>