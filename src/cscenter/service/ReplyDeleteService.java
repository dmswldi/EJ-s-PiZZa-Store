package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;

import cscenter.dao.ReplyDao;
import cscenter.model.PostStatus;
import init.ConnectionProvider;
import init.JDBCUtil;

public class ReplyDeleteService {
	private ReplyDao replyDao = new ReplyDao();

	public void deleteReply(PostStatus postStatus, int replyId, boolean isManager) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			replyDao.delete(conn, replyId);
			if(isManager) {// 관리자면 게시글 상태 '답변대기'로 변경
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
