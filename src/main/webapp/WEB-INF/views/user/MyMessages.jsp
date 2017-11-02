<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
			<img class="img" src="img/fdmConnect.jpg">
		</div>
		<div id="Head" class="row">
			<div class="col12">
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
					<li class="horizl"><a href="submitPost">Add Post</a></li>
					<li class="horizl"><a href="goToMyGroups">My Groups</a></li>
					<li class="horizl"><a href="goToMyMessages">My Messages</a></li>
					<li id="right" class="horizl"><a href="logout">Logout</a></li>

				</ul>

			</div>
		</div><br><br>
		<h2>Your messages:</h2><br>
		<c:forEach items="${contacts}" var="c">
			<h4>
				<a href="messages?username=${c.username}">${c.username}</a><br />
				<a href="viewProfile?profileId=${c.profile.profileId}">View Profile</a><br />
			</h4>


		</c:forEach>


			<div class="empty"></div>

			<div class="row">
				<footer> <br>
				${postRemovedByAdmin }

				<ul class="horiz">
					<li class="horizl"><a href="www.ContactUs.com">Contact Us</a></li>
					<li class="horizl"><a href="www.Help.com">Help</a></li>
					<li class="horizl"><a href="www.language.com">Language</a></li>
					<li class="horizl"><a href="www.about.com">About</a></li>
					<li class="horizl"><a href="www.SiteMap.com">Site Map</a></li>
				</ul>
				</footer>
			</div>

</div>

</body>
</html>