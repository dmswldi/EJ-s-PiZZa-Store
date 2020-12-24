package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.dao.CustomerDao;
import auth.model.Customer;
import auth.model.ModifyRequest;
import init.ConnectionProvider;
import init.JDBCUtil;

public class ModifyInfoService {
	private	CustomerDao customerDao = new CustomerDao();

	public void modify(ModifyRequest modifyReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Customer customer = customerDao.selecteById(conn, modifyReq.getId());
			if(customer == null) {
				throw new RuntimeException("fail to modify info");
			}
			
			customerDao.update(conn, modifyReq);
			
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
	}
	
}
