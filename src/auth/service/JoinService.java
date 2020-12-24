package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import auth.dao.CustomerDao;
import auth.model.Customer;
import auth.model.JoinRequest;
import init.ConnectionProvider;
import init.JDBCUtil;

public class JoinService {
	private CustomerDao customerDao = new CustomerDao();
	
	public void join(JoinRequest joinReq, Map<String, Boolean> errors) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();// dbcp 써보기
			conn.setAutoCommit(false);
			
			Customer customer = customerDao.selecteById(conn, joinReq.getId());
			// id 중복 시
			if(customer != null) {
				errors.put("duplicatedID", true);
				return ;
			}
			
			customerDao.insert(conn, joinReq);
			conn.commit();
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
	}

}
