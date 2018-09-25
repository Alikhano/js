<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<br>
<div class="container">
<form name="modelSearch" id = "modelSearch" action="${pageContext.request.contextPath}/searchModel" method="post">
      <input name="model" id="model" type="search" placeholder="Search"  aria-label="Search">
      <button class="btn btn-success" type="submit">Search by model</button>
    </form>
</div>
<br>
<div class="container">

	<form id="searchForm"
		action="${pageContext.request.contextPath}/searchProduct">

		<select id="categorySearch" name="categorySearch">
			<option selected value="any">any</option>
			<c:forEach var="category" items="${categoryDTOList}">
				<option value="${category.catType}">
					<c:out value="${category.catType}" />
				</option>
			</c:forEach>

		</select> <select id="consLevelSearch" name="consLevelSearch">
			<option selected value="any">any</option>
			<c:forEach var="cons" items="${consDTOList}">
				<option value="${cons.level}">
					<c:out value="${cons.level}" />
				</option>
			</c:forEach>
		</select> <input id="fromPrice" name="fromPrice" type="text" placeholder="0.0" />

		<input id="toPrice" name="toPrice" type="text" placeholder="0.0" /> <br>

		<input type="submit" value="Search by params" class="btn btn-success">

	</form>

	<br>
	<div class="container">
		<table id="searchTable" class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Model</th>
					<th>Category</th>
					<th>Consciousness</th>
					<th>Description</th>
					<th>Units in stock</th>
					<th>Price</th>
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
<script type="text/javascript">
	$(document).ready(function() {

		$("#searchForm").validate({
			rules : {
				"fromPrice" : {
					number : true,
					min: 0

				},
				"toPrice" : {
					number: true,
					min: 50
				}

			},
			messages : {
				"fromPrice" : {
					number : "value shoudl be a number",
					min: "should be at least 0 usd"

				},
				"toPrice" : {
					number : "value should be a number",
					min: "should be at least 50 usd"
				
				}
			}
		});
		$("#searchForm").submit(function(e) {
			e.preventDefault();

			var search = {
				"category" : $("#categorySearch").val(),
				"consLevel" : $("#consLevelSearch").val(),
				"fromPrice" : $("#fromPrice").val(),
				"toPrice" : $("#toPrice").val()

			}

			$.ajax({
				type : 'POST',
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				data : JSON.stringify(search),
				url : '${pageContext.request.contextPath}/searchProduct',
				success : function(data) {
					var product = data;
					if (!$.isArray(data) || !data.length) {
						alert("Oops, we found nothing. Try again!");
					} else {
						append_json(product);
					}
				}
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
				tr.innerHTML = '<td>' + object.model + '</td>' + '<td>'
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