<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Esito creazione prodotto</title>
</head>
<body>
	<jsp:include page="fragment/menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container text-center">
			<c:if test="${ errorOccurred }">
				<div class="alert alert-danger" role="alert">
					<h6>${ message }</h6>
				</div>
			</c:if>
		</div>
		<div class="container text-center">
			<c:if test="${ success }">
				<div class="alert alert-success" role="alert">
					<h6>${ message }</h6>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>