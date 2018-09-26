<%@ include file="/WEB-INF/pages/common/header.jsp"%>


<div class="container-fluid">
 
	<div class="row">
		<div class="col-2 px-1 bg-dark" id="admin-sidebar">
			<div class="py-2 sticky-top">
				<div class="nav flex-column">
				<div class="sidebar-item">
				<a href="<c:url value="/admin/stats" />" class="nav-link text-white" class="nav-link text-white">Admin stats</a> 
					<a href="<c:url value="/admin/productList" />" class="nav-link text-white">Inventory list</a> 
					<a href="<c:url value="/admin/addProduct" />" class="nav-link text-white">Add product</a> 
					<a href="<c:url value="/admin/addCategory"/>" class="nav-link text-white">Add category</a>
					<a href="<c:url value="/admin/addCons"/>" class="nav-link text-white">Add AI config</a>  
					<a href="<c:url value="/admin/orderStatus"/>" class="nav-link text-white">Order management</a>
				</div>
				</div>
			</div>
		</div>
		
		
		