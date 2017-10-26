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
	<h2>User Search Results</h2>
	<br />
	<br />
	<c:forEach items="${profiles}" var="p">
		<img src="${p.imageUrl}" />
		<br>
		<br>
		${p.firstName} ${p.lastName} <br />
		<br />
		<a href="viewProfile?profileId=${p.profileId}">View Profile</a>
	</c:forEach>
</body>
</html>