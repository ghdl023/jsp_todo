<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>List Page</h1>
	<c:forEach var="dto" items="${list }">
		<li><a href="<%= request.getContextPath()%>/todo/detail?tno=${dto.tno}">${ dto }</a></li>
	</c:forEach>
</body>
</html>