package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

	public List<Cart> selectByUser(Connection conn, String customerId) throws SQLException {
		String sql = "SELECT * FROM cart "
				+ "WHERE customerId = ?";
		List<Cart> cartList = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, customerId);
			
			pstmt.executeQuery();
			
		}
		
		return cartList;
	}

}
