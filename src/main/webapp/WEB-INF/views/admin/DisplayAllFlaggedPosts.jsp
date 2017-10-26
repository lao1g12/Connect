<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${message}
<table>
			<tr>
				<th>Post postId</th>
				<th>Post title</th>
				<th>Post bodyText</th>
				<th>Post link</th>
				<th>Post category </th>
				<th>Post imgUrl</th>
			
			</tr>
			<c:forEach items="${flaggedPosts}" var="p">
				<tr>
					<th>${p.postId}</th>
					<th>${p.title}</th>
					<th>${p.bodyText}</th>
					<th>${p.link}</th>
					<th>${p.category}</th>
					<th>${p.imgUrl}</th>
					<th>${p.getFlags().size()}</th>
					<th><a href="viewAllFlags?postId=${p.postId}">View All Flags</a></th>
	               <th><a href="processRemovePost?postId=${p.postId}">Remove Post</a></th>
				
					
				</tr>
			</c:forEach>
		</table>

</body>
</html>