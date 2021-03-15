package report;

import DeliveryOrder.DeliveryOrder;
import base.MysqlJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportSQLAccess {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public ReportSQLAccess() {

	}

	public Statement getConnectionStatement() {
		try {

			connect= MysqlJDBC.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public boolean insertDeliveryOrderDetailsAccount(DeliveryOrder d) {
		
		boolean insertSucessfull = true;
	
		//Add Code here to call embedded SQL to insert Customer into DB
	
		try {
		
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into DeliveryOrder values (DEFAULT, ?, ?, ?)");
			preparedStatement.setString(1, d.getName());
			preparedStatement.setString(2, d.getPublication());
			preparedStatement.setString(3, d.getDate());

			preparedStatement.executeUpdate();
		
	 
		}
		catch (Exception e) {
			e.printStackTrace();
			insertSucessfull = false;
		}
	
		return insertSucessfull;
		
	}// end insertCustomerDetailsAccount
	


	public ResultSet retrieveAllDeliveryOrderAccounts() {
		
		//Add Code here to call embedded SQL to view Customer Details
	
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.DeliveryOrder");
		
		}
		catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}
		return resultSet;
	}
	
	public boolean deleteDeliveryOrderById(int deliveryOrderID) {

		boolean deleteSucessfull = true;
		
		//Add Code here to call embedded SQL to insert Customer into DB
		
		try {
			
			//Create prepared statement to issue SQL query to the database
			if (deliveryOrderID == -99)
				//Delete all entries in Table
				preparedStatement = connect.prepareStatement("delete from newsagent.DeliveryOrder");
			else
				//Delete a particular Customer
				preparedStatement = connect.prepareStatement("delete from newsagent.DeliveryOrder where DeliveryOrderID = " + deliveryOrderID);
			preparedStatement.executeUpdate();
		 
		}
		catch (Exception e) {
			deleteSucessfull = false;
		}
		
		return deleteSucessfull;
		
	}
	
	public boolean updateDeliveryOrderById(int deliveryOrderID,String updateChoice,String adjustion) {

		boolean updateSucessful = true;
		
		//Add Code here to call embedded SQL to insert Customer into DB
		
		try {
			
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("update DeliveryOrder set "+ updateChoice +" = '" + adjustion + "' where custID= "+deliveryOrderID);
			preparedStatement.executeUpdate();
		 
		}
		catch (Exception e) {
			e.printStackTrace();
			updateSucessful = false;
		}
		
		return updateSucessful;
		
	}
	
	
}

