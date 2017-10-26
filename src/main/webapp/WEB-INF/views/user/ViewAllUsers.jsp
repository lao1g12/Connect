<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Users</title>
</head>
<body>
	<h2>FDM Connect Users</h2>
	<br />
	<br />
	<a href="goToUserSearch">Search for User</a> <br /> 
	${nullSearchMessage} <br /> <br />
	<c:if test="${userSearch == 'search'}">
		<form action="doUserSearch" method="post">
			Search by Name: <input type="text" name="profileName" /> <br /> <br /> 
			<input	type="submit" value="Search" />
		</form>
	</c:if>
	<br />
	<h3>Trainees</h3>
	<br />
	<c:forEach items="${users}" var="u">
		<c:if test="${u.job == 'Trainee'}">
			<img src="${u.profile.imageUrl}" />
			<br>
			<br>
		${u.profile.firstName} ${u.profile.lastName} <br />
		Stream: ${u.profile.stream} <br />
		Start Date: ${u.profile.getStartDateFormatted()} <br />
		End Date: ${u.profile.getEndDateFormatted()} <br />
			<br />
			<a href="viewProfile?profileId=${u.profile.profileId}">View
				Profile</a>
		</c:if>
	</c:forEach>
	<br />
	<br />
	<h3>Consultants</h3>
	<br />
	<c:forEach items="${users}" var="u">
		<c:if test="${u.job == 'Consultant'}">
			<img src="${u.profile.imageUrl}" />
			<br>
			<br>
		${u.profile.firstName} ${u.profile.lastName} <br />
		Stream: ${u.profile.stream} <br />
			<a href="viewProfile?profileId=${u.profile.profileId}">View
				Profile</a>
		</c:if>
	</c:forEach>
	<br />
	<br />
	<h3>Trainers</h3>
	<br />
	<c:forEach items="${users}" var="u">
		<c:if test="${u.job == 'Trainer'}">
			<img src="${u.profile.imageUrl}" />
			<br>
			<br>
		${u.profile.firstName} ${u.profile.lastName} <br />
			<a href="viewProfile?profileId=${u.profile.profileId}">View
				Profile</a>
		</c:if>
	</c:forEach>
	<br />
	<br />
	<h3>Account Managers</h3>
	<br />
	<c:forEach items="${users}" var="u">
		<c:if test="${u.job == 'Account Manager'}">
			<img src="${u.profile.imageUrl}" />
			<br>
			<br>
		${u.profile.firstName} ${u.profile.lastName} <br />
			<a href="viewProfile?profileId=${u.profile.profileId}">View
				Profile</a>
		</c:if>
	</c:forEach>
	<br />
	<br />
	<h3>Recruiters</h3>
	<br />
	<c:forEach items="${users}" var="u">
		<c:if test="${u.job == 'Recruiter'}">
			<img src="${u.profile.imageUrl}" />
			<br>
			<br>
		${u.profile.firstName} ${u.profile.lastName} <br />
			<a href="viewProfile?profileId=${u.profile.profileId}">View
				Profile</a>
		</c:if>
	</c:forEach>
	<br />
	<br />
	<h3>Managers</h3>
	<br />
	<c:forEach items="${users}" var="u">
		<c:if test="${u.job == 'Management'}">
			<img src="${u.profile.imageUrl}" />
			<br>
			<br>
		${u.profile.firstName} ${u.profile.lastName} <br />
			<a href="viewProfile?profileId=${u.profile.profileId}">View
				Profile</a>
		</c:if>
	</c:forEach>
	<br />
	<br />
	<h3>IT Department</h3>
	<br />
	<c:forEach items="${users}" var="u">
		<c:if test="${u.job == 'IT'}">
			<img src="${u.profile.imageUrl}" />
			<br>
			<br>
		${u.profile.firstName} ${u.profile.lastName} <br />
			<a href="viewProfile?profileId=${u.profile.profileId}">View
				Profile</a>
		</c:if>
	</c:forEach>
	<br />
	<br />
	<h3>Alumni</h3>
	<br />
	<c:forEach items="${users}" var="u">
		<c:if test="${u.job == 'Alumni'}">
			<img src="${u.profile.imageUrl}" />
			<br>
			<br>
		${u.profile.firstName} ${u.profile.lastName} <br />
			<a href="viewProfile?profileId=${u.profile.profileId}">View
				Profile</a>
		</c:if>
	</c:forEach>
	<br />
	<br />

</body>
</html>