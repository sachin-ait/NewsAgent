package biling;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillTest extends TestCase {


    public void testBill001() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date billDate = dateFormat.parse("01-13-2021");
            String outputDate = dateFormat.format(billDate);
            System.out.println();


            Bill bill = new Bill("Belle Brown", "1 Farburn Terrace London", 100, billDate);
            // Use getters to check for object creation
            assertEquals(0, bill.getId());
            assertEquals("Belle Brown", bill.getCustomerName());
            assertEquals("1 Farburn Terrace London", bill.getCustomerAddress());
            assertEquals(100.0, bill.getFee());
            assertEquals("01-13-2021", dateFormat.format(bill.getBillDate()));

        } catch (BillExceptionHandler | ParseException billExceptionHandler) {
            fail("Exception not expected");
        }
    }

    //Test #: 2.1
    //Test Objective: To catch an incorrect lower boundary value Customer Name
    //Inputs: custName = ""
    //Expected Output: "Customer Name NOT specified"

    public void testValidateCustomerName001() {
        try {
            //Call method under test
            Bill.validateCustomerName("");
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Customer Name NOT specified", e.getMessage());
        }
    }

    //Test #: 2.4
    //Test Objective: To catch an incorrect lower boundary value Customer Name
    //Inputs: custName = ""
    //Expected Output: "Customer Name NOT specified"

    public void testValidateCustomerName004() {
        try {
            //Call method under test
            Bill.validateCustomerName(" ");
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Customer Name NOT specified", e.getMessage());
        }
    }

    //Test #: 2.2
    //Test Objective: To catch an incorrect lower boundary value Customer Name
    //Inputs: custName = "Aj"
    //Expected Output: "Agent Name does not meet minimum length requirements"
    public void testValidateCustomerName002() {
        try {
            //Call method under test
            Bill.validateCustomerName("AJ");
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Customer Name does not meet minimum length requirements", e.getMessage());
        }
    }

    //Test #: 2.3
    //Test Objective: To catch an incorrect lower boundary value Customer Name
    //Inputs: custName = "AJdsfkjwfjlfqhjfkqhfjkqfhjkqfhleqlqghjdjlahjdlhfqitg jjjdlahflrqlqhdslhbafha;fhghhligihui"
    //Expected Output: "Customer Name exceeds maximum length requirements"
    public void testValidateCustomerName003() {
        try {
            //Call method under test
            Bill.validateCustomerName("AJdsfkjwfjlfqhjfkqhfjkqfhjkqfhleqlqghjdjlahjdlhfqitg jjjdlahflrqlqhdslhbafha;fhghhligihui");
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Customer Name exceeds maximum length requirements", e.getMessage());
        }
    }


    //Test #: 3.1
    //Test Objective: To catch an incorrect lower boundary value Customer Address
    //Inputs: customerAddress = ""
    //Expected Output: "Customer Address NOT specified"
    public void testValidateCustomerAddress001() {
        try {
            //Call method under test
            Bill.validateCustomerAddress("");
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Customer Address NOT specified", e.getMessage());
        }
    }

    //Test #: 3.2
    //Test Objective: To catch an incorrect lower boundary value Customer Address
    //Inputs: customerAddress = "Athlone"
    //Expected Output: "Customer Address does not meet minimum length requirements"
    public void testValidateCustomerAddress002() {
        try {
            //Call method under test
            Bill.validateCustomerAddress("Athlone");
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Customer Address does not meet minimum length requirements", e.getMessage());
        }
    }

    //Test #: 3.3
    //Test Objective: To catch an incorrect higher boundary value Customer Address
    //Inputs: customerAddress = "AthloneAtAthloneAthloneAthlhloneAthloneAthloneAthloneAthlonehloneAthloneAtAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthlonehloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthlone"
    //Expected Output: "Customer Address does not meet minimum length requirements"
    public void testValidateCustomerAddress003() {
        try {
            //Call method under test
            Bill.validateCustomerAddress("AthloneAtAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthlone" +
                    "AthloneAthloneAthloneAthloneAthloneAthloneAAthloneAtAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthlone" +
                    "AthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthlonehloneAthloneAthloneAthloneAthloneAthloneAtAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthlonehloneAthloneAthloneAthloneAthloneAthloneAtAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthloneAthl" +
                    "oneAthloneAthloneAthloneAthloneAthloneAthloneAthlonehloneAthloneAthloneAthloneAthlonethlonehloneAthloneAthloneAthloneAthlone");
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Customer Address exceeds maximum length requirements", e.getMessage());
        }
    }


    //Test #: 4.1
    //Test Objective: To catch an incorrect lower boundary value fee
    //Inputs: fee = -1
    //Expected Output: "Fee does not meet minimum length requirements"
    public void testValidateFee001() {
        try {
            //Call method under test
            Bill.validateFee(-1);
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Fee does not meet minimum length requirements", e.getMessage());
        }
    }

    //Test #: 4.2
    //Test Objective: To catch an incorrect higher boundary value fee
    //Inputs: fee = 1000001
    //Expected Output: "Fee does not meet minimum length requirements"
    public void testValidateFee002() {
        try {
            //Call method under test
            Bill.validateFee(1000001);
            fail("Exception expected");
        } catch (BillExceptionHandler e) {
            assertEquals("Fee does not meet maximum length requirements", e.getMessage());
        }
    }
}

