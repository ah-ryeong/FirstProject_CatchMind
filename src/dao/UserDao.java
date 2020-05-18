package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import db.DBConnection;
import db.DBUtills;
import models.User;

// 자바 디비 거점 
public class UserDao {
	
	private final static String TAG = "UserDao : ";
	
	public UserDao() {
		
	}
	
	private static UserDao instance = new UserDao();
	public static UserDao getInstance() {
		return instance;
	}
	
	public int 가입(User user) {
		
		final String SQL = "INSERT INTO users VALUES(num.nextval, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println(TAG + "UserName :" + user.getUserName());
		System.out.println(TAG + "Password :" + user.getPassword());
		try {
			// 1. 스트림 연결
			conn = DBConnection.getConnection();
			// 2. 버퍼달기
			pstmt = conn.prepareStatement(SQL);
//			 3. 물음표 완성
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			// 4. 쿼리전송(flush + commit)
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println(TAG + "추가오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtills.close(conn, pstmt);
		}
		return -1;
	}
}
