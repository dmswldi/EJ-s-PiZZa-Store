package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import init.JDBCUtil;
import order.model.Cart;

public class CartDao {

	public void insert(Connection conn, Cart cart, int customerId) throws SQLException {
		String sql = "INSERT INTO cart (menuId, ea, customerId) "
				+ "VALUES (?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, cart.getMenuId());
			pstmt.setInt(2, cart.getEa());
			pstmt.setInt(3, customerId);
			
			pstmt.executeUpdate();
		}
		
	}

	public List<Cart> selectByUser(Connection conn, int customerId) throws SQLException {
		String sql = "SELECT * FROM cartDetail "
				+ "WHERE customerId = ?";
		List<Cart> cartList = null;
		ResultSet rs = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			cartList = new ArrayList<>();
			pstmt.setInt(1, customerId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Cart cart = new Cart();
				cart.setMenuId(rs.getInt("menuId"));
				cart.setEa(rs.getInt("ea"));
				cart.setMenuName(rs.getString("menuName"));
				cartList.add(cart);
			}
			
		} finally {
			JDBCUtil.close(rs);
		}
		return cartList;
	}

	public int hasMenu(Connection conn, Cart cart, int customerId) throws SQLException {
		String sql = "SELECT * FROM cart "
				+ "WHERE customerId = ? "
				+ "AND menuId = ?";
		ResultSet rs = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, cart.getMenuId());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("ea");
			}
			
		} finally {
			JDBCUtil.close(rs);
		}
		return 0;
	}

	public void update(Connection conn, Cart cart, int customerId, int ea) throws SQLException {
		String sql = "UPDATE cart "
				+ "SET ea = ? "
				+ "WHERE customerId = ? "
				+ "AND menuId = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, ea + cart.getEa());
			pstmt.setInt(2, customerId);
			pstmt.setInt(3, cart.getMenuId());
			
			pstmt.executeUpdate();
		}
		
	}

}
