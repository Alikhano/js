<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cyberlife</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link href="<c:url value="/static/css/main.css" />" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Chakra+Petch"
	rel="stylesheet" type="text/css" />


</head>
<body>
	<nav
		class="navbar navbar-expand-lg navbar navbar-dark bg-dark navbar-fixed-top"
		id="main-nav">
		<a class="navbar-brand" href="<c:url value="/" />">Cyberlife</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
				<sec:authorize
						access="!hasRole('ROLE_ADMIN')"><a id="catalogue" class="nav-link"
					href="<c:url value="/catalogue" />"><spring:message code="label.catalogue"/> </a></sec:authorize></li>
				<li class="nav-item"><a id="search" class="nav-link"
					href="<c:url value="/searchProduct" />"><spring:message code="label.search"/></a></li>
				<li class="nav-item"><sec:authorize
						access="hasRole('ROLE_ADMIN')">
						<a id="admin-home" class="nav-link" href="<c:url value="/admin/stats" />"><spring:message code="label.adminHome"/></a>
					</sec:authorize> <sec:authorize access="hasRole('ROLE_USER')">
						<a id="user-account" class="nav-link" href="<c:url value="/myAccount" />"><spring:message code="label.myAccount"/></a>
					</sec:authorize></li>
				<li class="nav-item">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<a id = "inventory" class="nav-link" href="<c:url value="/admin/productList" />"><spring:message code="label.inventoryListShort"/></a>
					</sec:authorize>
					<sec:authorize access="!hasRole('ROLE_ADMIN')">
						<a  id="my-cart" class="nav-link" href="<c:url value="/myCart" />"><spring:message code="label.myCart"/></a>
				</sec:authorize> </li>
				<li class="nav-item"><sec:authorize
						access="hasRole('ROLE_USER')">
						<a id="my-orders" class="nav-link" href="<c:url value="/orderHistory" />"><spring:message code="label.myOrders"/></a>
					</sec:authorize></li>

				<li id="logout" class="nav-item"><sec:authorize access="isAuthenticated()">
						<a class="nav-link" href="<c:url value="/logout" />"><spring:message code="label.logout"/></a>
					</sec:authorize> <sec:authorize access="!isAuthenticated()">
						<a id="login" class="nav-link" href="<c:url value="/login" />"><spring:message code="label.login"/></a>
					</sec:authorize></li>
			</ul>
			<ul class="nav navbar-nav ml-auto">
			<li class="nav-item" ><span> <a 
						href="?lang=en">En</a> | <a href="?lang=fr">Fr</a>
				</span></li>
			</ul>

		</div>
	</nav>