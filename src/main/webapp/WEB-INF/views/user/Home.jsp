<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/FDMconnect.css" type="text/css" />
<title>Home Page</title>
</head>
<body>
<div class="page-container">
		<div id="HeadLog" class="row">
			<div class="col col7">
				<ul class="horiz">
					<li class="horizl"><a href="goHome">Home</a></li>
					<li class="horizl"><a href="www.categories.com">Categories</a></li>
					<li class="horizl"><a href="www.suggested.com">Suggested</a></li>
					<li class="horizl"><a href="www.deals.com">Deals</a></li>
					<li class="horizl"><a href="www.shops.com">Shops</a></li>
				</ul>
			</div>
			<div class="col col5 last">
				<ul class="horiz">
					<li class="horizl"><a href="signup">Sign Up</a></li>
					<li class="horizl"><form action="j_security_check"
							method="post">
							<input id="login" name="j_username" placeholder="Enter Login" /> <input
								id="login" type="password" name="j_password" placeholder="Enter Password" />
							<input id="login" type="submit" value="Login">
						</form></li>


				</ul>
			</div>
			</div>
<h1>Welcome ${user.getUsername()}</h1>
		<c:forEach items="${allPosts}" var="aP">
			<h3>${aP.title}<br /></h3>
			${aP.bodyText}<br />
			<img src="${aP.imgUrl}">
			<a href="${aP.link}">For more info click here!</a><br />
			Category: ${aP.category}<br />
			Posted: ${aP.postDate}<br />
			Posted By: ${aP.postOwner}<br />
			<hr />
		</c:forEach>
</div>

</body>
</html>