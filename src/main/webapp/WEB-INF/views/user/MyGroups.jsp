<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/FDMConnect.css" type="text/css" />
<title>MyGroup</title>
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

		<c:forEach items="${groups}" var="g">
			<h3>
				${g.name } <br />
			</h3>
	 
	 Description: ${g.description } <br />

			<img class="groupImg" src="${g.imageUrl }">
			<br />
			<br>
			<br>
			<a href="goToGroupHome?name=${g.name}">Go To Group</a>

			<br>
			<c:choose>
				<c:when test="${user.username != g.owner.username}">
					<a href="goToLeaveGroup?name=${g.name}"
					onclick="return confirm('Are you sure you want to leave this group?')">Leave Group</a>
				</c:when>
			

			</c:choose>
			<br>
			
			<c:choose>
				<c:when test='${user.username == g.owner.username}'>
					<a href="goToRemoveGroup?name=${g.name}" 
					onclick="return confirm('Are you sure you want to remove this group?')" >Remove Group</a>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</br> </br>

		<sf:form method="post" action="doCreateGroup" modelAttribute="group">
		Group Name: <sf:input type="text" path="name" />
			<br>
			<br>
			
		Group Description: <sf:input type="text" path="description" />
			<br>
			<br>
	    Group Picture<sf:input type="text" path="imageUrl" />
			<br>
			<br>
			<input type="submit" value="Create group" />
			<br>
		</sf:form>
		${groupWasCreated}${userLeftGroup}${groupAlreadyExists}${groupRemovedByOwner}
    <br>
	</div>
</body>
</html>