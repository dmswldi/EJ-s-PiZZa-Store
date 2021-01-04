package cscenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cscenter.model.ModifyReq;
import cscenter.model.Post;
import cscenter.model.Postf;
import init.JDBCUtil;

public class PostDao {
	
	public void insert(Connection conn, Postf post) throws SQLException {
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
	
	public int selectMyCount(Connection conn, String id) throws SQLException {
		String sql = "SELECT COUNT(*) "
				+ "FROM customercenter "
				+ "WHERE customerId = ?";
		
		ResultSet rs = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} finally {
			JDBCUtil.close(rs);
		}
		return 0;
	}

	public List<Post> select(Connection conn, int pageNum, int size) throws SQLException {
		String sql = "SELECT id, category, customerId, title, content, date, status "
				+ "FROM customercenter "
				+ "ORDER BY id DESC "
				+ "LIMIT ?, ?"; // 시작(zero base), 개수, // (0, 5), (5, 5)...
		/*String sql = "SELECT rn, id, category, customerId, title, content, date0, status " + 
				"FROM ( " + 
				"	SELECT id, category, customerId, title, content, date0, status, " + 
				"	ROW_NUMBER() OVER (ORDER BY id DESC) rn FROM customercenter "
				+ ") "
				+ "WHERE rn "
				+ "BETWEEN ? AND ?";*/
		ResultSet rs = null;
		List<Post> list = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){// 0 5 10 15 20 ... 
			pstmt.setInt(1, (pageNum -1) * size);// pageNum: 1 -> (0, 5) / pageNum: 2 -> (5,10) ...
			//pstmt.setInt(1, (pageNum -1) * size + 1);// 1 -> (1,5) / 2 -> (6, 10)
			pstmt.setInt(2, size);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setCategory(rs.getString("category"));
				post.setCustomerId(rs.getString("customerId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setDate(rs.getTimestamp("date"));///
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
	
	public Post selectById(Connection conn, int id) throws SQLException {
		String sql = "SELECT id, category, customerId, title, content, date, status "///
				+ "FROM customercenter "
				+ "WHERE id = ?";
		ResultSet rs = null;
		Post post = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post = new Post();
				post.setId(rs.getInt("id"));
				post.setCategory(rs.getString("category"));
				post.setCustomerId(rs.getString("customerId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setDate(rs.getTimestamp("date"));///
				post.setStatus(rs.getString("status"));
			}
		} finally {
			JDBCUtil.close(rs);
		}
		return post;
	}
	
	public List<Post> selectMy(Connection conn, String id, int pageNum, int size) throws SQLException {
		String sql = "SELECT id, category, customerId, title, content, date, status "
				+ "FROM customercenter "
				+ "WHERE customerId = ? "
				+ "ORDER BY id DESC "
				+ "LIMIT ?, ?";
		/*String sql = "SELECT rn, id, category, customerId, title, content, date0, status " + 
				"FROM ( " + 
				"	SELECT id, category, customerId, title, content, date0, status, " + 
				"	ROW_NUMBER() OVER (ORDER BY id DESC) rn FROM customercenter "
				+ ") "
				+ "WHERE customerId = ? "
				+ "AND rn "
				+ "BETWEEN ? AND ?";*/
		ResultSet rs = null;
		List<Post> list = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){// 0 5 10 15 20 ... 
			pstmt.setString(1, id);
			/* mysql */
			pstmt.setInt(2, (pageNum -1) * size);// pageNum: 1 -> (0, 5) / pageNum: 2 -> (5,10) ...
			pstmt.setInt(3, size);
			/* oracle */
			/*pstmt.setInt(2, (pageNum -1) * size + 1);
			psmt.setInt(3, pageNum * size);*/
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setCategory(rs.getString("category"));
				post.setCustomerId(rs.getString("customerId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setDate(rs.getTimestamp("date"));///
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
	
	public void update(Connection conn, ModifyReq modifyReq) throws SQLException {
		String sql = "UPDATE customercenter "
				+ "SET category = ?, title = ?, content = ? "
				+ "WHERE id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, modifyReq.getCategory());
			pstmt.setString(2, modifyReq.getTitle());
			pstmt.setString(3, modifyReq.getContent());
			pstmt.setInt(4, modifyReq.getId());
			
			pstmt.executeUpdate();
		}
	}
	
	public void delete(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM customercenter "
				+ "WHERE id = ?";
		/*String sql = "DELETE customercenter "
				+ "WHERE id = ?";*/
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
	}

}
