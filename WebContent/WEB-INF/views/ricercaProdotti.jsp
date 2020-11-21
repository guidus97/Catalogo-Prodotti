<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca prodotti</title>
</head>
<body>
	<jsp:include page="fragment/menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container text-center">
			<h1>Ricerca prodotti</h1>
			<br>
			<form:form
				action="${ pageContext.request.contextPath }/prodotti/ricerca"
				method="POST" modelAttribute="searchProdottoForm">
				<div class="form-group">
					<form:label path="name">Nome prodotto</form:label>
					<form:input type="text" path="name" cssClass="form-control" />
				</div>
				<div class="form-group">
					<form:label path="price">Costo</form:label>
					<form:input type="text" path="price" cssClass="form-control" />
				</div>
				<div class="form-group">
					<form:button cssClass="btn btn-primary">Cerca</form:button>
				</div>
			</form:form>
			<br>
			<c:if test="${ searchError }">
				<div class="alert alert-danger" role="alert">
					<h6>${ message }</h6>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>