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
			
			int ea = cartDao.hasMenu(conn, cart, customerId);
			if(ea == 0) {
				cartDao.insert(conn, cart, customerId);// 해당 메뉴 카트 뷰에 없으면 insert						
			} else {
				cartDao.update(conn, cart, customerId, ea);// 카트 뷰에 존재하면 update 
			}
			
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
