<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Page</h1>
	${param.result }
	<form action="<%= request.getContextPath() %>/login" method="POST">
		<input type="text" name="mid" />
		<input type="text" name="mpw" />
		<input type="checkbox" name="auto" />
		
		<button type="submit">Login</button>
	</form>
</body>
</html>