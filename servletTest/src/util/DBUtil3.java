package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로 구성된 class 만들기

// ResourceBundle객체를 이용하여 처리하기
public class DBUtil3 {
	static ResourceBundle bundle; 	// ResourceBundle객체 변수 선언
	
	static{ 		// static 초기화 블럭
		bundle = ResourceBundle.getBundle("dbinfo");
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패~~ " + e.getMessage());
		}
	}
	
	public static Connection getConnection(){
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", 
//					"pc00", "java");
			return DriverManager.getConnection(
					bundle.getString("url"), 
					bundle.getString("user"), 
					bundle.getString("pass"));
			
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패!!");
			return null;
		}
	}
	
}
