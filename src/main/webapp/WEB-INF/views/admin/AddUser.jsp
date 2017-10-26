<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/FDMConnect.css" type="text/css" />
<title>Add User</title>
</head>
<body>
<!-- Top Menu --> 

	<div class="page-container">
	<div id="logodiv">
	<img class="img" src="img/fdmConnect.jpg" >
	</div>
		<div id="Head" class="row">
			<div class ="col12">
				<ul class="horiz">
					<li class="horizl"><a href="goHome">Home</a></li>
					<c:choose>
						<c:when test='${user.getRole() == "Admin"}'>
							<li class="horizl"><a href="goToAdmin">Admin</a></li>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
					<li class="horizl"><a href="viewAllUsers">All Users</a></li>
					<li class="horizl"><a href="logout">Logout</a></li>
					<li class="horizl"><a href="account">Account</a></li>
				</ul>
			</div>
		</div> 
		</br> </br>
		<h2>Add a user below</h2>
		<br>
	<sf:form method="post" action="doAddUser" modelAttribute="user">
		First Name <sf:input required="1" type="text" path="profile.firstName" />
		<br> 
		Last Name <sf:input required="1" type="text" path="profile.lastName" />
		<br> 
		Stream <sf:select path="profile.stream">
			<sf:option value="Software Development">Software Development</sf:option>
			<sf:option value="Software Testing">Software Testing</sf:option>
			<sf:option value="Business Intelligence"> Business Intelligence</sf:option>
			<sf:option value="IT Service Management">IT Service Management</sf:option>
			<sf:option value="Business Analysis">Business Analysis</sf:option>
			<sf:option value="Project Support Office">Project Support Office</sf:option>
			<sf:option value="Risk, Regulation and Compliance">Risk, Regulation and Compliance</sf:option>
		</sf:select><br>
		Training Start date (dd/MM/yyyy) <sf:input path="profile.startDate" /><br>
		Training End date (dd/MM/yyyy) <sf:input path="profile.endDate" /><br>
		Username <sf:input required="1" type="text" path="username" /><br>
		Password <sf:password required="1" path="password" /><br>
		Confirm Password <sf:password required="1" path="confirmPassword" /><br>
		Current Job <sf:select path="job">
			<sf:option value="Trainee">Trainee</sf:option>
			<sf:option value="Consultant">Consultant</sf:option>
			<sf:option value="Trainer"> Trainer</sf:option>
			<sf:option value="Account Manager">Account Manager</sf:option>
			<sf:option value="Recruiter">Recruiter</sf:option>
			<sf:option value="Management">Management</sf:option>
			<sf:option value="IT">IT</sf:option>
			<sf:option value="Alumni">Alumni</sf:option>
		</sf:select><br>
		Role <sf:select path="role">
			<sf:option value="User">User</sf:option>
			<sf:option value="Admin">Admin</sf:option>
		</sf:select><br>
		<input type="submit" value="Add User" />
	</sf:form>
	${passwordErrorMessage}${userErrorMessage}
	<br><br>
			<footer>
			<ul class="horiz">
				<li class="horizl"><a href="www.ContactUs.com">Contact Us</a></li>
				<li class="horizl"><a href="www.Help.com">Help</a></li>
				<li class="horizl"><a href="www.language.com">Language</a></li>
				<li class="horizl"><a href="www.about.com">About</a></li>
				<li class="horizl"><a href="www.SiteMap.com">Site Map</a></li>
			</ul>
		</footer>
	</div>
</body>
</html>