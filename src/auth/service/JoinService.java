package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import auth.dao.CustomerDao;
import auth.model.JoinRequest;
import init.ConnectionProvider;

public class JoinService {
	CustomerDao customerDao = new CustomerDao();
	
	public void join(JoinRequest joinReq, Map<String, Boolean> errors) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();// dbcp 써보기
			conn.setAutoCommit(false);
			
			// id 중복 시
			if(customerDao.selecteById(conn, joinReq.getId())) {
				errors.put("duplicatedID", true);
				return ;
			}
			
			customerDao.insert(conn, joinReq);
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
