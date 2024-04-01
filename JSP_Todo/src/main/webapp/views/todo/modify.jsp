<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Modify Page</h1>
	<br>
	
	<form action="<%= request.getContextPath()%>/todo/modify" method="POST">
		<input type="text" name="tno" value="${dto.tno }" readonly/> <br>
		<input type="text" name="title" value="${dto.title }" /> <br>
		<input type="date" name="dueDate" value="${dto.dueDate }" > <br>
		<input type="checkbox" name="finished" ${dto.finished == 'Y' ? "checked" : "" }  />
		
		<div>
			<button type="submit">Modify</button>
		</div>
	</form>
	
	<form action="<%= request.getContextPath()%>/todo/delete" method="POST">
		<input type="hidden" name="tno" value="${dto.tno }" />
		<div>
			<button type="submit">Delete</button>
		</div>
	
	</form>
</body>
</html>