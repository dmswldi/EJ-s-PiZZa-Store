package cscenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cscenter.model.Comment;
import cscenter.model.PostStatus;
import cscenter.model.UpdateReplyReq;
import init.JDBCUtil;

public class ReplyDao {

	public void insert(Connection conn, Comment comment) throws SQLException {
		String sql = "INSERT INTO comment "
				+ "(inquiryId, customerId, comments, date ) "
				+ "VALUES (?, ?, ?, now()) ";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, comment.getInquiryId());
			pstmt.setString(2, comment.getCustomerId());
			pstmt.setString(3, comment.getComment());
			
			pstmt.executeUpdate();
		}
		
	}
	
	public void changeStatus(Connection conn, PostStatus postStatus) throws SQLException {
		String sql = "UPDATE customercenter "
				+ "SET status = ? "
				+ "WHERE id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, postStatus.getStatus());
			pstmt.setInt(2, postStatus.getPostId());
			
			pstmt.executeUpdate();
		}
		
	}
	
	public int count(Connection conn, int inquiryId) throws SQLException {
		String sql = "SELECT count(*) FROM comment "
				+ "WHERE inquiryId = ?";
		ResultSet rs = null;
		int cnt = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, inquiryId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} finally {
			JDBCUtil.close(rs);
		}
		return cnt;
	}

	// 해당 게시글의 댓글 읽어오기
	public List<Comment> select(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM comment "
				+ "WHERE inquiryId = ? "
				+ "ORDER BY date DESC";
		ResultSet rs = null;
		List<Comment> list = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setInquiryId(rs.getInt("inquiryId"));
				comment.setCustomerId(rs.getString("customerId"));
				comment.setComment(rs.getString("comments"));
				comment.setDate(rs.getTimestamp("date"));
				if(isManager(conn, rs.getString("customerId"))) {					
					comment.setStateChangable(true);
				} else {
					comment.setStateChangable(false);
				}
				
				list.add(comment);
			}
		} finally {
			JDBCUtil.close(rs);
		}
		
		return list;
	}
	
	public boolean isManager(Connection conn, String customerId) throws SQLException {
		String sql = "SELECT isManager FROM customer "
				+ "WHERE userId = ?";
		ResultSet rs = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, customerId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int result = rs.getInt(1);
				if(result == 1) {
					return true;
				}
			}
		} finally {
			JDBCUtil.close(rs);
		}
		return false;
	}

	public void update(Connection conn, UpdateReplyReq updateReq) throws SQLException {
		String sql = "UPDATE comment "
				+ "SET comments = ? "
				+ "WHERE id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, updateReq.getComment());
			pstmt.setInt(2, updateReq.getId());
			
			pstmt.executeUpdate();
		}
		
	}
	
	public void delete(Connection conn, int replyId) throws SQLException{
		String sql = "DELETE FROM comment "
				+ "WHERE id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, replyId);
			
			pstmt.executeUpdate();
		}
	}
	
	public void deleteAll(Connection conn, int postId) throws SQLException{
		String sql = "DELETE FROM comment "
				+ "WHERE inquiryId = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, postId);
			
			pstmt.executeUpdate();
		}
	}
	
	
}
