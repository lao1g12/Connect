<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ include file="../PageDirectives.jsp"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:forEach items="${groups}" var="g">
	<h3> ${g.name } <br/></h3>
	 
	 Description: ${g.description } <br/>
	 
	 <img class="boarding" src="${g.imageUrl }"> <br/>
			<br>
			<br>
			<a href="goToGroupHome?name=${g.name}">Go To Group</a>
			<br>
			<a href="goToLeaveGroup?name=${g.name}">Leave Group</a>

	</c:forEach>

<sf:form method="post" action="doCreateGroup"  modelAttribute="group">
		Group Name: <sf:input type="text"   path="name"/><br>
		Group Description: <sf:input type="text"  path="description"/><br>
	    Group Picture<sf:input type="text" path ="imageUrl"/> <br>
		<input type="submit" value="Create group"/><br>
	</sf:form>
	${groupWasCreated}
	${ownerLeftGroup }


</body>
</html>