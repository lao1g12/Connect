<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="PageDirectives.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/FDMConnect.css" />
<title>Welcome</title>
</head>



<body>
	<div class="center">
		<img src="img/fdmConnect.jpg" />
		<form action="j_security_check" method="post">
			Username: <input name="j_username" /> <br /> <br /> Password : <input
				type="password" name="j_password" /> <br /> <br /> <input
				class="loginbutton" type="Submit" value="Login" />
		</form>
	</div>
</body>
</html>