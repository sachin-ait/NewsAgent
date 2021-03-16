package di;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.mysql.cj.jdbc.exceptions.SQLError;

import base.MysqlJDBC;

import java.sql.ResultSet;

public class InvoiceMySQLAccess {

	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public InvoiceMySQLAccess() throws DIExceptionHandler {
		connect = MysqlJDBC.getConnection();
	}

	public static boolean insertDInvoiceDetails(DI c) {

		boolean insertSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareCall("call create_Manual(?, ?, ?, ?)");
			preparedStatement.setString(1, c.getDate());
			preparedStatement.setInt(2, c.getSuccess());
			preparedStatement.setInt(3, c.getFailed());
			preparedStatement.setDouble(4, c.getPay());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			insertSucessfull = false;
		}

		return insertSucessfull;

	}// end insertCustomerDetailsAccount

	public static ResultSet retrieveAllDInvoices() {

		// Add Code here to call embedded SQL to view Customer Details

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.DeliveryInvoices");

		} catch (Exception e) {
			resultSet = null;
		}
		return resultSet;
	}

	public static boolean deleteDIById(int DIID) {

		boolean deleteSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			if (DIID == -99) {
				// Delete all entries in Table
				statement = connect.createStatement();
				statement.executeUpdate("Delete from DeliveryInvoices");
			} else {
				// Delete a particular Customer
				preparedStatement = connect.prepareCall("call delete_Invoice(?)");
				preparedStatement.setInt(1, DIID);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			deleteSucessfull = false;
		}

		return deleteSucessfull;

	}

	public static boolean updateDIById(int DIID, int DISuccess, int DIFailure, double DIPay) {

		boolean updateSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {
			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("call update_Invoice(?, ?, ?, ?)");
			DI.validateSuccess(DISuccess);
			DI.validateFailed(DIFailure);
			DI.validatePay(DIPay);
			preparedStatement.setInt(1, DIID);
			preparedStatement.setInt(2, DISuccess);
			preparedStatement.setInt(3, DIFailure);
			preparedStatement.setDouble(4, DIPay);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			updateSucessfull = false;
		}

		return updateSucessfull;

	}

}// end Class
