<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
	<ul>
		<li><a href="user/login">로그인</a></li>
		<li><a href="user/register">회원가입</a></li>
		<li><a href="user/content">내용페이지</a></li>
	</ul>
	
	<!-- 파일을 업로드 할 때 enctype이 제대로 설정되지 않으면
	multipart가 아니라고 예외가 발생 -->
	<form action="file/upload" method="post" id="myform"
	enctype="multipart/form-data">
		이름<input type="text" name="name" id="name" 
		required="required" /><br />
		파일<input type="file" name="file" id="file" /><br />
		<input type="submit" value="파일 업로드" />
	</form>
</body>
</html>