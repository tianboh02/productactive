<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Notes</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/NotepadServlet" class="btn btn-success">Add New Note</a>
			</div>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>UserID</th>
						<th>Title</th>
						<th>Content</th>
					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlet response to a loop -->
				<tbody>
					<c:forEach var="note" items="${listNotes}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td>
								<c:out value="${note.id}" />
							</td>
							<td>
								<c:out value="${note.userid}" />
							</td>
							<td>
								<c:out value="${note.title}" />
							</td>
							<td>
								<c:out value="${note.content}" />
							</td>
							<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td>
							<a href="edit?id=<c:out value='${note.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
							<a href="delete?id=<c:out value='${note.id}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>