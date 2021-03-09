package di;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import base.MysqlJDBC;

import java.sql.ResultSet;

public class InvoiceMySQLAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public InvoiceMySQLAccess() throws Exception {
		connect = MysqlJDBC.getConnection();
	}

	public boolean insertDInvoiceDetails(DI c) {

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

	public ResultSet retrieveAllDInvoices() {

		// Add Code here to call embedded SQL to view Customer Details

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.DeliveryInvoices");

		} catch (Exception e) {
			resultSet = null;
		}
		return resultSet;
	}

	public boolean deleteDIById(int DIID) {

		boolean deleteSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			if (DIID == -99)
				// Delete all entries in Table
				preparedStatement = connect.prepareStatement("delete * from newsagent.DeliveryInvoices");
			else
				// Delete a particular Customer
				preparedStatement = connect.prepareCall("call delete_Invoice(?)");
				preparedStatement.setInt(1, DIID);
				preparedStatement.executeUpdate();

		} catch (Exception e) {
			deleteSucessfull = false;
		}

		return deleteSucessfull;

	}

	public boolean updateDIById(int DIID, int DISuccess, int DIFailure, double DIPay) {

		boolean updateSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {
			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("call update_Invoice(?, ?, ?, ?)");
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
