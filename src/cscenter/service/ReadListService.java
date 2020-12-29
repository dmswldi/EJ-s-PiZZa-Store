package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cscenter.dao.PostDao;
import cscenter.model.Post;
import cscenter.model.PostPage;
import init.ConnectionProvider;
import init.JDBCUtil;

public class ReadListService {
	private PostDao postDao = new PostDao();
	private int size = 5;

	public PostPage readPostList(int pageNum) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = postDao.selectCount(conn);
			if(total == 0) {// 포스트 없음
				return null;
			}
			List<Post> list = postDao.select(conn, pageNum, size);
			return new PostPage(list, total, pageNum, size);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

	
	
	
}
