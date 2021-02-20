package cust;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	final private String host ="localhost:3306";
	final private String user = "root";
	final private String password = "1234";
	
	public JDBC() {

	}

	public Statement getConnectionStatement() {
        connect = base.MysqlJDBC.getConnection();
		return null;
	}

	
	public boolean insertCustomerDetailsAccount(Customer c) {
		
		boolean insertSucessfull = true;
	
		//Add Code here to call embedded SQL to insert Customer into DB
	
		try {
		
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into Customer values (DEFAULT, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getAddress());
			preparedStatement.setString(3, c.getPhoneNumber());
			preparedStatement.setDouble(5, c.getPayment());
			preparedStatement.setString(6, c.getArea());
			preparedStatement.executeUpdate();
		
	 
		}
		catch (Exception e) {
			e.printStackTrace();
			insertSucessfull = false;
		}
	
		return insertSucessfull;
		
	}// end insertCustomerDetailsAccount
	


	public ResultSet retrieveAllCustomerAccounts() {
		
		//Add Code here to call embedded SQL to view Customer Details
	
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from newsagent.Customer");
		
		}
		catch (Exception e) {
			e.printStackTrace();
			resultSet = null;
		}
		return resultSet;
	}
	
	public boolean deleteCustomerById(int custID) {

		boolean deleteSucessfull = true;
		
		//Add Code here to call embedded SQL to insert Customer into DB
		
		try {
			
			//Create prepared statement to issue SQL query to the database
			if (custID == -99)
				//Delete all entries in Table
				preparedStatement = connect.prepareStatement("delete from newsagent.customer");
			else
				//Delete a particular Customer
				preparedStatement = connect.prepareStatement("delete from newsagent.customer where custID = " + custID);
			preparedStatement.executeUpdate();
		 
		}
		catch (Exception e) {
			deleteSucessfull = false;
		}
		
		return deleteSucessfull;
		
	}
	
	public boolean updateCustomerById(int custID,String updateChoice,String adjustion) {

		boolean updateSucessful = true;
		
		//Add Code here to call embedded SQL to insert Customer into DB
		
		try {
			
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("update customer set "+ updateChoice +" = '" + adjustion + "' where custID= "+custID);
			preparedStatement.executeUpdate();
		 
		}
		catch (Exception e) {
			e.printStackTrace();
			updateSucessful = false;
		}
		
		return updateSucessful;
		
	}
	
	
}
