package DeliveryOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import DeliveryOrder.DeliveryOrder;
import DeliveryOrder.DeliveryOrderExceptionHandler;
import DeliveryOrder.DeliveryOrderMySQLAccess;
import base.MysqlJDBC;
import junit.framework.TestCase;

public class DeliveryOrderTest extends TestCase {

	// Test #: 1
	// Test Objective: To create a Delivery Order Object
	// Inputs: custName = "Jack", publicationName = "StarryNight", date =
	// "13/2/2021"
	// Expected Output: Delivery Order Object created with custName = "Jack",
	// publicationName = "StarryNight", date = "13/2/2021"

	public void testPublication001() {

		// Create the Publication Object

		try {

			// Call method under test
			DeliveryOrder deliveryObj = new DeliveryOrder("Jack","Athlone, 12 Cartrontroy", "StarryNight", "February");

			// Use getters to check for object creation
			assertEquals("Jack", deliveryObj.getName());
			assertEquals("12 Cartrontroy", deliveryObj.getAddress());
			assertEquals("StarryNight", deliveryObj.getPublication());
			assertEquals("February", deliveryObj.getDate());
		} catch (DeliveryOrderExceptionHandler e) {
			fail("Exception not expected");
		}

	}

	// Test #: 2
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = "J"
	// Expected Output: Exception Message: "DeliveryOrder Name does not meet minimum
	// length requirements"

	public void testValidateName001() {

		try {

			// Call method under test
			DeliveryOrder.validateName("J");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("DeliveryOrder Name does not meet minimum length requirements", e.getMessage());
		}
	}

	// Test #: 3
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = ""
	// Expected Output: Exception Message: "DeliveryOrder Name NOT specified"

	public void testValidateName002() {

		try {

			// Call method under test
			DeliveryOrder.validateName("");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("DeliveryOrder Name NOT specified", e.getMessage());
		}
	}
	// Test #: 4
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = "This sentence has to be more than 30 words in order to
	// test JUnit"
	// Expected Output: Exception Message: "DeliveryOrder Name does not exceeds
	// maximum length requirements"

	public void testValidateName003() {

		try {

			// Call method under test
			DeliveryOrder.validateName("This sentence has to be more than 30 words in order to test JUnit");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("DeliveryOrder Name does not exceeds maximum length requirements", e.getMessage());
		}
	}
	// Test #: 5
	// Test Objective: To catch an invalid publication name
	// Inputs: publicationName = ""
	// Expected Output: Exception Message: "Publication Name NOT specified"

	public void testValidatePublicationName001() {

		try {

			// Call method under test
			DeliveryOrder.validatePublication("");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Publication Name NOT specified", e.getMessage());
		}

	}
	// Test #: 6
	// Test Objective: To catch an invalid publication name
	// Inputs: publicationName = "J"
	// Expected Output: Exception Message: "Publication Name does not meet minimum
	// length requirements"

	public void testValidatePublicationName002() {

		try {

			// Call method under test
			DeliveryOrder.validatePublication("J");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Publication Name does not meet minimum length requirements", e.getMessage());
		}

	}
	// Test #: 7
	// Test Objective: To catch an invalid publication name
	// Inputs: publicationName = "This sentence has to be more than 30 words in
	// order to test JUnit"
	// Expected Output: Exception Message: "Publication Name does not exceeds
	// maximum length requirements"

	public void testValidatePublicationName003() {

		try {

			// Call method under test
			DeliveryOrder.validatePublication("This sentence has to be more than 30 words in order to test JUnit");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Publication Name does not exceeds maximum length requirements", e.getMessage());
		}

	}
	// Test #: 8
	// Test Objective: To catch an invalid deliver date
	// Inputs: publicationName = ""
	// Expected Output: Exception Message: "Delivery Date NOT specified"

	public void testValidateDeliveryDate001() {

		try {

			// Call method under test
			DeliveryOrder.validateDate("");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Delivery Date NOT specified", e.getMessage());
		}

	}
	// Test #: 9
	// Test Objective: To catch an invalid deliver date
	// Inputs: publicationName = "J"
	// Expected Output: Exception Message: "Delivery Date does not meet minimum
	// length requirements"

	public void testValidateDeliveryDate002() {

		try {

			// Call method under test
			DeliveryOrder.validateDate("J");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Delivery Date does not meet minimum length requirements", e.getMessage());
		}

	}
	// Test #: 10
	// Test Objective: To catch an invalid deliver date
	// Inputs: publicationName = "Jjjjjjjjjjjjjjjjj"
	// Expected Output: Exception Message: "Delivery Date does not exceeds maximum
	// length requirements"

	public void testValidateDeliveryDate003() {

		try {

			// Call method under test
			DeliveryOrder.validateDate("Jjjjjjjjjjjjjjjjj");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Delivery Date does not exceeds maximum length requirements", e.getMessage());
		}

	}

	// Test #: 11
	// Test Objective: To test create delivery order object in database
	// Inputs: custName = "Jack", "StarryNight", "13/2/2021"
	// Expected Output: "DeliveryOrder Object created "Jack", "StarryNight",
	// "13/2/2021""
	public void testInsertDeliveryOrderDetailsAccount001() {
		try {
			boolean insert = true;

			DeliveryOrderMySQLAccess dao = new DeliveryOrderMySQLAccess();
			dao.getConnectionStatement();

			DeliveryOrder DeliveryOrderObj = new DeliveryOrder("Jack","Athlone, 12 Cartrontroy", "StarryNight", "February");
			assertEquals(insert, dao.insertDeliveryOrderDetailsAccount(DeliveryOrderObj));
		} catch (DeliveryOrderExceptionHandler e) {
			fail("Exception unexpected");
		}
	}

	// Test #: 12
	// Test Objective: To test the retrieve delivery order query
	// Inputs: Query = "Select * from newsagent.DeliveryOrder"
	// Expected Output: "DeliveryOrder Table is not null"
	public void testRetrieveDeliveryOrderDetailsAccount001() throws DeliveryOrderExceptionHandler {
		try {
			Connection connect = null;
			connect = MysqlJDBC.getConnection();
			DeliveryOrderMySQLAccess dao = new DeliveryOrderMySQLAccess();
			dao.getConnectionStatement();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("Select * from newsagent.DeliveryOrder");
			assertTrue(dao.retrieveAllDeliveryOrderAccounts() != null);
		} catch (SQLException e) {
			fail("Exception unexpected");

		}
	}

	// Test #: 13
	// Test Objective: To test the delete function of delivery order
	// Inputs: deliveryOrderId = "1"
	// Expected Output: "DeliveryOrder Id deleted"
	public void testDeleteDeliveryOrderById001() {
		boolean delete = true;

		try {
			DeliveryOrderMySQLAccess dao = new DeliveryOrderMySQLAccess();
			dao.getConnectionStatement();
			int id = 1;
			assertEquals(delete, dao.deleteDeliveryOrderById(id));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}

	// Test #: 14
	// Test Objective: To test the delete function of delivery order
	// Inputs: custId = "-99"
	// Expected Output: "DeliveryOrder Table emptied"
	public void testDeleteDeliveryOrderById002() {
		boolean delete = true;

		try {
			DeliveryOrderMySQLAccess dao = new DeliveryOrderMySQLAccess();
			dao.getConnectionStatement();
			int id = -99;
			assertEquals(delete, dao.deleteDeliveryOrderById(id));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}

	// Test #: 15
	// Test Objective: To test the update function of customer
	// Inputs: custId = "1" updateChoice = "CustName " adjustion = "Leon"
	// Expected Output: Exception Message: "DeliveryOrder Area NOT specified"
	public void testUpdateDeliveryOrderById001() {
		boolean update = true;

		try {
			DeliveryOrderMySQLAccess dao = new DeliveryOrderMySQLAccess();
			dao.getConnectionStatement();
			int id = 2;
			String updateChoice = "CustName";
			String adjustion = "Leon";

			assertEquals(update, dao.updateDeliveryOrderById(id, updateChoice, adjustion));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}
	// Test #: 16
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = "123456"
	// Expected Output: Exception Message: "DeliveryOrder Name contains Numeric"

	public void testValidateName004() {

		try {
			// Call method under test
			DeliveryOrder.validateName("123456");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("DeliveryOrder Name contains Numeric", e.getMessage());
		}
	}
	// Test #: 17
	// Test Objective: To catch an invalid publication name
	// Inputs: custName = "123456"
	// Expected Output: Exception Message: "Publication Name contains Numeric"

	public void testValidateName005() {

		try {
			// Call method under test
			DeliveryOrder.validatePublication("123456");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Publication Name contains Numeric", e.getMessage());
		}
	}

	// Test #: 18
	// Test Objective: To test the update function of customer
	// Inputs: custId = "1" updateChoice = "CustName " adjustion = "Leon"
	// Expected Output: Exception Message: "DeliveryOrder Area NOT specified"
	public void testUpdateDeliveryOrderById002() {
		boolean update = true;

		try {
			DeliveryOrderMySQLAccess dao = new DeliveryOrderMySQLAccess();
			dao.getConnectionStatement();
			int id = 2;
			String doName = "mok";
			String doAddress = "Willow Park";
			String doPublication = "ring";
			String doDate = "12/02/2021";

			assertEquals(update, dao.updateDeliveryOrderById(id, doName, "Willow Park", doPublication,doDate));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}
}
