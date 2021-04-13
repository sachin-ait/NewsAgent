package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import base.MysqlJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AgentMySQLAccess {

	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public AgentMySQLAccess() throws DAExceptionHandler {
		connect = MysqlJDBC.getConnection();
	}

	public static boolean insertDADetailsAccount(DA c) throws DAExceptionHandler {

		boolean insertSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareCall("call create_Agent(?, ?, ?, ?)");
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getArea());
			preparedStatement.setInt(3, c.getPayRate());
			preparedStatement.setInt(4, c.getHrsLogged());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			insertSucessfull = false;
		}

		return insertSucessfull;

	}// end insertCustomerDetailsAccount

	public ResultSet retrieveAllDAAccounts() throws DAExceptionHandler {

		// Add Code here to call embedded SQL to view Customer Details

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.DeliveryAgents");

		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}

	public static boolean deleteDAById(int DAID) throws DAExceptionHandler {

		boolean deleteSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {

			// Create prepared statement to issue SQL query to the database
			if (DAID == -99) {
				// Delete all entries in Table
				statement = connect.createStatement();
				statement.executeUpdate("Delete from DeliveryAgents");
			} else {
				// Delete a particular Customer
				preparedStatement = connect.prepareCall("call delete_Agent(?)");
				preparedStatement.setInt(1, DAID);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			deleteSucessfull = false;
		}

		return deleteSucessfull;

	}

	public static boolean updateDAById(int DAID, String DAName, String DAArea, int DAPayRate, int DAhrsLogged)
			throws DAExceptionHandler {

		boolean updateSucessfull = true;

		// Add Code here to call embedded SQL to insert Customer into DB

		try {
			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("call update_Agent(?, ?, ?, ?, ?)");
			DA.validateName(DAName);
			DA.validateArea(DAArea);
			DA.validatePayRate(DAPayRate);
			DA.validateLoggedHrs(DAhrsLogged);
			preparedStatement.setInt(1, DAID);
			preparedStatement.setString(2, DAName);
			preparedStatement.setString(3, DAArea);
			preparedStatement.setInt(4, DAPayRate);
			preparedStatement.setInt(5, DAhrsLogged);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			updateSucessfull = false;
		}

		return updateSucessfull;

	}


}// end Class
