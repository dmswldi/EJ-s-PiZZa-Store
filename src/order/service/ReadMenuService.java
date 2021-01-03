package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import init.ConnectionProvider;
import init.JDBCUtil;
import order.dao.OrderDao;
import order.model.Menu;
import order.model.Store;

public class ReadMenuService {
	private OrderDao orderDao = new OrderDao();
	
	public List<Menu> readMenu() {
		Connection conn = null;
		List<Menu> menu = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			menu = orderDao.selectMenu(conn);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
		return menu;
	}
	
	public List<Store> readStore() {
		Connection conn = null;
		List<Store> store = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			store = orderDao.selectStore(conn);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
		return store;
	}

}
