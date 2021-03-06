<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/FDMConnect.css" type="text/css" />
<title>View Account</title>
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
		<div class="row">
			<div class="col col6">
		<h2>Welcome ${profile.firstName}</h2>
		</div>
		<br>
			<div class="col col4, message">
			<c:if test="${session.user != userCur}">
				<a href="messages?username=${userCur.username}"><button class="button button5"> Send Message</button></a>
			</c:if>
			</div>
		
		<div class="col col3 last, account">
					<c:choose>
				<c:when test='${userCur == session.user}'>
					<a href="editProfile"><button class="button button5"> Edit Profile</button></a>
				</c:when>
			</c:choose>
				</div>
				</div>
			
		<div>

			<!-- Profile -->

			<div class="row">
				<div class="col3 ">
					<img class="profileImg" src="${profile.imageUrl}" /> <br>
				</div>
				<div class="col3 last, profileinfo">
					<br> <b> First Name </b> : ${profile.firstName} 
					<br> <b> Last Name </b> : ${profile.lastName} 
					<br> <b> Description </b> : ${profile.description} <br>
					<b> Hobbies </b> : ${profile.hobbies } <br>
				</div>
				
				<div class="col4 last, profileinfo , fdmprofile">
					<h2>FDM Training</h2> 
					<b> Stream </b>  : ${profile.stream}  <br>
					<b> StartDate </b>  : ${profile.getStartDateFormatted()} 
					  <br> <b> EndDate </b>  : ${profile.getEndDateFormatted()}  <br>
					</div>
			</div>


			<!--  other Information  -->

			<div class="row">
				<div class="col8">
					<h2>Education</h2>
					<c:forEach items="${education}" var="edu">
	Institution: ${edu.institution}<br>
	Qualifications: ${edu.qualification}<br>
	Start Date: ${edu.getStartDateFormatted()}<br>
	End Date: ${edu.getEndDateFormatted()}<br>
	Further Information: ${edu.furtherInfo}<br>
						<a href="deleteEducation?educationId=${edu.educationId}">Remove</a>
						<br>
					</c:forEach>
					</div>
							<div class="col4 last">
						<h2>Work Experience</h2>
					<c:forEach items="${experience}" var="exp">
	Company: ${exp.company}<br>
	Role: ${exp.role}<br>
	Start Date: ${exp.getStartDateFormatted()}<br>
	End Date: ${exp.getEndDateFormatted()}<br>
	Job Description: ${exp.description}<br>
						<a href="deleteExperience?experienceId=${exp.experienceId}">Remove</a>
						<br>
					</c:forEach>
						</div>
						</div>
					
					<br/> <br/>
			</div>
		
			<div class="row">
				<div class="col12">
					<h2>Your Posts</h2>
					<c:forEach items="${posts}" var="p">
						<h3>${p.title}</h3>
						<br />
					Category: ${p.category}<br />
						<c:choose>
							<c:when test='${aP.imgUrl == null}'>
							</c:when>
							<c:otherwise>
								<img class="boardimg" src="${aP.imgUrl}">
								<br>
							</c:otherwise>
						</c:choose><br>
						<br>${p.bodyText}<br />
						<a href="${p.link}">For more info click here!</a>
						<br />
						Posted: ${p.getPostDateFormatted()}<br />
						<br />

						<c:if test="${userCur == session.user}">
							<a href="goToEditPost?postId=${p.postId}">Edit Post</a>
							<br />
							<c:if test="${postId == p.postId and editPost == 'doEdit'}">
								<form method="post" action="doEditPost?postId=${p.postId}">
									Title: <input type="text" name="title" value="${p.title}" /> <br />
									Category: <input type="text" name="category"
										value="${p.category}" /> <br /> Description: <input type="text"
										name="bodyText" value="${p.bodyText}" /> <br /> Image URL: <input
										type="text" name="imgUrl" value="${p.imgUrl}" /> <br /> Link URL: <input
										type="text" name="link" value="${p.link}" /> <br /> <input
										type="submit" value="Edit Post" />
								</form>
							</c:if>
							<c:if test="${postId == p.postId}">
								${postEditedMessage}${postErrorMessage}
							</c:if>
						</c:if>
						
						<!-- Comments -->
						
						<c:if test="${viewComments != 'show'}">
							<a href="goToViewComments?postId=${p.postId}">View Comments</a>
						</c:if>
						<br />
					    <!-- Remove Post -->
						
						<c:if test='${userCur == session.user}'>
							<a href="processRemovePostUser?postId=${p.postId}"
								onclick="return confirm('Are you sure you want to remove this post?')">Remove
								Post</a>
							<c:if test="${postId == p.postId}">
                 	           ${postRemovedByUser }                                       
                            </c:if>
						</c:if>
						<hr />
					</c:forEach>
				</div>
			</div>

			<br>


			<br> <br>

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
</body>
</html>