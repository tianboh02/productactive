<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Registration Page</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-expand-sm navbar-light bg-light">
			<div>
				<a class="navbar-brand"> ProductActive </a>
			</div>
			<ul class="navbar-nav col-lg-9">
				<li><a href="<%=request.getContextPath()%>/HomeServlet"
					class="nav-link">Home</a></li>
				<c:if test="${userid != null}">
					<li><a
						href="<%=request.getContextPath()%>/NotepadManagement/dashboard"
						class="nav-link">Note Pad</a></li>
					<li><a href="<%=request.getContextPath()%>/DeadlineServlet"
						class="nav-link">Deadlines</a></li>
					<li><a
						href="<%=request.getContextPath()%>/ActivityLoggerServlet/dashboard"
						class="nav-link">Activity Logger</a></li>
				</c:if>
			</ul>
			<ul class="navbar-nav col-lg-9">
				<c:if test="${userid == null}">
					<li><a href="<%=request.getContextPath()%>/RegisterServlet"
						class="nav-link">Register</a></li>
					<li><a href="<%=request.getContextPath()%>/LoginServlet"
						class="nav-link">Login</a></li>
				</c:if>
				<c:if test="${userid != null}">
					<li><a href="<%=request.getContextPath()%>/Account.jsp"
						class="nav-link">Account</a></li>
					<li><a href="<%=request.getContextPath()%>/HomePage.jsp"
						class="nav-link">Logout</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
	<!-- Create a form with the action attribute to specific where to send the form-data when 
the form is submitted, method attribute to specific the method used (GET, POST, PUT, DELETE, 
Etc.) -->
	<form action="RegisterServlet" method="post">
		Username: <input type="text" name="username" size="32"></input><br>
		Password: <input type="password" name="password" size="32"></input><br>
		First Name: <input type="text" name="firstName" size="32"></input><br>
		Last Name: <input type="text" name="lastName" size="32"></input><br>
		<!-- Implement submit button with type = submit to perform the request when clicked -->
		<input type="submit" value="Register Account" />
	</form>
</body>
</html>