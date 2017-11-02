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
	<div class="row">
	<div class="col col9">
	<h2>Messages to ${username}</h2>
	</div>
	<div class="col col2 last">
	<sf:form method="post" action="messages" modelAttribute="notification">
			<input type="hidden" value="${username}" name="username"/>
			<button type="submit" value="Refresh">Refresh</button>
	</sf:form>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div>
		<c:forEach items="${conversation}" var="c">
			<div class="row col12">
			<div class="col col1">
			<h3>${c.sender.getUsername()}:</h3>
			</div>
			<div class="col col7">
			${c.body}
			</div>
			<div class="col col3 last">
			<h6>Messaged @ ${c.getNotificationDateFormatted()}</h6>
			</div>
			</div>

			


		</c:forEach>
		<br>
		<br>
	<h4>Send Message:</h4>	
	<sf:form method="post" action="sendMessage" modelAttribute="notification">

		Message: <sf:input type="text" path="body" /> <br> 
		<sf:hidden value="message" path="type"/>
		<input type="hidden" value="${username}" name="recipient"/>
		<button type="submit" value="Submit Post">Add post</button>
	</sf:form>
	</div>

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