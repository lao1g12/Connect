<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/FDMConnect.css" type="text/css" />
<title>Insert title here</title>
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
<h2>Welcome ${username} to your edit profile page!</h2>
<br>
<h3>Please enter all your information below to make your personal profile!</h3>
<br>
<h4>${message}</h4>
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
	
		<h2>Would you like to set a new password instead?</h2></br>
		<h2>${passNotMatch}</h2>
		<h2>${incorrectPass}</h2>
		<h2>${UpdatedPass}</h2>
			<form method="post" action="passwordChange">
			Enter Original password : <input required type="password" name="password"/><br>
			Enter Your new password : <input required type="password" name="newPassword"/><br>
			Please confirm your NEW password : <input required type="password" name="confNewPassword"/><br>
			<input type="submit" value="Update details"/>
		</form>
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