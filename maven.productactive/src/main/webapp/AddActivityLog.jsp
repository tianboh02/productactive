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
<div class="container col-md-6">
	<div class="card">
	<div class="card-body">

	<form action ="AddActivityLoggerServlet" method="post">
	<caption>
	<h2>
	Add New Log
	</h2>
	</caption>
	<fieldset class="form-group">
	<label>Activity</label> <input type="text" class="form-control" name="activity_name" id="activity_name" placeholder="Enter your activity name" required="required">
	</fieldset>
	<fieldset class="form-group">
	<label>Description</label> <input type="text" class="form-control" name="activity_description" id="activity_description" placeholder="Enter description" required="required">
	</fieldset>
	<fieldset class="form-group">
	<label>Start</label> <input type="datetime-local" class="form-control" name="activity_start" id="activity_start" required="required">
	</fieldset>
	<fieldset class="form-group">
	<label> End</label> <input type="datetime-local" class="form-control" name="activity_end" id="activity_end" required="required">
	</fieldset>
	<input type="submit" class="btn btn-primary" name="submit" value="Log Activity">
	</form>
	</div>
	</div>
</div>

</body>
</html>