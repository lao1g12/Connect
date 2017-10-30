<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<h2> Flags for ${post.postId}</h2>

<table>
			<tr>
				<th>Flag flagId</th>
				<th>Flag flagInfo</th>
				<th>Flag dateAdded</th>
				<th>Flag flaggedPost</th>
			
			
			</tr>
			<c:forEach items="${flagList}" var="f">
				<tr>

					<th>${f.flagId}</th>
					<th>${f.flagInfo}</th>
					<th>${f.flagDateFormatted}</th>
					<th>${post.postId}</th>
	
	               <th><a href="processRemovePost?postId=${post.postId}"
	               		  onclick="return confirm('Are you sure you want to remove this post?')">Remove Post</a></th>

					
				</tr>
			</c:forEach>
		</table>
${message }

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