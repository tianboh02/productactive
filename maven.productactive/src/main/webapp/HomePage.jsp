<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" crossorigin="anonymous"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/HomePage.css" crossorigin="anonymous">
<title>Home</title>
</head>
<body>

<div>
	<nav class="navbar navbar-expand-sm navbar-light bg-light">
		<div>
			<a class="navbar-brand"> ProductActive </a>
		</div>
		<ul class="navbar-nav col-lg-9">
			<li><a href="<%=request.getContextPath()%>/HomePage.jsp"
			class="nav-link">Home</a></li>
			<li><a href="<%=request.getContextPath()%>/NotepadPage.jsp"
			class="nav-link">Note Pad</a></li>
			<li><a href="<%=request.getContextPath()%>/DeadlineServlet"
			class="nav-link">Deadlines</a></li>
			<li><a href="<%=request.getContextPath()%>/ActivityLoggerServlet/dashboard"
			class="nav-link">Activity Logger</a></li>
		</ul>
		<ul class="navbar-nav col-lg-9">
		<li><a href="<%=request.getContextPath()%>/Register.jsp"
			class="nav-link">Register</a></li>
			<li><a href="<%=request.getContextPath()%>/Login.jsp"
			class="nav-link">Login</a></li>
			<li><a href="<%=request.getContextPath()%>/Account.jsp"
			class="nav-link">Account</a></li>
			<li><a href="<%=request.getContextPath()%>/HomePage.jsp"
			class="nav-link">Logout</a></li>
		</ul>
	</nav>
</div>
	
<div class="container col-md-8">
	<div class="container-home">
		<p class="home-intro1"> Have you heard of ProductActive? ProductActive is a productivity website that helps students or working adults keep track of time better. As this is still a developmental website, many more features will be coming your way!</p>
		<p class="home-intro2">What are you waiting for? Join us now!</p>
		
		<div class="card">
		  <img src="https://content.thriveglobal.com/wp-content/uploads/2019/09/The-True-Secret-to-Increasing-Your-Productivity.jpeg?w=1550" alt="Denim Jeans" style="width:100%">
		  <p><button>Register now</button></p>
		</div>
		
		<div class="card">
		  <img src="https://wallpaperaccess.com/full/3734602.jpg" alt="Denim Jeans" style="width:100%">
		  <p><button>Login</button></p>
		</div>
		
	</div>
</div>

<div class="container col-md-5">
	<div class="container-home">
		<div class="row">
			<div class="col-md-6">
            <div class="square-service-block">
               <a href="<%=request.getContextPath()%>/NotepadPage.jsp">
                 <h2 class="ssb-title">Note Pad</h2>  
               </a>
            </div>
          </div>
          
          <div class="col-md-6">
            <div class="square-service-block">
               <a href="<%=request.getContextPath()%>/DeadlineServlet">
                 <h2 class="ssb-title">Deadlines</h2>  
               </a>
            </div>
          </div>
          
          <div class="col-md-6">
            <div class="square-service-block">
               <a href="<%=request.getContextPath()%>/ActivityLoggerServlet/dashboard">
                 <h2 class="ssb-title">Activity Logger</h2>  
               </a>
            </div>
          </div>
          
          <div class="col-md-6">
            <div class="square-service-block">
               <a href="">
                 <h2 class="ssb-title">Coming soon...</h2>  
               </a>
            </div>
          </div>
		</div>
	</div>
</div>

</body>
</html>