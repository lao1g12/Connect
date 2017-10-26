<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/FDMConnect.css" type="text/css" />
<title>Home Page</title>
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
					<li class="horizl"><a href="logout">Logout</a></li>
					<li class="horizl"><a href="account">Account</a></li>
				</ul>
			</div>
		</div>
		</br> </br>

		<!--  welcome User  -->
		<div class="col col12 last">
			<h1>Welcome ${user.getUsername()}</h1>
		</div>

		<!-- Notice Board -->
		<div class="col col12 last">
			<c:forEach items="${allPosts}" var="aP">
				<h3>
					${aP.title}<br />
				</h3>
			${aP.bodyText}<br />
				<img src="${aP.imgUrl}">
				<a href="${aP.link}">For more info click here!</a>
				<br />
			Category: ${aP.category}<br />
			Posted: ${aP.getPostDateFormatted()}<br />
			Posted By: ${aP.postOwner}<br />
				<br />
				<a href="goToFlagPost?postId=${aP.postId}">Flag Post</a>
				<br />
				<br />
				<c:if test="${postId == aP.postId and flagPost == 'flagged'}">
					<sf:form method="post" action="doFlagPost?postId=${aP.postId}"
						modelAttribute="flag">
							Reason for flagging: <sf:input type="text" path="flagInfo" />
						<br />
						<input type="submit" value="Send Report" />
					</sf:form>
				</c:if>
				<c:if test="${postId == aP.postId}">
				${flagErrorMessage}${flagSubmittedMessage}
				</c:if>
				<hr />
			</c:forEach>
		</div>
	</div>

</body>
</html>