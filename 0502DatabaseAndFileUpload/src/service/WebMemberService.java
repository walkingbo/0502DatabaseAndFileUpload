package service;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

//비지니스 로직 처리를 위한 메소드의 원형을 가지는 인터페이스 	
public interface WebMemberService {
	//id 중복 체크를 위한 메소드
	//중복체크한 결과를 JSONObject로 만들어서 리턴하면
	//Controller는 이 데이터를 jsp로 출력
	//요청하는 쪽에서는 json 데이터를 받아서 사용하면됩니다.
	//JSON 형태로 리턴하면
	//이 서비스는 PC Application 과 SmartPhone 에서도 사용 가능
	public JSONObject idcheck(HttpServletRequest request);
	//nickname 중복 체크를 위한 메소드
	public JSONObject nicknamecheck(HttpServletRequest request);
	//회원가입을 처리하는 메소드
	public int insertuser(HttpServletRequest request);

}
