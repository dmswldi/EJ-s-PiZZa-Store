package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cscenter.dao.ReplyDao;
import cscenter.model.Comment;
import cscenter.model.Post;
import init.ConnectionProvider;
import init.JDBCUtil;

public class ReplyReadService {
	private ReplyDao replyDao = new ReplyDao();

	public List<Comment> getReplyList(int id) {
		Connection conn = null;
		List<Comment> comments = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			comments = replyDao.select(conn, id);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return comments;
	}
	
	public int getReplyCnt(int id) {
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			cnt = replyDao.count(conn, id);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return cnt;
	}
	
	public List<Integer> getReplyCntList(List<Post> list) {
		Connection conn = null;
		List<Integer> replyCntList = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			replyCntList = new ArrayList<>();
			for(Post post : list) {				
				int cnt = replyDao.count(conn, post.getId());
				replyCntList.add(cnt);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return replyCntList;
	}

}
