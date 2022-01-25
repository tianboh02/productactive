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
	<div>
		<form action="NotepadServlet" method="post">
			Title: <input type="text" name="title" size="20"></input><br>
			Content: <input type="text" name="content" size="20"></input><br>
			<!-- Implement submit button with type = submit to perform the request when clicked -->
			<input type="submit" value="Add Notes" />
		</form>	
	</div>
</body>
</html>