<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Activity Logger Dashboard</title>
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
			<li><a href="<%=request.getContextPath()%>/Account.jsp"
			class="nav-link">Account</a></li>
			<li><a href="<%=request.getContextPath()%>/HomeServlet/logout"
			class="nav-link">Logout</a></li>
			</c:if>
		</ul>
	</nav>
</div>

<div class="row"> 
<div class="container">
<h3 class="text-center">Activity Log</h3>
<hr>
<div class="container text-left">
<!-- Add new user button redirects to the register.jsp page -->
<a href="<%=request.getContextPath()%>/AddActivityLoggerServlet" class="btn btn-success">Log New Activity</a>
</div>
<br>
<!-- Create a table to list out all current users information -->
<table class="table">
<thead>
<tr>
<th>Activity</th>
<th>Description</th>
<th>Start</th>
<th>End</th>
<th>Actions</th>
</tr>
</thead>
<!-- Pass in the list of users receive via the Servlet’s response to a loop 
-->
<tbody>
<c:forEach var="log" items="${listActivityLog}">
<!-- For each user in the database, display their 
information accordingly -->
<tr>
<td>
<c:out value="${log.activityName}" />
</td>
<td>
<c:out value="${log.activityDescription}" />
</td>
<td>
<input type="datetime-local" value="<c:out value="${log.startDateTime}" />" readonly="readonly">
</td>
<td>
<input type="datetime-local" value="<c:out value="${log.endDateTime}" />" readonly="readonly">
</td>
<!-- For each user in the database, Edit/Delete 
buttons which invokes the edit/delete functions -->
<td>
<a href="edit?id=<c:out value='${log.id}' 
/>">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; 
<a href="delete?id=<c:out 
value='${log.id}' />">Delete</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</body>
</html>