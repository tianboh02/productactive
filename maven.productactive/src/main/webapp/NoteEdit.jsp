
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
<link rel="stylesheet" 
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
crossorigin="anonymous">
<title>Insert title here</title>
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
			<li><a href="<%=request.getContextPath()%>/NotepadManagement/dashboard"
			class="nav-link">Note Pad</a></li>
			<li><a href="<%=request.getContextPath()%>/DeadlineServlet/dashboard"
			class="nav-link">Deadlines</a></li>
			<li><a href="<%=request.getContextPath()%>/ActivityLoggerServlet/dashboard"
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
			<li><a href="<%=request.getContextPath()%>/AccountServlet/userPage"
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
				<c:if test="${note != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${note == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${note != null}">
							Edit Note
						</c:if>
						<c:if test="${note == null}">
							Add New Note
						</c:if>
					</h2>
				</caption>
				<c:if test="${note != null}">
					<input type="hidden" name="oriId" value="<c:out value='${note.id}' />" />
				</c:if>
				<fieldset class="form-group">
					<input type="hidden" value="<c:out value='${note.id}' />" class="form-control" name="id">
				</fieldset>
				<fieldset class="form-group">
					<input type="hidden" value="<c:out value='${note.userid}' />" class="form-control" name="userid">
				</fieldset>
				<fieldset class="form-group">
					<label>Note Title</label> <input type="text" value="<c:out value='${note.title}' />" class="form-control" name="title">
				</fieldset>
				<fieldset class="form-group">
					<label>Note Content</label> <input type="text" value="<c:out value='${note.content}' />" class="form-control" name="content">
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>