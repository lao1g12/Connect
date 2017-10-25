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
<h2>Enter your Work Experience to your profile below</h2>

	<sf:form method="post" action="doAddExperience" modelAttribute="experience">
		What was the company? : <sf:input type="text" path="company"/>
		What was the role? : <sf:input type="text" path="role" />
		Write a short description about your experience : <sf:input type="text" path="description" />
		Start date (dd/MM/yyyy) <sf:input path="startDate" />
		End date (dd/MM/yyyy) <sf:input path="endDate" />
		<input type="submit" value="Add Work Experience to your profile"/>
	</sf:form>

</body>
</html>