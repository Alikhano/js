<%@ include file="/WEB-INF/pages/common/header.jsp"%>

	<div class="container">
	
	<form action="${pageContext.request.contextPath}/searchProduct">
	<select name = "category">
	 <option selected value="any">any</option>
	 <c:forEach var="category" items="${categoryDTOList}">
            <option value="${category.catType}">
                <c:out value="${category.catType}"/>
            </option>
        </c:forEach>
      
	</select>
	
	<select name = "consLevel">
	<option selected value="any">any</option>
	 <c:forEach var="cons" items="${consDTOList}">
            <option value="${cons.level}">
                <c:out value="${cons.level}"/>
            </option>
        </c:forEach>
	</select>

	
	<input name="fromPrice" type="text" placeholder="0.0"/>
	
	<input name="toPrice" type="text" placeholder="0.0"/>
	<br>
	
	<button id="searchButton" onclick="doSearch()" title="searchButton" class="btn btn-success">Search</button>
	
	</form>
	
	<br>
<div class="container">
<table class="table table-striped table-hover">
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
	
		<tr>
			<td id="model"></td>
			<td id="category"></td>
			<td id="cons"></td>
			<td id ="description"></td>
			<td id="unitsInStock"></td>
			<td id="price"></td>
			<%-- <td><a href="<spring:url value="/viewProduct/${product.productId}" />">
				<input type="submit" class="buttons" value="<spring:message text="View"/>"/></a>
				 <a><input type="hidden" name="id" id="productId" value="${product.productId}" />
				</a>
			</td> --%>
		</tr>

</table>
	</div>
	</div>

<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>