<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
	integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="fragment/menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="justify-content-center">
			<h1>Login</h1>
			<br>
			<form:form modelAttribute="userLoginForm" method="POST"
				action="${ pageContext.request.contextPath }/login">
				<div class="form-group">
					<form:label path="userName">Username</form:label>
					<form:input type="text" path="userName" cssClass="form-control" />
				</div>
				<div class="form-group">
					<form:label path="password">Password</form:label>
					<form:input type="password" path="password" cssClass="form-control" />
				</div>
				<div>
					<form:button cssClass="btn btn-primary">Login</form:button>
				</div>
			</form:form>
			<br>
			<div ${ error ? '' : 'hidden' } class="alert alert-danger"
				role="alert">
				<h6>${ messaggio }</h6>
			</div>
			<div ${ error_login ? '' : 'hidden' } class="alert alert-danger"
				role="alert">
				<h6>${ messaggio_login }</h6>
			</div>
		</div>
	</div>
</body>
</html>