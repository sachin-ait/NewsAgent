package Customer;
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
			Customer.validatePhoneNumber("1234567891234");
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
}
