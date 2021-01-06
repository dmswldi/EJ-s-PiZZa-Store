package order.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import init.JDBCUtil;
import order.model.Menu;
import order.model.Store;

public class OrderDao {

	public List<Menu> selectMenu(Connection conn) throws SQLException {
		String sql = "SELECT * FROM menu "
				+ "ORDER BY category, price";
		ResultSet rs = null;
		List<Menu> list = null;
		
		try (Statement stmt = conn.createStatement()){
			rs = stmt.executeQuery(sql);
			list = new ArrayList<>();
			
			while(rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setName(rs.getString("name"));
				menu.setPrice(rs.getInt("price"));
				menu.setDiscountRate(rs.getInt("discountRate"));
				menu.setOrderCount(rs.getInt("orderCount"));
				menu.setDescription(rs.getString("description"));
				menu.setRating(rs.getInt("rating"));
				menu.setCategory(rs.getString("category"));
				menu.setReviewId(rs.getInt("reviewId"));
				list.add(menu);
			}
		} finally {
			JDBCUtil.close(rs);
		}
		
		return list;
	}
	
	public List<Store> selectStore(Connection conn) throws SQLException {
		String sql = "SELECT * FROM store "
				+ "ORDER BY name";
		ResultSet rs = null;
		List<Store> list = null;
		
		try (Statement stmt = conn.createStatement()){
			rs = stmt.executeQuery(sql);
			list = new ArrayList<>();
			
			while(rs.next()) {
				Store store = new Store();
				store.setId(rs.getInt("id"));
				store.setName(rs.getString("name"));
				store.setLoc(rs.getString("loc"));
				store.setNumber(rs.getString("number"));///// -> number
				list.add(store);
			}
		} finally {
			JDBCUtil.close(rs);
		}
		return list;
	}

}
