<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN PAGE</h1>
	<form action="${pageContext.request.contextPath}/logout" method="post">
		<input type="submit" value="logout"/>
 		<input type="hidden" name="_csrf" value="${_csrf.token}" />
	</form>

</body>
</html>