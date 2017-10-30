<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/FDMConnect.css" type="text/css" />
<title>Home Page</title>
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
					<li class="horizl"><a href="submitPost">Add Post</a><li> 

				</ul>

			</div>
		</div> 
		</br>
		 </br>
		
															<!--  welcome User  -->		
		<div class="col col12 last">
			<h3>Welcome ${user.getUsername()}</h3>
		</div>
		<form action="searchPosts" method="get">
			Search for post: <input type="text" name="input"/>
			<input type="submit" value="Search!"/>
		</form>
		
															<!-- Notice Board -->		
		<div class="col col12 last, border">
			<div class="boardtext"> 
			<c:forEach items="${allPosts}" var="aP">
				<h3>${aP.title}<br /></h3>
				
				Category: ${aP.category}<br />
				<img class="boardimg" src="${aP.imgUrl}"><br>
				${aP.bodyText}<br />
				<a href="${aP.link}">For more info click here!</a>
				<br />
			
			Posted: ${aP.getPostDateFormatted()}<br />
			Posted By: ${aP.postOwner.username}<br /> 
			<br />
			<a href="goToFlagPost?postId=${aP.postId}">Flag Post</a>
			<br>
			<c:choose>
			<c:when test='${user.getRole() == "Admin"}'>
			<a href="processRemovePostAdmin?postId=${aP.postId}">Remove  Post</a>
			</c:when>
			<c:otherwise>

			</c:otherwise>
			</c:choose>
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
		<div class="empty"> </div>
		<footer>
			<br>
			
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

</body>
</html>