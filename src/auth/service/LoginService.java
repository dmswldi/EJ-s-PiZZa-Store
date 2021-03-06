package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import auth.dao.CustomerDao;
import auth.model.Customer;
import auth.model.User;
import init.ConnectionProvider;
import init.JDBCUtil;

public class LoginService {
	private CustomerDao customerDao = new CustomerDao();

	public Customer login(User user, Map<String, Boolean> errors) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Customer customer = customerDao.selecteById(conn, user.getId());
			
			// 해당 id 없음 에러
			if(customer == null) {
				errors.put("idNotExist", true);
				return null;
			}
			
			// pw 틀림 에러
			if(!customer.matchPassword(user.getPassword())) {
				errors.put("pwNotMatch", true);
				return null;
			}
			
			return customer;
			
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
