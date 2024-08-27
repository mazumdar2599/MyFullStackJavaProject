<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
	<%@ include file="./shared/nav.jsp"%>

	<div class="wrapper">
		<div class="content">
			<!-- Home Content -->
			<div class="container">
				<c:if test="${userclickhome == true}">
					<%@ include file="home.jsp"%>
				</c:if>
				<c:if test="${userclickaboutus == true}">
					<%@ include file="about.jsp"%>
				</c:if>
				<c:if test="${userclickcontactus == true}">
					<%@ include file="contact.jsp"%>
				</c:if>
				<c:if test="${userclicksingleproduct == true}">
					<%@ include file="viewproduct.jsp"%>
				</c:if>
				<c:if
					test="${userclickallproducts == true || userclickcategoryproducts==true}">
					<%@ include file="product.jsp"%>
				</c:if>
				<c:if test="${userclickmanageproduct == true}">
					<%@ include file="manageproduct.jsp"%>
				</c:if>
				<c:if test="${userclickregister == true}">
					<%@ include file="register.jsp"%>
				</c:if>
				<c:if test="${userclickshowcart == true}">
					<%@ include file="cart.jsp"%>
				</c:if>
			</div>
		</div>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>
	</div>
	<!-- JQuery core JavaScript -->
	<script src="${js}/jquery.min.js"></script>

	<!-- Bootstrap core JavaScript -->
	<script src="${js}/bootstrap.min.js"></script>
	<!-- Datatable JavaScript -->
	<script src="${js}/dataTables.bootstrap.js"></script>
	<script src="${js}/jquery.dataTables.js"></script>
	<script src="${js}/bootbox.min.js"></script>
	<!-- Custom JavaScript -->
	<script src="${js}/myscript.js"></script>

</body>

</html>
