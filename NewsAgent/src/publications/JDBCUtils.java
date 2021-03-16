package publications;

import java.sql.*;

public class JDBCUtils {
	public static Connection getConnection() {
		Connection conn = base.MysqlJDBC.getConnection();
		return conn;
	}

	public static void closeConnection(ResultSet re, Statement stmt, Connection conn) {
		if (re != null) {
			try {
				re.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
