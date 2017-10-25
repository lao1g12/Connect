<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Users</title>
</head>
<body>
<h3>Trainees</h3> <br />
<c:forEach items="${users}" var="u">
	<c:if test="${u.job == 'Trainee'}">
		${u.profile.firstName} ${u.profile.lastName} <br />
		Stream: ${u.profile.stream} <br />
		Start Date: ${u.profile.getStartDateFormatted()} <br />
		End Date: ${u.profile.getEndDateFormatted()} <br /> <br />
	</c:if>
</c:forEach> <br /> <br />
<h3>Consultants</h3> <br />
<c:forEach items="${users}" var="u">
	<c:if test="${u.job == 'Consultant'}">
		${profile.firstName} ${profile.lastName} <br />
		Stream: ${profile.stream} <br />
	</c:if>
</c:forEach> <br /> <br />
<h3>Trainers</h3> <br />
<c:forEach items="${users}" var="u">
	<c:if test="${u.job == 'Trainer'}">
		${profile.firstName} ${profile.lastName} <br />
	</c:if>
</c:forEach> <br /> <br />
<h3>Account Managers</h3> <br />
<c:forEach items="${users}" var="u">
	<c:if test="${u.job == 'Account Manager'}">
		${profile.firstName} ${profile.lastName} <br />
	</c:if>
</c:forEach> <br /> <br />
<h3>Recruiters</h3> <br />
<c:forEach items="${users}" var="u">
	<c:if test="${u.job == 'Recruiter'}">
		${profile.firstName} ${profile.lastName} <br />
	</c:if>
</c:forEach> <br /> <br />
<h3>Managers</h3> <br />
<c:forEach items="${users}" var="u">
	<c:if test="${u.job == 'Management'}">
		${profile.firstName} ${profile.lastName} <br />
	</c:if>
</c:forEach> <br /> <br />
<h3>IT Department</h3> <br />
<c:forEach items="${users}" var="u">
	<c:if test="${u.job == 'IT'}">
		${profile.firstName} ${profile.lastName} <br />
	</c:if>
</c:forEach> <br /> <br />
<h3>Alumni</h3> <br />
<c:forEach items="${users}" var="u">
	<c:if test="${u.job == 'Alumni'}">
		${profile.firstName} ${profile.lastName} <br />
	</c:if>
</c:forEach> <br /> <br />

</body>
</html>