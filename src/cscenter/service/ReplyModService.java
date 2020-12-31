package cscenter.service;

import java.sql.Connection;
import java.sql.SQLException;

import cscenter.dao.ReplyDao;
import cscenter.model.UpdateReplyReq;
import init.ConnectionProvider;
import init.JDBCUtil;

public class ReplyModService {
	private ReplyDao replyDao = new ReplyDao();

	public void modifyReply(UpdateReplyReq updateReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			replyDao.update(conn, updateReq);
			conn.commit();
		} catch(SQLException e) {
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		
	}

}
