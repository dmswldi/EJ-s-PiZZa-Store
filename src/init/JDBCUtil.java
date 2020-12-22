package init;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {
	public static void rollback(Connection conn) {
		try {
			if(conn != null) {
				conn.rollback();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(AutoCloseable... ins) {
		for(AutoCloseable i : ins) {
			if(i != null) {
				try {
					i.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
