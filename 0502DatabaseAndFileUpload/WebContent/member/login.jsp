<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form method="post">
	
		<div id="msg"></div>
	
		<label for="id">아이디</label>
		<input type="text" name="id" id="id" required="required"/>
		<br />
		
		<label for="pw">비밀번호</label>
		<input type="text" name="pw" id="pw" required="required"/>
		<br />
	
		<input type="submit" value="로그인"/>
		
	</form>
</body>
</html>