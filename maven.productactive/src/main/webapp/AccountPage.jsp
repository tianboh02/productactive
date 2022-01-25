<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Account Page</title>
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
					<li><a
						href="<%=request.getContextPath()%>/DeadlineServlet/dashboard"
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
					<li><a
						href="<%=request.getContextPath()%>/AccountServlet/userPage"
						class="nav-link">Account</a></li>
					<li><a href="<%=request.getContextPath()%>/HomeServlet/logout"
						class="nav-link">Logout</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
	<c:forEach var="userDetails" items="${userDetails}">
		<a class="btn btn-success"
			href="edit?id=<c:out value='${userDetails.id}' />">Edit User</a>&nbsp;&nbsp; <br><br>
		<a class="btn btn-danger"
			href="delete?id=<c:out value='${userDetails.id}' />">Delete User</a>
	</c:forEach>
</body>
</html>