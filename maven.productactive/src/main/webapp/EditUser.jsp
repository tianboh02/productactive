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
<title>Edit User Page</title>
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

	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<form action="update" method="post">
					<caption>
						<h2>Edit User Information</h2>
					</caption>
					<c:if test="${currentUser != null}">
						<input type="hidden" name="oriId"
							value="<c:out value='${currentUser.id}' />" />
					</c:if>
					<fieldset class="form-group">
						<label>Username</label> <input type="text"
							value="<c:out value='${currentUser.username}' />"
							class="form-control" name="username" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Password</label> <input type="password"
							value="<c:out value='${currentUser.password}' />"
							class="form-control" name="password"
							required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>First Name</label> <input type="text"
							value="<c:out value='${currentUser.firstName}' />"
							class="form-control" name="firstName" required="required">
					</fieldset>
					<fieldset class="form-group">
						<label>Last Name</label> <input type="text"
							value="<c:out value='${currentUser.lastName}' />"
							class="form-control" name="lastName" required="required">
					</fieldset>
					<button type="submit" class="btn btn-success">Update User Information</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>