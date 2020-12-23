package auth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import auth.model.JoinRequest;
import init.JDBCUtil;

public class CustomerDao {
	
	public Boolean selecteById(Connection conn, String id) throws SQLException {
		String sql = "SELECT * FROM customer "
				+ "WHERE userId = ?";
		ResultSet rs = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} finally {
			JDBCUtil.close(rs);
		}
		
		return false;
	}

	public void insert(Connection conn, JoinRequest joinReq) throws SQLException {
		String sql = "INSERT INTO customer "
				+ "(userId, name, password, phone, address) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, joinReq.getId());
			pstmt.setString(2, joinReq.getName());
			pstmt.setString(3, joinReq.getPassword());
			pstmt.setString(4, joinReq.getPhone());
			pstmt.setString(5, joinReq.getAddress());
			
			pstmt.executeUpdate();
		} 
	}
	
	
}
