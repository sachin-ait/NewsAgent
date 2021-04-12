package publications;

import docket.DocketExceptionHandler;
import junit.framework.TestCase;

public class PublicationTest extends TestCase {

    //Test #: 1
    public void testPublication001() throws DocketExceptionHandler {
        try {
            Publication publication = new Publication("Athlone BBC", 10, 20.5, "Weekly");
            // Use getters to check for object creation
            assertEquals(10, publication.getAmount());
            assertEquals("Athlone BBC", publication.getName());
            assertEquals("Weekly", publication.getFreq());
        } catch (PubExceptionHandler docketExceptionHandler) {
            fail("Exception not expected");
        }
    }

    //Test #: 2.1
    //Test Objective: To catch an incorrect lower boundary value Publication Name
    //Inputs: PublicationName = ""
    //Expected Output: "Publication Name NOT specified"

    public void testValidatePublicationName001() {
        try {
            //Call method under test
            Publication.validateName("");
            fail("Exception expected");
        } catch (PubExceptionHandler e) {
            assertEquals("Publication Name NOT specified", e.getMessage());
        }
    }

    //Test #: 2.3
    public void testValidatePublicationName002() {
        try {
            //Call method under test
            Publication.validateName("a");
            fail("Exception expected");
        } catch (PubExceptionHandler e) {
            assertEquals("Publication Name does not meet minimum length requirements", e.getMessage());
        }
    }

    //Test #: 2.4
    public void testValidatePublicationName003() {
        try {
            //Call method under test
            Publication.validateName("Publication maximum length requirements");
            fail("Exception expected");
        } catch (PubExceptionHandler e) {
            assertEquals("Publication Name does not exceeds maximum length requirements", e.getMessage());
        }
    }

    //Test #: 3.1
    public void testValidatePublicationAmount001() {
        try {
            //Call method under test
            Publication.validateAmount(-1);
            fail("Exception expected");
        } catch (PubExceptionHandler e) {
            assertEquals("Publication amount does not meet minimum length requirements", e.getMessage());
        }
    }

    //Test #: 3.2
    public void testValidatePublicationAmount002() {
        try {
            //Call method under test
            Publication.validateAmount(20001);
            fail("Exception expected");
        } catch (PubExceptionHandler e) {
            assertEquals("Publication amount does not exceeds maximum length requirements", e.getMessage());
        }
    }


    //Test #: 4
    public void testValidatePublicationFrequency001() {
        try {
            //Call method under test
            Publication.validateFreq("Yearly");
            fail("Exception expected");
        } catch (PubExceptionHandler e) {
            assertEquals("Publication frequency should be Daily,Weekly or Monthly", e.getMessage());
        }
    }

    //Test #: 5.1
    public void testValidatePublicationPrice001() {
        try {
            //Call method under test
            Publication.validatePrice(-10);
            fail("Exception expected");
        } catch (PubExceptionHandler e) {
            assertEquals("Publication price does not meet minimum length requirements", e.getMessage());
        }
    }

    public void testValidatePublicationPrice002() {
        try {
            //Call method under test
            Publication.validatePrice(1001);
            fail("Exception expected");
        } catch (PubExceptionHandler e) {
            assertEquals("Publication price does not exceeds maximum length requirements", e.getMessage());
        }
    }
}



