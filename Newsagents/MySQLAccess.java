import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;


public class MySQLAccess {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	final private String host ="localhost:3306";
	final private String user = "root";
	final private String password = "Phillyx2";
	
	
	public MySQLAccess() throws Exception {
		
		try {
			
			//Load MySQL Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://" + host + "/NewsAgents2021?" + "user=" + user + "&password=" + password);
		}
		catch (Exception e) {
			throw e;
		}
		
		
	}	

	public boolean insertDADetailsAccount(DA c) {
	
		boolean insertSucessfull = true;
	
		//Add Code here to call embedded SQL to insert Customer into DB
	
		try {
		
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareCall("call create_Agent(?, ?, ?, ?)");
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getArea());
			preparedStatement.setInt(3, c.getPayRate());
			preparedStatement.setInt(4, c.getHrsLogged());
			preparedStatement.executeUpdate();
		
	 
		}
		catch (Exception e) {
			insertSucessfull = false;
		}
	
		return insertSucessfull;
		
	}// end insertCustomerDetailsAccount

	public ResultSet retrieveAllDAAccounts() {
		
		//Add Code here to call embedded SQL to view Customer Details
	
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("Select * from NewsAgents2021.DeliveryAgents");
		
		}
		catch (Exception e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	public boolean deleteDAById(int DAID) {

		boolean deleteSucessfull = true;
		
		//Add Code here to call embedded SQL to insert Customer into DB
		
		try {
			
			//Create prepared statement to issue SQL query to the database
			if (DAID == -99)
				//Delete all entries in Table
				preparedStatement = connect.prepareStatement("delete from NewsAgents2021.DeliveryAgents");
			else
				//Delete a particular Customer
				preparedStatement = connect.prepareCall("call delete_Agent(?)");
				preparedStatement.setInt(1, DAID);
				preparedStatement.executeUpdate();
		 
		}
		catch (Exception e) {
			deleteSucessfull = false;
		}
		
		return deleteSucessfull;
		
	}
	public boolean updateDAById(int DAID, String DAName, String DAArea, int DAPayRate, int DAhrsLogged) {

		boolean updateSucessfull = true;
		
		//Add Code here to call embedded SQL to insert Customer into DB
		
		try {
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("call update_Agent(?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, DAID);
			preparedStatement.setString(2, DAName);
			preparedStatement.setString(3, DAArea);
			preparedStatement.setInt(4, DAPayRate);
			preparedStatement.setInt(5, DAhrsLogged);
			preparedStatement.executeUpdate();
		 
		}
		catch (Exception e) {
			updateSucessfull = false;
		}
		
		return updateSucessfull;
		
	}


}// end Class

