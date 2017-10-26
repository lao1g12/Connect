<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../PageDirectives.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${profile.firstName} ${profile.lastName}'s Profile</h2>
<body>
	<div>

		<img src="${profile.imageUrl}" /> <br>
		<br> First Name : ${profile.firstName} <br>
		<br> Last Name : ${profile.lastName} <br>
		<br> Description: ${profile.description} <br>
		<br> Hobbies: ${profile.hobbies } <br>
		<br>

		<h2>Education</h2>
		<c:forEach items="${education}" var="edu">
	Institution: ${edu.institution}<br>
	Qualifications: ${edu.qualification}<br>
	Start Date: ${edu.getStartDateFormatted()}<br>
	End Date: ${edu.getEndDateFormatted()}<br>
	Further Information: ${edu.furtherInfo}<br>
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
			<br>
		</c:forEach>

		<h2>FDM Training</h2>
		Stream: ${profile.stream} <br>
		<br> StartDate: ${profile.getStartDateFormatted()} <br>
		<br> EndDate: ${profile.getEndDateFormatted()} <br>
		<br>


	</div>

</body>
</html>