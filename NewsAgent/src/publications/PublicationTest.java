package publications;

import docket.Docket;
import docket.DocketExceptionHandler;
import junit.framework.TestCase;

public class PublicationTest extends TestCase {

    public void testBill001() throws DocketExceptionHandler {
        try {
            Publication docket = new Publication("Brown", 10);
            // Use getters to check for object creation
            assertEquals(10, docket.getAmount());
            assertEquals("Brown", docket.getName());
        } catch (DocketExceptionHandler docketExceptionHandler) {
            fail("Exception not expected");
        }
    }


    public void testValidateDocketName001() {
        try {
            //Call method under test
            Docket.validateDocketName("");
            fail("Exception expected");
        } catch (DocketExceptionHandler e) {
            assertEquals("Docket Name NOT specified", e.getMessage());
        }
    }


    public void testValidateDocketName002() {
        try {
            //Call method under test
            Docket.validateDocketName("DD");
            fail("Exception expected");
        } catch (DocketExceptionHandler e) {
            assertEquals("Docket Name does not meet minimum length requirements", e.getMessage());
        }
    }


    public void testValidateDocketName003() {
        try {
            //Call method under test
            Docket.validateDocketName("jsdkljlwjr;oriqkldajjl;k;keworkopwkoprkwmlk;lmdlmfk;l;klskf;kwkoopwkopjtklksdklfkljsjkljklsjlkwejirjptklklsdjflkksd");
            fail("Exception expected");
        } catch (DocketExceptionHandler e) {
            assertEquals("Docket Name exceeds maximum length requirements", e.getMessage());
        }
    }
}



