<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cyberlife</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> 
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link href="<c:url value="/static/css/main.css" />" rel="stylesheet">


</head>
<body>
<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark navbar-fixed-top">
  <a class="navbar-brand" href="<c:url value="/" />">Cyberlife</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="<c:url value="/catalogue" />">Catalogue </a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<c:url value="/searchProduct" />">Search </a>
      </li>
      <li class="nav-item">
       <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a class="nav-link" href="<c:url value="/admin/admin-home" />">Admin home</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
            <a class="nav-link" href="<c:url value="/myAccount" />">My Account</a>
         </sec:authorize>
      </li>
      <li class="nav-item">
       <sec:authorize access="!hasRole('ROLE_ADMIN')">
         <a  class="nav-link" href="<c:url value="/myCart" />">My Cart</a>
        </sec:authorize>   
      </li>
      <li class="nav-item">
       <sec:authorize access="hasRole('ROLE_USER')">
            <a  class="nav-link" href="<c:url value="/orderHistory" />">My Orders</a>
         </sec:authorize>
      </li>
      <li class="nav-item">
         <sec:authorize access="isAuthenticated()">
            <a  class="nav-link" href="<c:url value="/logout" />">Logout</a>
          </sec:authorize> 
          <sec:authorize access="!isAuthenticated()">
             <a class="nav-link" href="<c:url value="/login" />">Login</a>
          </sec:authorize> 
      </li>
    </ul>
    
  </div>
</nav>