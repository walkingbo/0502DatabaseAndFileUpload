package util;

import java.sql.Connection;

//공유변수를 소유한 클래스
public class ShareInstance {
	private ShareInstance() {}
	
	private static ShareInstance shareInstance;
	
	public static ShareInstance getInstance() {
		if(shareInstance == null) {
			shareInstance = new ShareInstance();
		}
		return shareInstance;
	}
	//데이터 연결변수
	public Connection con;
}
