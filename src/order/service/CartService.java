package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import auth.dao.CustomerDao;
import init.ConnectionProvider;
import init.JDBCUtil;
import order.dao.CartDao;
import order.model.Cart;

public class CartService {
	private CartDao cartDao = new CartDao();
	private CustomerDao customerDao = new CustomerDao();

	public void add(Cart cart, int customerId) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			cartDao.insert(conn, cart, customerId);				
			
			conn.commit();
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
	}

	public List<Cart> getCart(int customerId) {
		Connection conn = null;
		List<Cart> cartList = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			cartList = cartDao.selectByUser(conn, customerId);
			
			conn.commit();
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
		return cartList;
	}

}
