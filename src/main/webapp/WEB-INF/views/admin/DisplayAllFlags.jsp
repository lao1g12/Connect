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
				<th>Flag flagId</th>
				<th>Flag flagInfo</th>
				<th>Flag dateAdded</th>
				<th>Flag flaggedPost</th>
			
			
			</tr>
			<c:forEach items="${flags}" var="f">
				<tr>
					<th>${f.flagId}</th>
					<th>${f.flagInfo}</th>
					<th>${f.dateAddedt}</th>
					<th>${f.flaggedPost}</th>
	
	               <th><a href="processRemovePost?postId=${p.postId}">Remove Flag</a></th>
				
					
				</tr>
			</c:forEach>
		</table>
${message }

</body>
</html>