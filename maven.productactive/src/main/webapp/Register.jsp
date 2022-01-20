<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Registration Page</title>
</head>
<body>
	<!-- Create a form with the action attribute to specific where to send the form-data when 
the form is submitted, method attribute to specific the method used (GET, POST, PUT, DELETE, 
Etc.) -->
	<form action="RegisterServlet" method="post">
		Username: <input type="text" name="username" size="20"></input><br>
		Password: <input type="password" name="password" size="20"></input><br>
		First Name: <input type="text" name="firstName" size="32"></input><br>
		Last Name: <input type="text" name="lastName" size="32"></input><br>
		<!-- Implement submit button with type = submit to perform the request when clicked -->
		<input type="submit" value="Register Account" />
	</form>
</body>
</html>