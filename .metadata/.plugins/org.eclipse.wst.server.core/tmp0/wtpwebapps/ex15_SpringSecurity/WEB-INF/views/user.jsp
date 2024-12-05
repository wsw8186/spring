<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>USER PAGE</h1>
	
	<p>principal : <sec:authentication property="principal"/></p><hr/>
 	<p>UserDto : <sec:authentication property="principal.userDto"/></p><hr/>
 	<p>principal로 꺼낸 ID : <sec:authentication property="principal.Username"/></p><hr/>
 	<p>사용자의 이름 : <sec:authentication property="principal.userDto.username"/></p><hr/>
 	<p>사용자의 아이디 : <sec:authentication property="principal.userDto.username"/></p><hr/>
 	<p>사용자의 권한 목록 : <sec:authentication property="principal.userDto.role"/></p><hr/>
</body>
</html>