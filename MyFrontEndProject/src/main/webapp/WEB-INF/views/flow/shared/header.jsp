<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<spring:url var="images" value="/rs/images" />
<spring:url var="css" value="/rs/css" />
<spring:url var="js" value="/rs/js" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Online shopping website">
<meta name="author" content="Debabrato Ghosh">

<title>${title}</title>

<script>
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">
<!-- Datatable CSS -->
<link href="${css}/jquery.dataTables.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${css}/myStyle.css" rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="nav navbar-nav ">
				<li class="nav-item active"><a class="nav-link"
					href="${contextRoot}/home">Home </a></li>
				</ul>
		</div>
	</div>
</nav>