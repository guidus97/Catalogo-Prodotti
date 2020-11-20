<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loggato</title>
</head>
<body>
	<jsp:include page="fragment/menu.jsp"></jsp:include>
	<div class="jumbotron">
		<h1>Benvenuto ${ sessionScope.loginSessionAttribute.userName }</h1>
	</div>
	
</body>
</html>