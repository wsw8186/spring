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
	<h1>JOIN PAGE</h1>
	<form action="${pageContext.request.contextPath}/join" method="post">
		<div>
			<label>USERNAME : </label>
			<input type="text" name="username" />
 		</div>
		<div>
			<label>PASSWORD : </label>
			<input type="password" name="password" />
 		</div>
 		<div>
 			<input type="submit" value="회원가입">
 		</div>
 			
 		<input type="hidden" name="_csrf" value="${_csrf.token}" />
	</form>

</body>
</html>