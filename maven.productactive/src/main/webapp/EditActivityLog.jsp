<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container col-md-6">
	<div class="card">
	<div class="card-body">
	<c:if test="${activityLog != null}">
	<form action="update" method="post">
	</c:if>
	<c:if test="${activityLog == null}">
	<form action="insert" method="post">
	</c:if>
	<caption>
	<h2>
	<c:if test="${activityLog != null}">
	Edit User
	</c:if>
	<c:if test="${activityLog == null}">
	Add New User
	</c:if>
	</h2>
	</caption>
	<c:if test="${activityLog != null}">
	<input type="hidden" name="oriId" value="<c:out value='${activityLog.id}' />" />
	<input type="hidden" name="oriUserId" value="<c:out value='${activityLog.userId}' />" />
	</c:if>
	<fieldset class="form-group">
	<label>Activity</label> <input type="text" value="<c:out value='${activityLog.activityName}' />" class="form-control" name="activityName" required="required">
	</fieldset>
	<fieldset class="form-group">
	<label>Description</label> <input type="text" value="<c:out value='${activityLog.activityDescription}' />" class="form-control" name="activityDescription">
	</fieldset>
	<fieldset class="form-group">
	<label>Start</label> <input type="datetime-local" value="<c:out value='${activityLog.startDateTime}' />" class="form-control" name="startDateTime">
	</fieldset>
	<fieldset class="form-group">
	<label> End</label> <input type="datetime-local" value="<c:out value='${activityLog.endDateTime}' />" class="form-control" name="endDateTime">
	</fieldset>
	<button type="submit" class="btn btn-success">Save</button>
	</form>
	</div>
	</div>
</div>
</body>
</html>