package publications;

import java.sql.*;

public class JDBCUtils {
    public static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/newsagent";
    private static String username = "root";
    private static String password = "root1234";

//    static {
//        ResourceBundle rb = ResourceBundle.getBundle("mysql_jdbc"); //package java.util.
//        driver = rb.getString("jdbc.driver");
//        url = rb.getString("jdbc.url");
//        user = rb.getString("jdbc.username");
//        password = rb.getString("jdbc.password");
//
//    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
