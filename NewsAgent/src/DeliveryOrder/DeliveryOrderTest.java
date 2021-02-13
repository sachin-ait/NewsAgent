package DeliveryOrder;

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
			DeliveryOrder deliverObj = new DeliveryOrder("Jack", "StarryNight", "13/2/2021");

			// Use getters to check for object creation
			assertEquals("Jack", deliverObj.getName());
			assertEquals("StarryNight", deliverObj.getPublication());
			assertEquals("13/2/2021", deliverObj.getDate());
		} catch (DeliveryOrderExceptionHandler e) {
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
			DeliveryOrder.validateName("J");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Customer Name does not meet minimum length requirements", e.getMessage());
		}
	}

	// Test #: 3
	// Test Objective: To catch an invalid customer name
	// Inputs: custName = ""
	// Expected Output: Exception Message: "Customer Name NOT specified"

	public void testValidateName002() {

		try {

			// Call method under test
			DeliveryOrder.validateName("");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Customer Name NOT specified", e.getMessage());
		}
	}
	// Test #: 10
		// Test Objective: To catch an invalid customer name
		// Inputs: custName = "This sentence has to be more than 30 words in order to test JUnit"
		// Expected Output: Exception Message: "Customer Name does not exceeds maximum length requirements"

		public void testValidateName003() {

			try {

				// Call method under test
				DeliveryOrder.validateName("This sentence has to be more than 30 words in order to test JUnit");
				fail("Exception expected");
			} catch (DeliveryOrderExceptionHandler e) {
				assertEquals("Customer Name does not exceeds maximum length requirements", e.getMessage());
			}
		}
	// Test #: 4
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
	// Test #: 5
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
	// Test #: 9
	// Test Objective: To catch an invalid publication name
	// Inputs: publicationName = "This sentence has to be more than 30 words in
	// order to test JUnit"
	// Expected Output: Exception Message: "Publication Name does not exceeds maximum length requirements"

	public void testValidatePublicationName003() {

		try {

			// Call method under test
			DeliveryOrder.validatePublication("This sentence has to be more than 30 words in order to test JUnit");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Publication Name does not exceeds maximum length requirements", e.getMessage());
		}

	}
	// Test #: 6
	// Test Objective: To catch an invalid deliver date
	// Inputs: publicationName = ""
	// Expected Output: Exception Message: "Deliver Date NOT specified"

	public void testValidateDeliverDate001() {

		try {

			// Call method under test
			DeliveryOrder.validateDate("");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Deliver Date NOT specified", e.getMessage());
		}

	}
	// Test #: 7
	// Test Objective: To catch an invalid deliver date
	// Inputs: publicationName = "J"
	// Expected Output: Exception Message: "Deliver Date does not meet minimum
	// length requirements"

	public void testValidateDeliverDate002() {

		try {

			// Call method under test
			DeliveryOrder.validateDate("J");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Deliver Date does not meet minimum length requirements", e.getMessage());
		}

	}
	// Test #: 8
	// Test Objective: To catch an invalid deliver date
	// Inputs: publicationName = "Jjjjjjjjjjjjjjjjj"
	// Expected Output: Exception Message: "Deliver Date does not exceeds maximum
	// length requirements"

	public void testValidateDeliverDate003() {

		try {

			// Call method under test
			DeliveryOrder.validateDate("Jjjjjjjjjjjjjjjjj");
			fail("Exception expected");
		} catch (DeliveryOrderExceptionHandler e) {
			assertEquals("Deliver Date does not exceeds maximum length requirements", e.getMessage());
		}

	}


}
