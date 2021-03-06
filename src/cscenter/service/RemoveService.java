package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;

import cscenter.dao.PostDao;
import cscenter.dao.ReplyDao;
import init.ConnectionProvider;
import init.JDBCUtil;

public class RemoveService {
	private PostDao postDao = new PostDao();
	private ReplyDao replyDao = new ReplyDao();

	public void removePost(int id) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			postDao.delete(conn, id);
			replyDao.deleteAll(conn, id);
			conn.commit();
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
	}
	
}
