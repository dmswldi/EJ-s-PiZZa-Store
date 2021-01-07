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

}
