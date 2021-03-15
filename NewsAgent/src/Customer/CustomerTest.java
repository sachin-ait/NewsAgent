package Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DeliveryOrder.DeliveryOrder;
import DeliveryOrder.DeliveryOrderExceptionHandler;
import base.MysqlJDBC;
import junit.framework.TestCase;

public class CustomerTest extends TestCase {

	// Test #: 1
	// Test Objective: To create a Customer Account
	// Inputs: custName = "Jack Daniels", custAddr = "Athlone", custPhone =
	// "087-123123123"
	// Expected Output: Customer Object created with id = 0, "Jack Daniels",
	// custAddr = "Athlone", custPhone = "087123123123", custDOid = "100

	public void testCustomer001() {

		// Create the Customer Object
		try {

			// Call method under test
			Customer custObj = new Customer("Jack Daniels", "Athlone", "08712312312", 50.5, "Willow Park");

			// Use getters to check for object creation
			assertEquals(0, custObj.getId());
			assertEquals("Jack Daniels", custObj.getName());
			assertEquals("Athlone", custObj.getAddress());
			assertEquals("08712312312", custObj.getPhoneNumber());
			assertEquals(50.5, custObj.getPayment());
			assertEquals("Willow Park", custObj.getArea());
		} catch (CustomerExceptionHandler e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}

	// Test #: 2
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = "J"
	// Expected Output: Exception Message: "Customer Name does not meet minimum
	// length requirements"

	public void testValidateName001() {

		try {

			// Call method under test
			Customer.validateName("J");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer Name does not meet minimum length requirements", e.getMessage());
		}
	}
	// Test #: 3
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = "Jjjjjjjjjjjjjjjjjjjjjj"
	// Expected Output: Exception Message: "Customer Name does not exceeds maximum
	// length requirements"

	public void testValidateName002() {

		try {

			// Call method under test
			Customer.validateName("Jjjjjjjjjjjjjjjjjjjjjj");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer Name does not exceeds maximum length requirements", e.getMessage());
		}
	}

	// Test #: 4
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = ""
	// Expected Output: Exception Message: "Customer Name does not meet minimum
	// length requirements"
	public void testValidateName003() {

		try {

			// Call method under test
			Customer.validateName("");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer Name NOT specified", e.getMessage());
		}
	}

	// Test #: 5
	// Test Objective: To catch an invalid customer address
	// Inputs: custName = ""
	// Expected Output: Exception Message: "Customer Address NOT specified
	// length requirements"

	public void testValidateAddress001() {

		try {

			// Call method under test
			Customer.validateAddress("");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer Address NOT specified", e.getMessage());
		}
	}
	// Test #: 6
	// Test Objective: To catch an invalid customer address
	// Inputs: custName = "Jjjj"
	// Expected Output: Exception Message: "Customer Name does not exceeds maximum
	// length requirements"

	public void testValidateAddress002() {

		try {

			// Call method under test
			Customer.validateAddress("Jjj");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer Address does not meet minimum length requirements", e.getMessage());
		}
	}

	// Test #: 7
	// Test Objective: To catch an invalid customer phone number
	// Inputs: custName = ""
	// Expected Output: Exception Message: "Customer PhoneNumber NOT specified
	// length requirements"
	public void testValidatePhone001() {

		try {

			// Call method under test
			Customer.validatePhoneNumber("");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer PhoneNumber NOT specified", e.getMessage());
		}
	}

	// Test #: 8
	// Test Objective: To catch an invalid customer phone number
	// Inputs: custName = "123"
	// Expected Output: Exception Message: "Customer PhoneNumber does not meet
	// minimum length requirements
	// length requirements"
	public void testValidatePhone002() {

		try {

			// Call method under test
			Customer.validatePhoneNumber("123");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer PhoneNumber does not meet minimum length requirements", e.getMessage());
		}
	}

	// Test #: 9
	// Test Objective: To catch an invalid customer phone number
	// Inputs: custName = "1234567891234"
	// Expected Output: Exception Message: "Customer PhoneNumber does not exceeds
	// maximum length requirements
	// length requirements"
	public void testValidatePhone003() {

		try {

			// Call method under test
			Customer.validatePhoneNumber("123456789123412");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer PhoneNumber does not exceeds maximum length requirements", e.getMessage());
		}
	}

	// Test #: 10
	// Test Objective: To catch an invalid customer area
	// Inputs: custName = ""
	// Expected Output: Exception Message: "Customer Area NOT specified"
	public void testValidateArea001() {

		try {

			// Call method under test
			Customer.validateArea("");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer Area NOT specified", e.getMessage());
		}
	}

	// Test #: 11
	// Test Objective: To test create customer object in database
	// Inputs: custName = "Paul", "Willow", "123456789",321.3,"Willow Park"
	// Expected Output: "Customer Object created "Paul", "Willow",
	// "123456789",321.3,"Willow Park""
	public void testInsertCustomerDetailsAccount001() {
		try {
			boolean insert = true;

			CustomerMySQLAccess dao = new CustomerMySQLAccess();
			dao.getConnectionStatement();

			Customer CustomerObj = new Customer("Paul", "Willow", "123456987", 321.3, "Willow Park");
			assertEquals(insert, dao.insertCustomerDetailsAccount(CustomerObj));
		} catch (CustomerExceptionHandler e) {
			fail("Exception unexpected");
		}
	}

	// Test #: 12
	// Test Objective: To test the retrieve customer query
	// Inputs: Query = "Select * from newsagent.Customer"
	// Expected Output: "Customer Table is not null"
	public void testRetrieveCustomerDetailsAccount001() throws CustomerExceptionHandler {
		try {
			Connection connect = null;
			connect = MysqlJDBC.getConnection();
			CustomerMySQLAccess dao = new CustomerMySQLAccess();
			dao.getConnectionStatement();
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("Select * from newsagent.Customer");
			assertTrue(dao.retrieveAllCustomerAccounts() != null);
		} catch (SQLException e) {
			fail("Exception unexpected");

		}
	}

	// Test #: 13
	// Test Objective: To test the delete function of customer
	// Inputs: custId = "1"
	// Expected Output: "Customer Id deleted"
	public void testDeleteCustomerById001() {
		boolean delete = true;

		try {
			CustomerMySQLAccess dao = new CustomerMySQLAccess();
			dao.getConnectionStatement();
			int id = 1;
			assertEquals(delete, dao.deleteCustomerById(id));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}

	// Test #: 14
	// Test Objective: To test the delete function of customer
	// Inputs: custId = "-99"
	// Expected Output: "Customer Table emptied"
	public void testDeleteCustomerById002() {
		boolean delete = true;

		try {
			CustomerMySQLAccess dao = new CustomerMySQLAccess();
			dao.getConnectionStatement();
			int id = -99;
			assertEquals(delete, dao.deleteCustomerById(id));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}

	// Test #: 15
	// Test Objective: To test the update function of customer
	// Inputs: custId = "1" updateChoice = "Address " adjustion = "Athlone"
	// Expected Output: Exception Message: "Customer Area NOT specified"
	public void testUpdateCustomerById001() {
		boolean update = true;

		try {
			CustomerMySQLAccess dao = new CustomerMySQLAccess();
			dao.getConnectionStatement();
			int id = 2;
			String updateChoice = "Address";
			String adjustion = "Address";

			assertEquals(update, dao.updateCustomerById(id, updateChoice, adjustion));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}

	// Test #: 16
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = "10"
	// Expected Output: Exception Message: "Customer Name Contains Numeric"
	public void testValidateName004() {

		try {

			// Call method under test
			Customer.validateName("10");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer Name Contains Numeric", e.getMessage());
		}
	}

	// Test #: 17
	// Test Objective: To catch an invalid customer phone number
	// Inputs: custName = "abcdefghif"
	// Expected Output: Exception Message: "Customer PhoneNumber Contains Alphabet"
	public void testValidatePhone004() {

		try {
			// Call method under test
			Customer.validatePhoneNumber("abcdefghif");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer PhoneNumber Contains Alphabet", e.getMessage());
		}
	}

	// Test #: 18
	// Test Objective: To catch an invalid customer phone number
	// Inputs: custName = "12345678a"
	// Expected Output: Exception Message: "Customer PhoneNumber Contains Alphabet"
	public void testValidatePhone005() {

		try {
			// Call method under test
			Customer.validatePhoneNumber("12345678a");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer PhoneNumber Contains Alphabet", e.getMessage());
		}
	}

	// Test #: 19
	// Test Objective: To test the update function of customer
	// Inputs: custId = "1" updateChoice = "Address " adjustion = "Athlone"
	// Expected Output: Exception Message: "Customer Area NOT specified"
	public void testUpdateCustomerById002() {
		boolean update = true;

		try {
			CustomerMySQLAccess dao = new CustomerMySQLAccess();
			dao.getConnectionStatement();
			int id = 2;
			String updateName = "Testing";
			String updateAddress = "Testing";
			String updatePhone = "Testing";
			Double updatePayment = 50.5;
			String updateArea = "Testing";
			assertEquals(update,
					dao.updateCustomerById(id, updateName, updateAddress, updatePhone, updatePayment, updateArea));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected");
		}

	}
	// Test #: 20
	// Test Objective: To catch an invalid customer address
	// Inputs: custName = "This sentence has to be more than 30 words in order to
	// test JUnit"
	// Expected Output: Exception Message: "Customer Address does not exceeds
	// maximum length requirements"

	public void testValidateAddress003() {

		try {
			// Call method under test
			Customer.validateAddress("This sentence has to be more than 30 words in order to test JUnit");
			fail("Exception expected");
		} catch (CustomerExceptionHandler e) {
			assertEquals("Customer Address does not exceeds maximum length requirements", e.getMessage());
		}
	}
}
