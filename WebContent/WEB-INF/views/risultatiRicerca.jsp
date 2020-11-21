<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Risultati</title>
</head>
<body>
	<jsp:include page="fragment/menu.jsp"></jsp:include>
	<div class="jumbotron jumbotron-fluid">
		
			<div class="container text-center">
				<h1>Risultati ricerca</h1>
			</div>
			<br>
			<br>
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Nome</th>
						<th scope="col">Costo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="res" items="${ resultList }">
						<tr>
							<td>${ res.id }</td>
							<td>${ res.name }</td>
							<td>${ res.price  }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</body>
</html>