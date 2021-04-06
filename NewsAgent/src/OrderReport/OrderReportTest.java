package OrderReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import base.MysqlJDBC;
import junit.framework.TestCase;

public class OrderReportTest extends TestCase {
	// Test #: 1
	// Test Objective: To test create order report object in database
	// Inputs: custName = "Jack", "StarryNight", "13/2/2021","UPD",3
	// Expected Output: ""Jack", "StarryNight", "13/2/2021","UPD",3"
	public void testInsertDeliveryOrderDetailsAccount001() {
		try {
			boolean insert = true;

			OrderReportMySQLAccess dao = new OrderReportMySQLAccess();
			dao.getConnectionStatement();

			assertEquals(insert, dao.insertOrderReportDetailsAccount("Jack", "StarryNight", "13/2/2021","UPD",3));
		} catch (Exception e) {
			fail("Exception unexpected");
		}
	}

	// Test #: 2
	// Test Objective: To test the retrieve order report query
	// Inputs: Query = "Select * from newsagent.OrderReport where Status = 'DEL';"
	// Expected Output: "Order Report Table is not null"
	public void testRetrieveAllDeletedOrder001() {
		try {
			Connection connect = null;
			connect = MysqlJDBC.getConnection();
			OrderReportMySQLAccess dao = new OrderReportMySQLAccess();
			dao.getConnectionStatement();
			Statement statement = connect.createStatement();		
			statement.executeQuery("Select * from newsagent.OrderReport where Status = 'DEL';");
			
			assertTrue(dao.retrieveAllDeletedOrderReportAccounts() != null);
		} catch (SQLException e) {
			fail("Exception unexpected");

		}
	}
	// Test #: 2
		// Test Objective: To test the retrieve order report query
	// Inputs: Query = "Select * from newsagent.OrderReport where Status = 'UPD';"
	// Expected Output: "Order Report Table is not null"
		public void testRetrieveAllUpdatedOrder001() {
			try {
				Connection connect = null;
				connect = MysqlJDBC.getConnection();
				OrderReportMySQLAccess dao = new OrderReportMySQLAccess();
				dao.getConnectionStatement();
				Statement statement = connect.createStatement();		
				statement.executeQuery("Select * from newsagent.OrderReport where Status = 'UPD';");
				
				assertTrue(dao.retrieveAllUpdatedOrderReportAccounts() != null);
			} catch (SQLException e) {
				fail("Exception unexpected");

			}
		}
}
