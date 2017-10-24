<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<h1>Welcome ${user.getUsername()}</h1>
		<c:forEach items="${allPosts}" var="aP">
			<h3>${aP.title}<br /></h3>
			${aP.bodyText}<br />
			<img src="${aP.imgUrl}">
			<a href="${aP.link}">For more info click here!</a><br />
			Category: ${aP.category}<br />
			Posted: ${aP.postDate}<br />
			Posted By: ${aP.postOwner}<br />
			<hr />
		</c:forEach>

</body>
</html>