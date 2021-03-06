package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;

import cscenter.dao.PostDao;
import cscenter.model.Post;
import init.ConnectionProvider;
import init.JDBCUtil;

public class ReadService {
	private PostDao postDao = new PostDao();

	public Post readPost(int id) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			Post post = postDao.selectById(conn, id);
			
			return post;
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
		return null;
	}

}
