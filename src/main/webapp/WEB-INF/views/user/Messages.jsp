<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../PageDirectives.jsp"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<c:forEach items="${conversation}" var="c">
			<h3>
				${c.getNotificationDateFormatted()}
				${c.title}
				${c.body}
				${c.sender.getUsername()}
			</h3>


		</c:forEach>
		
	<sf:form method="post" action="sendMessage" modelAttribute="notification">

		Title : <sf:input type="text" path="title" /> <br> 
		BodyText: <sf:input type="text" path="body" /> <br> 
		<sf:hidden value="message" path="type"/>
		<input type="hidden" value="${username}" name="recipient"/>
		<button type="submit" value="Submit Post">Add post</button>
	</sf:form>

</body>
</html>