<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add New Post</title>
</head>
<body>
<h1> Add New Post </h1>

<sf:form method="post" action="admin/addPost" modelAttribute="post">

Title : <sf:input type="text" path="title" /> <br> 


BodyText: <sf:input type="text" path="bodyText" /> <br> 
Link: <sf:input type="text" path="link" /> <br>
Category: <sf:input type="text" path="category" />  <br>
Image : <sf:input> </sf:input>
<button type="submit" value="Submit Post"/>
</sf:form>

</body>
</html>