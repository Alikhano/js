<%@ include file="/WEB-INF/pages/common/header.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-2 px-1 bg-dark" id="admin-sidebar">
			<div class="py-2 sticky-top">
				<div class="nav flex-column">
				<div class="sidebar-item">
				<a href="<c:url value="/admin-home" />" class="nav-link text-white">Admin Home</a> 
					<a href="<c:url value="/productList" />" class="nav-link text-white">Inventory list</a> 
					<a href="<c:url value="/addProduct" />" class="nav-link text-white">Add product</a> 
					<a href="#" class="nav-link text-white">Edit characteristics</a> 
					<a href="#" class="nav-link text-white">Order management</a>
				</div>
				</div>
			</div>
		</div>
		<div class="col" id="admin-main">
			<h1>Admin home</h1>
		</div>
	</div>
</div>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>