<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/FDMConnect.css" type="text/css" />
<title>View User Account</title>
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
		<h2>Welcome ${profile.firstName}</h2>
		<br>
		<div>

			<!-- Profile -->

			<div class="row">
				<div class="col3 ">
					<img class="profileImg" src="${profile.imageUrl}" /> <br>
				</div>
				<div class="col6 last, profileinfo">
					<br> First Name : ${profile.firstName} <br> Last Name :
					${profile.lastName} <br> Description: ${profile.description} <br>
					Hobbies: ${profile.hobbies } <br>
				</div>
			</div>


			<!--  other Information  -->

			<div class="row">
				<div class="col12">
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
					<br> <br>
					<h2>Work Experience</h2>
					<c:forEach items="${experience}" var="exp">
	Company: ${exp.company}<br>
	Role: ${edu.role}<br>
	Start Date: ${exp.getStartDateFormatted()}<br>
	End Date: ${exp.getEndDateFormatted()}<br>
	Job Description: ${exp.description}<br>
	<a href="deleteExperience?experienceId=${exp.experienceId}">Remove</a>
	
						<br>
					</c:forEach>

					<h2>FDM Training</h2>
					Stream: ${profile.stream} <br> <br> StartDate:
					${profile.getStartDateFormatted()} <br> <br> EndDate:
					${profile.getEndDateFormatted()} <br> <br>
				</div>
			</div>
			<div class="row">
				<div class="col12">
					<h2>Your Posts</h2>
					<c:forEach items="${userPosts}" var="up">
						<h3>${up.title}</h3>
						<br />
					Category: ${up.category}<br />
						<img class="boardimg" src="${up.imgUrl}">
						<br>${up.bodyText}<br />
						<a href="${up.link}">For more info click here!</a>
						<br />
						Posted: ${up.getPostDateFormatted()}<br />
						<br />
						<c:if test="${userCur == session.user}">
							<a href="goToEditPost?postId=${up.postId}">Edit Post</a>
							<br />
							<br />
							<c:if test="${postId == up.postId and editPost == 'doEdit'}">
								<sf:form method="post" action="doEditPost?postId=${up.postId}"
									modelAttribute="post">
										Title: <sf:input type="text" path="title" />
										Category: <sf:input type="text" path="category" />
										Description: <sf:input type="text" path="bodyText" />
										Image URL: <sf:input type="text" path="imgUrl" />
										Link URL: <sf:input type="text" path="link" />
									<br />
									<input type="submit" value="Edit Post" />
								</sf:form>
							</c:if>
							<c:if test="${postId == up.postId}">
								${postEditedMessage} ${postErrorMessage}
							</c:if>
							<hr />
						</c:if>
						
						           <c:if test='${userCur == session.user}'>
                                 <a href="processRemovePostUser?postId=${up.postId}">Remove Post</a>
                                   <c:if test= "${postId == up.postId}">
                                            ${postRemovedByUser }
                                                                                
                                       </c:if>
                                        </c:if>

                                                <br>
						
					</c:forEach>
				</div>
			</div>

			<br>
			<c:choose>
				<c:when test='${userCur == session.user}'>
					<a href="editProfile">Edit Profile</a>
				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>

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
</body>
</html>