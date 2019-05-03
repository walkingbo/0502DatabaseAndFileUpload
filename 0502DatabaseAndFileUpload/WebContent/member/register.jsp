<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<!-- 최근에는 form에 action을 기재하지 않고 method를 post로 설정해서 get 용청과 구분해서 처리한다. -->
	<form method="post" id="registerform">
		<div>	
			<label for="id">아이디</label>
			<input type="text" id="id" name="id" required="required"/>
			<span id="idmsg"></span>
		</div>
		<div>	
			<label for="pw">비밀번호</label>
			<input type="text" id="pw" name="pw" required="required"/>
			<span id="pwmsg"></span>
		</div>
		<div>	
			<label for="pwconfirm">비밀번호 확인</label>
			<input type="password" id="pwconfirm" required="required"/>
		</div>
		<div>	
			<label for="nickname">닉네임</label>
			<input type="text" id="nickname" name="nickname" required="required"/>
			<input type="button" id="nicknameconfirm" value="중복확인"/>
			<span id="nicknamemsg"></span>
		</div>
		<div>	
			<input type="button" id="mainbtn" value="메인화면"/>
			<input type="submit" value="회원가입"/>
		</div>
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script>
		var ids = document.getElementById("id")
		var pw = document.getElementById("pw")
		var nickname = document.getElementById("nickname")
		
		var idmsg = document.getElementById("idmsg")
		var pwmsg = document.getElementById("pwmsg")
		var nicknamemsg = document.getElementById("nicknamemsg")
		
		var nicknameconfirm = document.getElementById("nicknameconfirm")
		var registerform = document.getElementById("registerform")
		
		//중복체크 성공 여부를 저장할 변수 선언
		var idcheck = false
		
		//아이디 입력란에서 포커스가 없어날 때 ajax로 중복 체크 요청
		ids.addEventListener("focusout",function(e){
			//아래 메시지는 출력되지 않으면 이벤트 에러이고 아래 메시지는 뜨는데 Controller에서 출력이 안되면
			//URL을 잘못 설정 한 것
			alert("확인")
			$.ajax({
				url:'idcheck',
				data:{'id':ids.value},
				dataType:'json',
				success:function(data){
					if(data.result == "success"){
				   	 idmsg.innerHTML ="사용가능한 아이디 입니다."
				   	 idmsg.style.color ="green"
				   	 idcheck = true
				}else{
					 idmsg.innerHTML ="사용 불가능한 아이디 입니다."
					 idmsg.style.color ="red"
					 idcheck = false
				 }
			   }
			});
		})
		
		//nickname 중복 체크
		var nicknamecheck = false;
		
		//중복 체크 버튼을 누르면 ajax 로 처리
		nicknameconfirm.addEventListener('click', function(e){
			$.ajax({
				url:'nicknamecheck',
				data:{'nickname':nickname.value},
				dataType:'json',
				success:function(data){
					if(data.result === "success"){
						nicknamemsg.innerHTML = "사용 가능한 닉네임입니다."
							nicknamemsg.style.color = "green"
						nicknamecheck = true
					}else{
						nicknamemsg.innerHTML = "사용 불 가능한 닉네임입니다."
						nicknamemsg.style.color = "red"
						nicknamecheck = false
					}
				}
			});
		});
		
		//폼의 데이터를 전송하고자 할 때 	
		registerform.addEventListener("submit",function(e){
			//id 중복 검사 결과 확인
			if(idcheck == false){
				idmsg.innerHTML = "아이디 중복검사를 수행해야 합니다."
				//폼의 데이터 전송을 하지 않도록 설정
				e.preventDefault()	
			}
			
			//nickname 중복검사 결과 확인
			if(nicknamecheck == false){
				nicknamemsg.innerHTML = "닉네임 중복검사를 수행해야 합니다."
				//폼의 데이터 전송을 하지 않도록 설정
				e.preventDefault()	
			}
			
			//비밀번호 일치 여부 확인
			if(pw.value !== pwconfirm.value){
				pwmsg.innerHTML = "2개의 값은 일치해야 합니다."
				e.preventDefault()	
			}else{
				pwmsg.innerHTML = ""
				registerform.submit();
			}
			
			
		});
		
		
		
		
		
	</script>
	
	

</body>
</html>