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
					<li class="horizl"><a href="account">Account</a></li>
					<li id="right" class="horizl"><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div> 
		</br> </br>
<h2>Enter your Education to your profile below</h2>
<br>

	<sf:form method="post" action="doAddEducation" modelAttribute="education">
		What was the institution? : <sf:input type="text" path="institution"/><br>
		What grades or qualifications did you achieve? : <sf:input type="text" path="qualification"/><br>
		Is there any further interesting information to add? : <sf:input type="text" path="furtherInfo"/><br>
		Start date (dd/MM/yyyy) <sf:input path="startDate" /><br>
		End date (dd/MM/yyyy) <sf:input path="endDate" /><br>
		<input type="submit" value="Add Education to your profile"/><br>
	</sf:form>
	${dateFormatErrorMessage}
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