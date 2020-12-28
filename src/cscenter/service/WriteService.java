package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;

import cscenter.dao.PostDao;
import cscenter.model.Post;
import init.ConnectionProvider;
import init.JDBCUtil;

public class WriteService {
	private PostDao postDao = new PostDao();

	public void write(Post post) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			postDao.insert(conn, post);
			conn.commit();
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
	}

	
}
