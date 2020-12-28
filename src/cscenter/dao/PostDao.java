package cscenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cscenter.model.APost;
import cscenter.model.Post;
import cscenter.model.UpdateReq;
import init.JDBCUtil;

public class PostDao {
	
	public void insert(Connection conn, Post post) throws SQLException {
		String sql = "INSERT INTO customercenter "
				+ "(category, customerId, title, content) "
				+ "VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, post.getCategory());
			pstmt.setString(2, post.getCustomerId());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getContent());
			
			pstmt.executeUpdate();
		}
		
	}
	
	public int selectCount(Connection conn) throws SQLException {
		String sql = "SELECT COUNT(*) "
				+ "FROM customercenter";
		ResultSet rs = null;
		
		try (Statement stmt = conn.createStatement()){
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} finally {
			JDBCUtil.close(rs);
		}
		return 0;
	}

	public List<APost> select(Connection conn, int pageNum, int size) throws SQLException {
		String sql = "SELECT id, category, customerId, title, content, date, status "
				+ "FROM customercenter "
				+ "ORDER BY id DESC "
				+ "LIMIT ?, ?";// 시작(zero base), 개수, // (0, 5), (5, 10)...
		ResultSet rs = null;
		List<APost> list = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){// 1 5    6 10    11
			pstmt.setInt(1, ( (pageNum - 1) / 5 + 1 ) * 5 - size);// ((pageNum -1) / 5 + 1) * 5 	 	// 1    2   3 * 5 
			pstmt.setInt(2, ( (pageNum - 1) / 5 + 1 ) * 5 );
			
			rs = pstmt.executeQuery();
			while(rs.next()) {// while... wyrano
				APost post = new APost();
				post.setId(rs.getInt("id"));
				post.setCategory(rs.getString("category"));
				post.setCustomerId(rs.getString("customerId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setDate(rs.getTimestamp("date"));
				if(rs.getInt("status") == 0) {
					post.setStatus("답변대기");
				} else {
					post.setStatus("답변완료");
				}
				list.add(post);
			}
			return list;
			
		} finally {
			JDBCUtil.close(rs);
		}
	}
	
	public APost selectById(Connection conn, int id) throws SQLException {
		String sql = "SELECT id, category, customerId, title, content, date, status "
				+ "FROM customercenter "
				+ "WHERE id = ?";
		ResultSet rs = null;
		APost post = new APost();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post.setId(rs.getInt("id"));
				post.setCategory(rs.getString("category"));
				post.setCustomerId(rs.getString("customerId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setDate(rs.getTimestamp("date"));
				post.setStatus(rs.getString("status"));
			}
		} finally {
			JDBCUtil.close(rs);
		}
		return post;
	}
	
	public void update(Connection conn, UpdateReq req) throws SQLException {
		String sql = "UPDATE customercenter "
				+ "SET title = ?, content = ? "
				+ "WHERE id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, req.getTitle());
			pstmt.setString(2, req.getContent());
			pstmt.setInt(3, req.getId());
			
			pstmt.executeUpdate();
		}
	}
	
	public void delete(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM customercenter "
				+ "WHERE id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

}
