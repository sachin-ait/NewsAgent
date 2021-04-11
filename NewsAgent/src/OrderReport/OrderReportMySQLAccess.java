package OrderReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DeliveryOrder.DeliveryOrder;

public class OrderReportMySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost:3306";
	final private String user = "root";
	final private String password = "1234";

	public OrderReportMySQLAccess() {
		connect= base.MysqlJDBC.getConnection();
	}

	public Statement getConnectionStatement() {
		try {
			return connect.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertOrderReportDetailsAccount(String name,String publication,String date,String status, int id) {

		boolean insertSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into OrderReport values (DEFAULT, ?, ?, ?, ?, ?)");			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, publication);
			preparedStatement.setString(3, date);
			preparedStatement.setString(4, status);
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSucessfull = false;
		}

		return insertSucessfull;

	}// end insertCustomerDetailsAccount

	public ResultSet retrieveAllUpdatedOrderReportAccounts() {

		// Add Code here to call embedded SQL to view Customer Details

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.OrderReport where Status = 'UPD';");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}
		return resultSet;
	}
	public ResultSet retrieveAllDeletedOrderReportAccounts() {

		// Add Code here to call embedded SQL to view Customer Details

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.OrderReport where Status = 'DEL';");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}
		return resultSet;
	}
}
