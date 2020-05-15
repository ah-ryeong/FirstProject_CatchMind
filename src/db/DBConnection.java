package db;

import java.sql.Connection;
import java.sql.DriverManager;

// 연결
public class DBConnection {
	
	private final static String TAG = "DBConnection : ";
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = 
					DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "chatting", "bitc5600");
		} catch (Exception e) {
			System.out.println(TAG + "DB 연결 실패" + e.getMessage());
		}
		return null;
	}
}
