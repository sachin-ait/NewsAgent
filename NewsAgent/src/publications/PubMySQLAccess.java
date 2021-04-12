package publications;

import java.sql.*;

import base.MysqlJDBC;
import da.DAExceptionHandler;

public class PubMySQLAccess {

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

		private static Connection connect = null;
		private static Statement statement = null;
		private static PreparedStatement preparedStatement = null;
		private static ResultSet resultSet = null;

		public PubMySQLAccess() throws PubExceptionHandler {
			connect = MysqlJDBC.getConnection();
		}

		public static boolean insertPub(Publication c) throws PubExceptionHandler {
			boolean insertSucessfull = true;

			// Add Code here to call embedded SQL to insert Customer into DB

			try {
				
				// Create prepared statement to issue SQL query to the database
				preparedStatement = connect.prepareStatement("Insert into newsagent.publication values (DEFAULT, ?, ?, ?, ?)");
				preparedStatement.setString(1, c.getName());
				preparedStatement.setInt(2, c.getAmount());
				preparedStatement.setDouble(3, c.getPrice());
				preparedStatement.setString(4, c.getFreq());
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				insertSucessfull = false;
			}

			return insertSucessfull;

		}// end insertCustomerDetailsAccount

		public static ResultSet retrieveAllPub() throws PubExceptionHandler {

			// Add Code here to call embedded SQL to view Customer Details

			try {
				statement = connect.createStatement();
				resultSet = statement.executeQuery("Select * from newsagent.publication");

			} catch (SQLException e) {
				resultSet = null;
			}
			return resultSet;
		}

		public static boolean deletePubById(int PubID) throws PubExceptionHandler {

			boolean deleteSucessfull = true;

			// Add Code here to call embedded SQL to insert Customer into DB

			try {

				// Create prepared statement to issue SQL query to the database
				if (PubID == -99) {
					// Delete all entries in Table
					statement = connect.createStatement();
					statement.executeUpdate("Delete from DeliveryAgents");
				} else {
					// Delete a particular Customer
					preparedStatement = connect.prepareStatement("delete from newsagent.publication where id = " + PubID);
					preparedStatement.executeUpdate();
				}
			} catch (SQLException e) {
				deleteSucessfull = false;
			}

			return deleteSucessfull;

		}

		public static boolean updatePubById(int PubID, String PubName, int PubAmount, double PubPrice, String PubFreq)
				throws PubExceptionHandler {

			boolean updateSucessfull = true;

			// Add Code here to call embedded SQL to insert Customer into DB

			try {
				// Create prepared statement to issue SQL query to the database
				preparedStatement = connect.prepareStatement("update publication set name = '" + PubName + "',stock = '"
						+ PubAmount + "',price = '" + PubPrice + "',frequence = '" + PubFreq +"' where id= " + PubID);
				Publication.validateName(PubName);
				Publication.validateAmount(PubAmount);
				Publication.validatePrice(PubPrice);
				Publication.validateFreq(PubFreq);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				updateSucessfull = false;
				System.out.println(e);
			}

			return updateSucessfull;

		}


	}// end Class
