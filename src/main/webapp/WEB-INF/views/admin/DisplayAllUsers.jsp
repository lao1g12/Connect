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

<table>
			<tr>
				<th>User Username</th>
				<th>User Email</th>
				<th>User Job</th>
				<th>User Role</th>
				<th>User Profile </th>
				<th>User Posts </th>
			</tr>
			<c:forEach items="${users}" var="u">
				<tr>
					<th>${u.username}</th>
					<th>${u.email}</th>
					<th>${u.job}</th>
					<th>${u.role}</th>
					<th>${u.profile}</th>
					<th>${u.posts}</th>
					
				</tr>
			</c:forEach>
		</table>
${message }


</body>
</html>