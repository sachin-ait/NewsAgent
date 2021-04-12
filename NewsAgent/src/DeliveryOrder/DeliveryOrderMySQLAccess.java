package DeliveryOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import OrderReport.OrderReportMySQLAccess;
import base.MysqlJDBC;

public class DeliveryOrderMySQLAccess {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private OrderReportMySQLAccess orderReportSQL;
	
	
	public DeliveryOrderMySQLAccess() {
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


	public boolean insertDeliveryOrderDetailsAccount(DeliveryOrder d) {

		boolean insertSucessfull = true;
		System.out.println(d.getCustId());
		System.out.println(d.getName());
		System.out.println(d.getAddress());
		System.out.println(d.getPublication());
		System.out.println(d.getOrderamount());
		System.out.println(d.getDate());
		// Add Code here to call embedded SQL to insert Customer into DB

		try {
			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into DeliveryOrder(DeliveryOrderId,CustID,CustName,CustAddress,PublicationName,PAmount,DeliveryDate) values (DEFAULT, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, d.getCustId());
			preparedStatement.setString(2, d.getName());
			preparedStatement.setString(3, d.getAddress());
			preparedStatement.setString(4, d.getPublication());
			preparedStatement.setInt(5, d.getOrderamount());
			preparedStatement.setString(6, d.getDate());
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
		String status = "DEL";
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

			//String doName = resultSet.getString("CustName");
			//String doPublication = resultSet.getString("PublicationName");
			//String doDate = resultSet.getString("DeliveryDate");
			//orderReportSQL.insertOrderReportDetailsAccount(doName, doPublication, doDate, status, deliveryOrderID);

		} catch (Exception e) {
			e.printStackTrace();
			deleteSucessful = false;
		}

		return deleteSucessful;

	}

	/*
	 * public boolean updateDeliveryOrderById(int deliveryOrderID, String
	 * updateChoice, String adjustion) {
	 * 
	 * boolean updateSucessful = true;
	 * 
	 * // Add Code here to call embedded SQL to insert Customer into DB
	 * 
	 * try { readInfo(deliveryOrderID); // Create prepared statement to issue SQL
	 * query to the database preparedStatement =
	 * connect.prepareStatement("update DeliveryOrder set " + updateChoice + " = '"
	 * + adjustion + "' where DeliveryOrderID= " + deliveryOrderID);
	 * preparedStatement.executeUpdate(); } catch (Exception e) {
	 * e.printStackTrace(); updateSucessful = false; }
	 * 
	 * return updateSucessful;
	 * 
	 * }
	 */

	public boolean updateDeliveryOrderById(int deliveryOrderID, String doName, int CID, String doAddress,
			String doPublication, String doDate, int doPubamount) {

		boolean updateSucessful = true;
		String status = "UPD";
		// Add Code here to call embedded SQL to insert Customer into DB

		try {
			readInfo(deliveryOrderID);
			// Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("update DeliveryOrder set CustID = " + CID + ",CustName = '"
					+ doName + "',CustAddress  = '" + doAddress + "', PublicationName = '" + doPublication
					+ "', PAmount  = '" + doPubamount	+ "', DeliveryDate  = '" + doDate + "' where DeliveryOrderID = " + deliveryOrderID);
			preparedStatement.executeUpdate();

			//orderReportSQL.insertOrderReportDetailsAccount(doName, doPublication, doDate, status, deliveryOrderID);

		} catch (Exception e) {
			e.printStackTrace();
			updateSucessful = false;
		}

		return updateSucessful;

	}

	public ResultSet readInfo(int id) {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from newsagent.DeliveryOrder where DeliveryOrderId = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

}
