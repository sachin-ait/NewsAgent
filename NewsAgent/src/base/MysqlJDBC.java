package base;

import java.sql.Connection;
import java.sql.DriverManager;

import da.DAExceptionHandler;

public class MysqlJDBC {

	final static String userName = "agent";
	final static String password = "password";
	static Connection connection;

	public MysqlJDBC() {

	}

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {

				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost/newsagent?user=" + userName + "&password=" + password);

			}
		} catch (Exception e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
			return null;
		}
		return connection;
	}

	public static void closeConnection() throws DAExceptionHandler {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		} catch (Exception e) {
			System.out.println("Failed to close connection");
			e.printStackTrace();
		}
	}

}
