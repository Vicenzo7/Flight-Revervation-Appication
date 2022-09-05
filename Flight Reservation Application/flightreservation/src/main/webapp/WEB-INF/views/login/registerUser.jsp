<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
</head>
<body>
	<h2>User Registration:</h2>
	<form action="registerUser" method="post">
		<pre>
		First Name: <label><input type="text" name="firstName" /></label>
		Last Name: <label><input type="text" name="lastName" /></label>
		Email: <label><input type="text" name="email" /></label> 
		Password: <label><input type="password" name="password" /></label>
		Confirm Password: <input type="password"   name="confirmPassword" />
		<input type="submit" value="register"  />
		</pre>
	</form>

</body>
</html>