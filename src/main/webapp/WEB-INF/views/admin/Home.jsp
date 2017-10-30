<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/FDMConnect.css" type="text/css" />
<title>Admin Portal</title>
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
					<li class="horizl"><a href="admin/goHome">Home</a></li>
					<c:choose>
						<c:when test='${user.getRole() == "Admin"}'>
							<li class="horizl"><a href="admin/goToAdmin">Admin</a></li>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
					<li class="horizl"><a href="admin/viewAllUsers">All Users</a></li>
					<li class="horizl"><a href="admin/logout">Logout</a></li>
					<li class="horizl"><a href="admin/account">Account</a></li>
				</ul>
			</div>
		</div>
		</br> </br>
		<div id="Head" class="row">
			<div class="col col4">
				<form method="get" action="admin/goToAddUser">
    				<button type="submit">Add User</button>
				</form>
			</div>
			<div class="col col4">
				<form method="get" action="admin/viewAllUsers">
    				<button type="submit">View all Users</button>
				</form>
			</div>
			<div class="col col4 last">
				<form method="get" action="admin/viewAllFlaggedPosts">
    				<button type="submit">View all Flagged Posts</button>
				</form>
			</div>
		</div>
		<form method="post" action="admin/addBadWords">
			Enter Your new badwords : <input required type="text" name="badWords"
				value="${flag.flagInfo}" /><br> <input type="submit"
				value="Update details" />
		</form>
		${userAddedMessage} <br>
		<br>
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