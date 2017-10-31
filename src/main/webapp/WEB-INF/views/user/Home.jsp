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
					<li id="right" class="horizl"><a href="logout">Logout</a></li>

				</ul>

			</div>
		</div>

		<!--  welcome User  -->
		<div class="col col12 last">
			<h3>Welcome ${user.getUsername()}</h3>
		</div>
		<form action="searchPosts" method="get">
			Search for post: <input type="text" name="input" /> <input
				type="submit" value="Search!" />
		</form>

		<!-- Notice Board -->
		<div class="col col12 last, border">
			<div class="boardtext">
				<c:forEach items="${allPosts}" var="aP">
					<h3>${aP.title}<br />
					</h3>
				
				Category: ${aP.category}<br />
				<c:choose>
				<c:when test='${aP.imgUrl == null}'>
				</c:when>
				<c:otherwise>
				<img class="boardimg" src="${aP.imgUrl}"><br>
				</c:otherwise>
				</c:choose>
				${aP.bodyText}<br />
					<a href="${aP.link}">For more info click here!</a>
					<br />
			
			Posted: ${aP.getPostDateFormatted()}<br />
			Posted By: ${aP.postOwner.username}<br />
					<br />
					<c:if test="${viewComments != 'show'}">
						<a href="goToViewComments?postId=${aP.postId}">View Comments</a>
					</c:if>
					<br />
					<c:if test="${postId == aP.postId and viewComments == 'show' }">
						<h3>Comments</h3>
						<c:forEach items="${aP.comments}" var="c">
							${c.user.profile.firstName} ${c.user.profile.lastName} 
							<br />
							${c.getCommentDateFormatted()} 
							<br />
							<br />
							${c.commentBody} 
							<br />
							<br />
							<c:if
								test="${user.getRole() == 'Admin' or username == c.user.getUsername()}">
								<a href="doRemoveComment?commentId=${c.commentId}&postId=${aP.postId}">Remove</a>
							</c:if>
							
							<c:if test="${username == c.user.getUsername()}">
								<a href="goToEditComment?commentId=${c.commentId}&postId=${aP.postId}">Edit</a>
								<br />
								<c:if test="${postId == aP.postId and editComment == 'edit'}">
									<form method="post" action="doEditComment?commentId=${c.commentId}">
										<input type="text" name="commentBody" /> 
										<br /> 
										<input type="submit" value="Update" />
									</form>
								</c:if>
							</c:if>
							<br />
							${commentRemovedMessage}
							<br />
						</c:forEach>
						<br />
						<c:if test="${addComment != 'add'}">
							<a href="goToAddComment?postId=${aP.postId}">Add Comment</a>
						</c:if>
						<br />
						<c:if test="${postId == aP.postId and addComment == 'add'}">
							<form method="post" action="doAddComment?postId=${aP.postId}">
								<input type="text" name="commentBody" /> <br /> <input
									type="submit" value="Add Comment" />
							</form>
						</c:if>
					</c:if>
					<br />
					<a href="goToFlagPost?postId=${aP.postId}">Flag Post</a>
					<c:if test="${postId == aP.postId and flagPost == 'flagged'}">
						<sf:form method="post" action="doFlagPost?postId=${aP.postId}"
							modelAttribute="flag">
							Reason for flagging: <sf:input type="text" path="flagInfo" />
							<br />
							<input type="submit" value="Send Report" />
						</sf:form>
					</c:if>
					<br />
					<c:choose>
						<c:when test='${user.getRole() == "Admin"}'>
							<a href="processRemovePostAdmin?postId=${aP.postId}"
								onclick="return confirm('Are you sure you want to remove this post?')">Remove
								Post</a>
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>

					<br />
					<c:if test="${postId == aP.postId}">
				${flagErrorMessage}${flagSubmittedMessage}
				</c:if>
					<hr />
				</c:forEach>
			</div>
		</div>
		<div class="empty"></div>
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

</body>
</html>