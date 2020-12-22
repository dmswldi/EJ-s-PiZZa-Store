package init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url;
	private static String user;
	private static String pw;
	
	static void setConnection(String url, String user, String pw) {
		ConnectionProvider.url = url;
		ConnectionProvider.user = user;
		ConnectionProvider.pw = pw;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
			
		try {
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("Successfully Connection!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
