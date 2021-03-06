<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/FDMConnect.css" type="text/css" />
<title>Group Home</title>
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
		</div>
		</br> </br>

		<!--  welcome User  -->
		<div class="col col12 last">
			<h3>Welcome to ${group.name}</h3>
		</div>
		<div>

			<a href="addGroupPost?name=${group.name}"><button>Add
					Post To Group</button></a>
		</div>


		<c:choose>
			<c:when test='${addGroupPost == "hello"}'>

				<br>

				<sf:form method="post" action="groupPost" modelAttribute="post">
					<h2>${badPost}</h2>
Title : <sf:input type="text" path="title" />
					<br> 
BodyText: <sf:input type="text" path="bodyText" />
					<br> 
Link: <sf:input type="text" path="link" />
					<br>
Category: <sf:input type="text" path="category" />
					<br>
Image : <sf:input type="text" path="imgUrl" />
					<br>
					<input type="hidden" value="${group.name}" name="name" />


					<button type="submit" value="Submit Post">Add post</button>
				</sf:form>
				<br>

			</c:when>

			<c:otherwise>

			</c:otherwise>


		</c:choose>

		<h3>Group Members</h3>
		<div class="row">
			<div class="col col2">
				<c:forEach items="${group.users}" var="user">
					<a href="viewProfile?profileId=${user.profile.profileId}">${user.username}</a>
				</c:forEach>
			</div>
		</div>
		<br />
		<br />
		<div class="row">
			<div class="col col2">
				<c:if test="${user.username == group.owner.username}">
					<a href="goToSendInvite?groupName=${group.name}">Send Invite</a>
					<c:if test="${sendInvite == 'send'}">
						<form method="post" action="doSendInvite?groupName=${group.name}">
							<input type="text" name="username" /> <br /> <input
								type="submit" value="Send" />
						</form>
					</c:if>
				</c:if>
			</div>

			<br /> 
			${userErrorMessage}
		</div>
		<br /> <br />

<div>
				<div class="row">
				<div class="col col12 last, border">
					<div class="boardtext">
						<c:forEach items="${allPosts}" var="aP">
							<h3>${aP.title}<br /></h3>
				
				Category: ${aP.category}<br />
							<c:choose>
								<c:when test='${aP.imgUrl == null}'>
								</c:when>
								<c:otherwise>
									<img class="boardimg" src="${aP.imgUrl}">
									<br>
								</c:otherwise>
							</c:choose>
				${aP.bodyText}<br />
							<a href="${aP.link}">For more info click here!</a>
							<br />
			
			Posted: ${aP.getPostDateFormatted()}<br />
			Posted By: ${aP.postOwner.username}<br />
							<br />
							<c:if test="${viewComments != 'show'}">
								<a href="goToViewGroupComments?postId=${aP.postId}"><button
										class="button button4">View Comments</button></a>
							</c:if>
							<br />
							<c:if test="${postId == aP.postId and viewComments == 'show' }">
								<h3>Comments</h3>
								<c:forEach items="${comments}" var="c">
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
										<a href="doRemoveGroupComment?commentId=${c.commentId}&postId=${aP.postId}"><button
										class="button button4">Remove</button></a>
									</c:if>

									<c:if test="${username == c.user.getUsername()}">
										<a
											href="goToEditGroupComment?commentId=${c.commentId}&postId=${aP.postId}"><button
										class="button button4">Edit</button></a>
										<br />
										<c:if test="${postId == aP.postId and editComment == 'edit'}">
											<form method="post"
												action="doEditGroupComment?commentId=${c.commentId}">
												<input type="text" name="commentBody" /> <br /> <br /> <input
													type="submit" value="Update" />
											</form>
										</c:if>
									</c:if>
									<br />
									${commentRemovedMessage}
									<br />
								</c:forEach>
								<br />
								<c:if test="${addComment != 'add'}">
									<a href="goToAddGroupComment?postId=${aP.postId}"><button
										class="button button4">Add Comment</button></a>
								</c:if>
								<br />
								<c:if test="${postId == aP.postId and addComment == 'add'}">
									<h4>${badComment}</h4>
									<form method="post"
										action="doAddGroupComment?postId=${aP.postId}&name=${group.name}">
										<input type="text" name="commentBody" /> <br /> <br /> <input
											type="submit" value="Add Comment" />
									</form>
								</c:if>
							</c:if>
							<br />
							<a href="goToFlagPost?postId=${aP.postId}">Flag Post</a>
							<br>
							<c:choose>
								<c:when
									test='${user.getRole() == "Admin" or user == group.owner}'>
									<a href="processRemovePostAdmin?postId=${aP.postId}"
										onclick="return confirm('Are you sure you want to remove this post?')">Remove
										Post</a>
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
			</div>
		</div>
		<div class="empty"></div>

			${postRemovedByAdmin }
			<br>
 <br> <br>
		
		<div> 
		<br>
	<div id="Footer" class="row">
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
		</div>
		</div>
	
</body>
</html>