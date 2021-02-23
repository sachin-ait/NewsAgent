package DeliveryOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import OrderReport.OrderReport;

public class MySQLAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost:3306";
	final private String user = "root";
	final private String password = "1234";

	public MySQLAccess() {

	}

	public Statement getConnectionStatement() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			connect = DriverManager
					.getConnection("jdbc:mysql://" + host + "/newsagent?" + "user=" + user + "&password=" + password);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertDeliveryOrderDetailsAccount(DeliveryOrder d) {

		boolean insertSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into DeliveryOrder values (DEFAULT, ?, ?, ?)");
			preparedStatement.setString(1, d.getName());
			preparedStatement.setString(2, d.getPublication());
			preparedStatement.setString(3, d.getDate());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSucessfull = false;
		}

		return insertSucessfull;

	}// end insertCustomerDetailsAccount

	public ResultSet retrieveAllDeliveryOrderAccounts() {

		// Add Code here to call embedded SQL to view Customer Details

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.DeliveryOrder");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}
		return resultSet;
	}

	public boolean deleteDeliveryOrderById(int deliveryOrderID) {

		boolean deleteSucessful = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {
			readInfo(deliveryOrderID);

			// Create prepared statement to issue SQL query to the database
			if (deliveryOrderID == -99)
				// Delete all entries in Table
				preparedStatement = connect.prepareStatement("delete from newsagent.DeliveryOrder");
			else
				// Delete a particular Customer
				preparedStatement = connect.prepareStatement(
						"delete from newsagent.DeliveryOrder where DeliveryOrderID = " + deliveryOrderID);
			preparedStatement.executeUpdate();

			OrderReport.OrderReport(resultSet, 0);

		} catch (Exception e) {
			e.printStackTrace();
			deleteSucessful = false;
		}

		return deleteSucessful;

	}

	public boolean updateDeliveryOrderById(int deliveryOrderID, String updateChoice, String adjustion) {

		boolean updateSucessful = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {
			readInfo(deliveryOrderID);
			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("update DeliveryOrder set " + updateChoice + " = '" + adjustion
					+ "' where DeliveryOrderID= " + deliveryOrderID);
			preparedStatement.executeUpdate();
			OrderReport.OrderReport(resultSet, 1);
		} catch (Exception e) {
			e.printStackTrace();
			updateSucessful = false;
		}

		return updateSucessful;

	}

	public ResultSet readInfo(int id) {
		try {
			resultSet = statement.executeQuery("select * from newsagent.DeliveryOrder where DeliveryOrderId = " + id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

}
