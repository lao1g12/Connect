<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
</head>
<body>
	<sf:form method="post" action="doAddUser" modelAttribute="user">
		First Name <sf:input required="1" type="text" path="profile.firstName" />
		<br /> 
		Last Name <sf:input required="1" type="text" path="profile.lastName" />
		<br /> 
		Stream <sf:select path="profile.stream">
			<sf:option value="Software Development">Software Development</sf:option>
			<sf:option value="Software Testing">Software Testing</sf:option>
			<sf:option value="Business Intelligence"> Business Intelligence</sf:option>
			<sf:option value="IT Service Management">IT Service Management</sf:option>
			<sf:option value="Business Analysis">Business Analysis</sf:option>
			<sf:option value="Project Support Office">Project Support Office</sf:option>
			<sf:option value="Risk, Regulation and Compliance">Risk, Regulation and Compliance</sf:option>
		</sf:select>
		Training Start date (dd/MM/yyyy) <sf:input path="profile.startDate" />
		Training End date (dd/MM/yyyy) <sf:input path="profile.endDate" />
		Username <sf:input required="1" type="text" path="username" />
		Password <sf:input required="1" type="text" path="password" />
		Confirm Password <sf:input required="1" type="text" path="confirmPassword" />
		Current Job <sf:select path="job">
			<sf:option value="Trainee">Trainee</sf:option>
			<sf:option value="Consultant">Consultant</sf:option>
			<sf:option value="Trainer"> Trainer</sf:option>
			<sf:option value="Account Manager">Account Manager</sf:option>
			<sf:option value="Recruiter">Recruiter</sf:option>
			<sf:option value="Management">Management</sf:option>
			<sf:option value="IT">IT</sf:option>
			<sf:option value="Alumni">Alumni</sf:option>
		</sf:select>
		Role <sf:select path="role">
			<sf:option value="User">User</sf:option>
			<sf:option value="Admin">Admin</sf:option>
		</sf:select>
		<input type="submit" value="Add User" />
	</sf:form>
	${passwordErrorMessage}${userErrorMessage}

</body>
</html>