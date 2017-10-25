<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View User Account</title>
</head>
<h2>Welcome ${profile.firstName}</h2>
<body>
<div>
ID: ${profile.profileId} <br><br>

ImageURL: ${profile.imageUrl } <br><br>

First Name : ${profile.firstName} <br><br>
Last Name : ${profile.lastName} <br><br>

Stream: ${profile.stream} <br><br>

Description: ${profile.description} <br><br>

Hobbies: ${profile.hobbies } <br><br>

StartDate: ${profile.startDate } <br><br>

EndDate: ${profile.endDate } <br><br>
</div>

<br>
<a href="editProfile">Edit Profile</a>

</body>
</html>