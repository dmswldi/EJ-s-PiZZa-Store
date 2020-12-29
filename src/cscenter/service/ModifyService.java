package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;

import cscenter.dao.PostDao;
import cscenter.model.ModifyReq;
import init.ConnectionProvider;
import init.JDBCUtil;

public class ModifyService {
	private PostDao postDao = new PostDao();

	public void modifyPost(ModifyReq modifyReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			postDao.update(conn, modifyReq);
			conn.commit();
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
	}

}
