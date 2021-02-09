package da;
import junit.framework.TestCase;

public class DATest extends TestCase {
	
	//Test #: 1
	//Test Objective: To create a Customer Account
	//Inputs: DAName = "John Doe", DAArea = "Willow Grove", DAPayRate = 12, DAHrsLogged = 50
	//Expected Output: DA Object created with id = 0, "John Doe", DAArea = "Willow Grove", DAPayRate = 12, DAHrsLogged = 50
	
	public void testDA001() {
		
		//Create the Customer Object
		
		
		try {
			
			//Call method under test
			DA DAObj = new DA("John Doe", "Willow Grove", 12, 50);
			
			// Use getters to check for object creation
			assertEquals(0, DAObj.getId());
			assertEquals("John Doe", DAObj.getName());
			assertEquals("Willow Grove", DAObj.getArea());
			assertEquals(12, DAObj.getPayRate());
			assertEquals(50, DAObj.getHrsLogged());
		}
		catch (DAExceptionHandler e) {
			fail("Exception not expected");
		}
		
	}
	
	//Test #: 2
	//Test Objective: To catch an incorrect lower boundary value Agent name
	//Inputs: custName = "JE"
	//Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"

	public void testValidateName001() {
			
		try {
				
			//Call method under test
			DA.validateName("JE");
			fail("Exception expected");
		}
		catch (DAExceptionHandler e) {
			assertEquals("Agent Name does not meet minimum length requirements", e.getMessage());	
		}
	}
	
	//Test #: 3
	//Test Objective: To catch an incorrect Upper boundary value Agent name
	//Inputs: DAName = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
	//Expected Output: Exception Message: "Customer Name does not meet maximum length requirements"
	public void testValidateName002() {
		
		try {
				
			//Call method under test
			DA.validateName("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
			fail("Exception expected");
		}
		catch (DAExceptionHandler e) {
			assertEquals("Agent Name exceeds maximum length requirements", e.getMessage());	
		}
}
	
	//Test #: 3
			//Test Objective: To catch an empty DA name
			//Inputs: DAName = ""
			//Expected Output: Exception Message: "Agent Name NOT specified"
		public void testValidateName003() {
			
			try {
					
				//Call method under test
				DA.validateName("");
				fail("Exception expected");
			}
			catch (DAExceptionHandler e) {
				assertEquals("Agent Name NOT specified", e.getMessage());	
			}
	}
		//Test #: 3.1
		//Test Objective: To test a correct lower boundary value customer name
		//Inputs: DAName = "Tom"
		//Expected Output: Exception Message: ""
		public void testValidateName004() {
			
			try {
					
				//Call method under test
				DA.validateName("Tom");
			}
			catch (DAExceptionHandler e) {
				fail("Exception unexpected");
			}
	}
		//Test #: 3.2
				//Test Objective: To test a correct upper boundary value customer name
				//Inputs: DAName = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				//Expected Output: Exception Message: ""
		public void testValidateName005() {
			
			try {
					
				//Call method under test
				DA.validateName("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
			}
			catch (DAExceptionHandler e) {
				fail("Exception unexpected");
			}
	}
		//Test #: 5
		//Test Objective: To catch an incorrect lower boundary value Area name
		//Inputs: areaName = "JEA"
		//Expected Output: Exception Message: "Delivery area does not meet minimum length requirements"

		public void testValidateArea001() {
				
			try {
					
				//Call method under test
				DA.validateArea("JEA");
				fail("Exception expected");
			}
			catch (DAExceptionHandler e) {
				assertEquals("Delivery area does not meet minimum length requirements", e.getMessage());	
			}
		}
		
		//Test #: 6
				//Test Objective: To catch an incorrect upper boundary value Area name
				//Inputs: areaName = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				//Expected Output: Exception Message: "Delivery area exceeds maximum length requirements"

				public void testValidateArea002() {
						
					try {
							
						//Call method under test
						DA.validateArea("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
						fail("Exception expected");
					}
					catch (DAExceptionHandler e) {
						assertEquals("Delivery area exceeds maximum length requirements", e.getMessage());	
					}
				}
				
				//Test #: 7
				//Test Objective: To catch an empty Area name
				//Inputs: areaName = ""
				//Expected Output: Exception Message: "Delivery area is NOT specified"

				public void testValidateArea003() {
						
					try {
							
						//Call method under test
						DA.validateArea("");
						fail("Exception expected");
					}
					catch (DAExceptionHandler e) {
						assertEquals("Delivery area is NOT specified", e.getMessage());	
					}
				}
				//Test #: 7.1
				//Test Objective: To catch a correct lower boundary Area name
				//Inputs: areaName = "Slane"
				//Expected Output: Exception Message: ""

				public void testValidateArea004() {
						
					try {
							
						//Call method under test
						DA.validateArea("Slane");
						
					}
					catch (DAExceptionHandler e) {
						fail("Exception unexpected");
					}
				}
				//Test #: 7.2
				//Test Objective: To catch a correct upper boundary Area name
				//Inputs: areaName = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				//Expected Output: Exception Message: ""

				public void testValidateArea005() {
						
					try {
							
						//Call method under test
						DA.validateArea("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
						
					}
					catch (DAExceptionHandler e) {
						fail("Exception unexpected");
					}
				}
				//Test #: 8
				//Test Objective: To catch an invalid Pay Rate
				//Inputs: payRate = -50
				//Expected Output: Exception Message: "Agents pay can not be negative"

				public void testValidatePayRate001() {
						
					try {
							
						//Call method under test
						DA.validatePayRate(-50);
						fail("Exception expected");
					}
					catch (DAExceptionHandler e) {
						assertEquals("Agents pay can not be negative", e.getMessage());	
					}
				}
				
				//Test #: 9
				//Test Objective: To catch an invalid Pay Rate
				//Inputs: payRate = 0
				//Expected Output: Exception Message: "Agents pay is NOT specified"

				public void testValidatePayRate002() {
						
					try {
							
						//Call method under test
						DA.validatePayRate(0);
						fail("Exception expected");
					}
					catch (DAExceptionHandler e) {
						assertEquals("Agents pay is NOT specified", e.getMessage());	
					}
				}
				//Test #: 9
				//Test Objective: To test a correct Pay Rate
				//Inputs: payRate = 50
				//Expected Output: Exception Message: ""

				public void testValidatePayRate003() {
						
					try {
							
						//Call method under test
						DA.validatePayRate(50);
					}
					catch (DAExceptionHandler e) {
						fail("Exception unexpected");
					}
				}
					
				//Test #: 10
				//Test Objective: To catch an invalid Logged Hours
				//Inputs: payRate = 0
				//Expected Output: Exception Message: "Agents hours can not be negative"

				public void testValidateLoggedHours001() {
						
					try {
							
						//Call method under test
						DA.validateLoggedHrs(-10);
						fail("Exception expected");
					}
					catch (DAExceptionHandler e) {
						assertEquals("Agents hours can not be negative", e.getMessage());	
					}
				}
				//Test #: 10
				//Test Objective: To test a correct amount of Logged Hours
				//Inputs: payRate = 50
				//Expected Output: Exception Message: ""

				public void testValidateLoggedHours002() {
						
					try {
							
						//Call method under test
						DA.validateLoggedHrs(10);
					}
					catch (DAExceptionHandler e) {
						fail("Exception unexpected");	
					}
				}
}

		

