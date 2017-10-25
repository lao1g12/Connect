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
	<div class="page-container">
		<div id="Head" class="row">
			<div class="col col7">
				<ul class="horiz">
					<li class="horizl"><a href="goHome">Home</a></li>
					<c:choose>
						<c:when test='${user.getRole() == "Admin"}'>
							<li class="horizl"><a href="goToAdmin">Admin</a></li>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="col col5 last">
				<ul class="horiz">
					<li class="horizl">
						<h4>Welcome ${username}</h4>
					</li>
					<li class="horizl"><a href="logout">Logout</a></li>
					<li class="horizl"><a href="account">Account</a></li>
			


				</ul>
			</div>
		</div> </br> </br>
		<div class="col col12 last">
			<h1>Welcome ${user.getUsername()}</h1>
		</div>
		<div class="col col12 last">
			<c:forEach items="${allPosts}" var="aP">
				<h3>${aP.title}<br />
				</h3>
			${aP.bodyText}<br />
				<img src="${aP.imgUrl}">
				<a href="${aP.link}">For more info click here!</a>
				<br />
			Category: ${aP.category}<br />
			Posted: ${aP.postDate}<br />
			Posted By: ${aP.postOwner}<br />
				<hr />
			</c:forEach>
		</div>
	</div>

</body>
</html>