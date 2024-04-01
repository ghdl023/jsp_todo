<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Register Page</h1>
	
	<form action="<%= request.getContextPath() %>/todo/register" method="post">
		Title: <input type="text" name="title" /> <br/>
		DueDate: <input type="date" name="dueDate" /> <br>
	
		<button type="submit">등록</button>
	</form>
</body>
</html>