<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Post</title>
</head>
<body>
<a href="addPost">Add New Post</a>
<h1> Add New Post </h1>

<form action="addPost"></form>
Title : <input type="text" name=title> <br> 

BodyText: <input type="text" name=bodyText> <br> 

Link: <input type="text" name=link> <br>

Category: <input type="text" name=category> <br>

<input type="submit" value="Submit Post"/>
</form>
</body>
</html>