<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/AddActivityLog.css" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Log new activity</title>
</head>
<body>
<div class="container col-md-5">
	<form action ="ActivityLoggerServlet" method="post">
	  <div class="form-group">
	    <label for="inputActivity">Activity</label>
	    <input type= "text" name="activity_name" class="form-control" id="activity_name" placeholder="Enter your activity name">
	  </div>
	  <div class="form-group">
	    <label for="inputDescription">Description</label>
	    <input type= "text" name="activity_description" class="form-control" id="activity_description" placeholder="Enter description">
	  </div>
	  <div class="form-group">
	    <label for="inputStart">Start</label>
	    <input type= "datetime-local" name="activity_start" class="form-control" id="activity_start" placeholder="Select your starting time">
	  </div>
	  <div class="form-group">
	    <label for="inputEnd">End</label>
	    <input type= "datetime-local" name="activity_end" class="form-control" id="activity_end" placeholder="Select your ending time">
	  </div>
	  <div class="buttonHolder">
	  	  <input type="submit" class="btn btn-primary" name="submit" value="Log Activity" style="width: 100%;">
	  </div>
	</form>
</div>

</body>
</html>