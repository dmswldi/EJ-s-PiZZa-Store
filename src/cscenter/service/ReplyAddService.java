package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;

import cscenter.dao.ReplyDao;
import cscenter.model.Comment;
import cscenter.model.PostStatus;
import init.ConnectionProvider;
import init.JDBCUtil;

public class ReplyAddService {
	private ReplyDao replyDao = new ReplyDao();

	public void addReply(Comment comment) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			replyDao.insert(conn, comment);
			
			PostStatus postStatus = new PostStatus();
			postStatus.setPostId(comment.getInquiryId());
			postStatus.setStatus(1);
			
			if(comment.isStateChangable()) {// 관리자면 게시글 상태 '답변완료'로 변경
				replyDao.changeStatus(conn, postStatus);				
			}
			conn.commit();
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
	}

}
