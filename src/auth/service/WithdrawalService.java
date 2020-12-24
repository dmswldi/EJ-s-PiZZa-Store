package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import auth.dao.CustomerDao;
import auth.model.Customer;
import auth.model.User;
import init.ConnectionProvider;
import init.JDBCUtil;

public class WithdrawalService {
	private CustomerDao customerDao = new CustomerDao();

	public Boolean withdrawal(User user, Map<String, Boolean> errors) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Customer customer = customerDao.selecteById(conn, user.getId());
			
			if(customer == null) {
				throw new RuntimeException("fail to withdrawal");
			}
			
			if(!customer.matchPassword(user.getPassword())) {
				errors.put("pwNotMatch", true);
				return false;
			}
			
			customerDao.remove(conn, user.getId());
			conn.commit();
			System.out.println("됐냐고!!");
			return true;
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
		return false;
	}
	
}
