<%@ include file="/WEB-INF/pages/common/header.jsp"%>

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

		<input type="submit" value="Search" class="btn btn-success">

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
<script type="text/javascript">
	$(document).ready(function() {
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
					if (product === null) {
						alert("Oops, we found nothing. Try again!")
					}
					append_json(product);
				}
			});
		});
	});
	function append_json(data) {
		var table = document.getElementById('searchTable');
		$('.resultRow').remove();
		/*  for(var i = 0, len = data.length; i < length; i++) {
			 var tr = document.createElement('tr');
		     tr.className = "resultRow";
		     tr.innerHTML = '<td>' + data[i].model + '</td>' +
		     '<td>' + data[i].category + '</td>' +
		     '<td>' + data[i].cons + '</td>' +
		     '<td>' + data[i].description + '</td>' +
		     '<td>' + data[i].unitsInStock + '</td>'  +
		     '<td>' + data[i].price + " USD"+ '</td>';
		     table.appendChild(tr);
		
		 } */
		$.each(data, function(index, object) {
			var tr = document.createElement('tr');
			tr.className = "resultRow";
			for (var x in object) {
				tr.innerHTML = '<td>' + object.model + '</td>' + '<td>'
				+ object.category + '</td>' + '<td>' + object.cons
				+ '</td>' + '<td>' + object.description + '</td>' + '<td>'
				+ object.unitsInStock + '</td>' + '<td>' + object.price
				+ " USD" + '</td>';
			}
			
			table.appendChild(tr);
		})
	};
</script>
</body>
</html>