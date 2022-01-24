<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Deadline Management </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/DeadlineServlet"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${deadline != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${deadline == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${deadline != null}">
Edit User
</c:if>
						<c:if test="${deadline == null}">
Add New User
</c:if>
					</h2>
				</caption>
				<c:if test="${deadline != null}">
					<input type="hidden" name="id"
						value="<c:out
value='${deadline.id}' />" />
				</c:if>
				<c:if test="${deadline == null}">
					<input type="hidden" name="id"
						value=null />
				</c:if>
				<c:if test="${deadline != null}">
					<input type="hidden" name="userId"
						value="<c:out
value='${deadline.userId}' />" />
				</c:if>
				<c:if test="${deadline == null}">
					<input type="hidden" name="userId"
						value=001 />
				</c:if>
				<fieldset class="form-group">
					<label>Title</label> <input type="text"
						value="<c:out
value='${deadline.title}' />" class="form-control"
						name="title" required="required">
				</fieldset>
				
				
				<fieldset class="form-group">
					<label> Deadline</label> <input type="datetime-local"
						value="<c:out
value='${deadline.deadline}' />" class="form-control"
						name="deadline">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>