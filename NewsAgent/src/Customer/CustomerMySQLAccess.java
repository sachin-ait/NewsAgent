package Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import base.MysqlJDBC;

public class CustomerMySQLAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public CustomerMySQLAccess() {
		connect= MysqlJDBC.getConnection();
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

	public boolean insertCustomerDetailsAccount(Customer c) {

		boolean insertSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into Customer values (DEFAULT, ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getAddress());
			preparedStatement.setString(3, c.getPhoneNumber());
			preparedStatement.setDouble(4, c.getPayment());
			preparedStatement.setString(5, c.getArea());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			insertSucessfull = false;
		}

		return insertSucessfull;

	}// end insertCustomerDetailsAccount

	public ResultSet retrieveAllCustomerAccounts() {

		// Add Code here to call embedded SQL to view Customer Details

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.Customer;");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}
		return resultSet;
	}

	public boolean deleteCustomerById(int custID) {

		boolean deleteSucessfull = false;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			if (custID == -99) {
				// Delete all entries in Table
				preparedStatement = connect.prepareStatement("delete from newsagent.customer");
				deleteSucessfull = true;
			}

			else {
				// Delete a particular Customer
				preparedStatement = connect.prepareStatement("delete from newsagent.customer where custID = " + custID);
				preparedStatement.executeUpdate();
				deleteSucessfull = true;
			}

		} catch (Exception e) {
			deleteSucessfull = false;
		}

		return deleteSucessfull;

	}

	public boolean updateCustomerById(int custID, String updateName, String updateAddress, String updatePhone,
			double updatePayment, String updateArea) {

		boolean updateSucessful = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database

			preparedStatement = connect.prepareStatement("update customer set Name = '" + updateName + "',Address = '"
					+ updateAddress + "',PhoneNumber = '" + updatePhone + "',Payment = '" + updatePayment + "',Area = '"
					+ updateArea + "' where custID= " + custID);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSucessful = false;
		}

		return updateSucessful;

	}

	public boolean updateCustomerById(int custID, String updateChoice, String adjustion) {

		boolean updateSucessful = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement(
					"update customer set " + updateChoice + " = '" + adjustion + "' where custID= " + custID);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			updateSucessful = false;
		}

		return updateSucessful;

	}
	public ResultSet retrieveAllArea() {

		// Add Code here to call embedded SQL to view Customer Details

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select Agent_Area from newsagent.DeliveryAgents;");

		} catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}
		return resultSet;
	}
}
