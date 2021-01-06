package auth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import auth.model.Customer;
import auth.model.JoinRequest;
import auth.model.ModifyRequest;
import init.JDBCUtil;

public class CustomerDao {
	
	public Customer selecteById(Connection conn, String userId) throws SQLException {
		String sql = "SELECT * FROM customer "
				+ "WHERE userId = ?";
		ResultSet rs = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getString("userId"));
				customer.setName(rs.getString("name"));
				customer.setPassword(rs.getString("password"));
				customer.setPhone(rs.getString("phone"));
				customer.setAddress(rs.getString("address"));
				int isManager = rs.getInt("ismanager");
				if(isManager == 1) {
					customer.setManager(true);
				} else {
					customer.setManager(false);
				}
				
				return customer;
			}
		} finally {
			JDBCUtil.close(rs);
		}
		
		return null;
	}
	
	public Integer getPK(Connection conn, String userId) throws SQLException {
		String sql = "SELECT id FROM customer "
				+ "WHERE userId = ?";
		ResultSet rs = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id");
			}
		} finally {
			JDBCUtil.close(rs);
		}
		
		return null;
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
	
	public void update(Connection conn, ModifyRequest modifyReq) throws SQLException {
		String sql = "UPDATE customer "
				+ "SET password = ?, phone = ?, address = ? "
				+ "WHERE userId = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, modifyReq.getNewPassword());
			pstmt.setString(2, modifyReq.getPhone());
			pstmt.setString(3, modifyReq.getAddress());
			pstmt.setString(4, modifyReq.getId());
			
			pstmt.executeUpdate();
		} 
	}
	
	public void remove(Connection conn, String userId) throws SQLException {
		String sql = "DELETE FROM customer "
				+ "WHERE userId = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			
			pstmt.executeUpdate();
		} 
	}
}
