<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Oh it looks like it is your first time to FDM Connect, please update your password below!</h2>
<h3>${passNotMatch}</h3>
	<form method="post" action="passwordUpdate">
		Enter Original password : <input required type="password"
			name="password" /> Enter Your new password : <input required
			type="password" name="newPassword" /> Please confirm your NEW
		password : <input required type="password" name="confNewPassword" /> <input
			type="submit" value="Update details" />
	</form>

</body>
</html>