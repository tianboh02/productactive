<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/HomePage.css" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

<div>
	<nav class="navbar navbar-expand-sm navbar-light bg-light">
		<div>
			<a class="navbar-brand"> ProductActive </a>
		</div>
		<ul class="navbar-nav col-lg-9">
			<li><a href="<%=request.getContextPath()%>/UserServlet/dashboard"
			class="nav-link">Home</a></li>
			<li><a href="<%=request.getContextPath()%>/UserServlet/dashboard"
			class="nav-link">Note Pad</a></li>
			<li><a href="<%=request.getContextPath()%>/UserServlet/dashboard"
			class="nav-link">Deadlines</a></li>
			<li><a href="<%=request.getContextPath()%>/UserServlet/dashboard"
			class="nav-link">Activity Logger</a></li>
		</ul>
		<ul class="navbar-nav col-lg-9">
			<li><a href="<%=request.getContextPath()%>/UserServlet/dashboard"
			class="nav-link">Account</a></li>
			<li><a href="<%=request.getContextPath()%>/UserServlet/dashboard"
			class="nav-link">Logout</a></li>
		</ul>
	</nav>
<div class="container col-md-8">
<div class="container-home">
<p class="abu">hello</p>
<p class="abu1">i dont know what is going on</p>
</div>
</div>
</div>

</body>
</html>