<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Detail Page</h1>
	${ dto }
	<br>
	<input type="text" name="tno" value="${dto.tno }" readonly/> <br>
	<input type="text" name="title" value="${dto.title }" readonly/> <br>
	<input type="date" name="dueDate" value="${dto.dueDate }" > <br>
	<input type="checkbox" name="finished" ${dto.finished == 'Y' ? "checked" : "" } readonly />
	
	<div>
		<a href="<%= request.getContextPath() %>/todo/modify?tno=${dto.tno}">수정/삭제</a>
		<a href="<%= request.getContextPath() %>/todo/list">목록으로</a>
	</div>
</body>
</html>