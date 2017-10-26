<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Welcome ${username} to your edit profile page!</h2>
<h3>Please enter all your information below to make your personal profile!</h3>
	<sf:form method="post" action="doUpdateProfile" modelAttribute="profile"> <br>
		<a href="addEducation">Click here to add to your education</a> <br>
		<a href="addExperience">Click here to add to your work experience</a> <br>
		Add a picture : <sf:input type="text" path="imageUrl" value="${imageUrl}"/> <br>
		Edit your personal profile : <sf:input type="text" path="description" value="${description}"/> <br>
		What are your hobbies? : <sf:input type="text" path="hobbies" value="${hobbies}"/> <br>
		<input type="submit" value="Update Profile!"/> <br>
		<sf:hidden path="profileId" value="${profileId}"/> <br>
		<sf:hidden path="firstName" value="${firstName}"/> <br>
		<sf:hidden path="lastName" value="${lastName}"/> <br>
		<sf:hidden path="Stream" value="${stream}"/> <br>
		<sf:hidden path="startDate" value="${startDate}"/> <br>
		<sf:hidden path="endDate" value="${endDate}"/> <br>
	</sf:form>



</body>
</html>