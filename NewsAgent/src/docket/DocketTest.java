package docket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import base.MysqlJDBC;
import da.AgentMySQLAccess;
import da.DAExceptionHandler;
import di.DI;
import di.DIExceptionHandler;
import di.InvoiceMySQLAccess;
import junit.framework.TestCase;

public class DocketTest extends TestCase {

    public void testBill001() throws DocketExceptionHandler {
        try {
            Docket docket = new Docket("Brown", 10);
            // Use getters to check for object creation
            assertEquals(10, docket.getNum());
            assertEquals("Brown", docket.getDocketName());
        } catch (DocketExceptionHandler docketExceptionHandler) {
            fail("Exception not expected");
        }
    }

    //Test #: 2.1
    //Test Objective: To catch an incorrect lower boundary value Customer Name
    //Inputs: docketName = ""
    //Expected Output: "Docket Name NOT specified"

    public void testValidateDocketName001() {
        try {
            //Call method under test
            Docket.validateDocketName("");
            fail("Exception expected");
        } catch (DocketExceptionHandler e) {
            assertEquals("Docket Name NOT specified", e.getMessage());
        }
    }

    //Test #: 2.2
    //Test Objective: To catch an incorrect lower boundary value Docket Name
    //Inputs: docketName = "DD"
    //Expected Output: "Docket Name does not meet minimum length requirements"
    public void testValidateDocketName002() {
        try {
            //Call method under test
            Docket.validateDocketName("DD");
            fail("Exception expected");
        } catch (DocketExceptionHandler e) {
            assertEquals("Docket Name does not meet minimum length requirements", e.getMessage());
        }
    }

    //Test #: 2.3
    //Test Objective: To catch an incorrect lower boundary value Docket Name
    //Inputs: docketName = "AJdsfkjwfjlfqhjfkqhfjkqfhjkqfhleqlqghjdjlahjdlhfqitg jjjdlahflrqlqhdslhbafha;fhghhligihui"
    //Expected Output: "Docket Name exceeds maximum length requirements"
    public void testValidateDocketName003() {
        try {
            //Call method under test
            Docket.validateDocketName("jsdkljlwjr;oriqkldajjl;k;keworkopwkoprkwmlk;lmdlmfk;l;klskf;kwkoopwkopjtklksdklfkljsjkljklsjlkwejirjptklklsdjflkksd");
            fail("Exception expected");
        } catch (DocketExceptionHandler e) {
            assertEquals("Docket Name exceeds maximum length requirements", e.getMessage());
        }
    }
    public void testinsertData001() {
    	try {
    		Connection connect = null;
    		connect= MysqlJDBC.getConnection();
    		DocketMySQLAccess dio = new DocketMySQLAccess();
    		Docket docket = new Docket("xtzzzzzzzz", 2);
    		assertEquals(true, dio.insertDocket(docket));
    	}
    	catch(Exception e){
    		fail("Exception unexpected");
    	}
    }

    public void testdeleteDIById001() {
    	try {
    		Connection connect = null;
    		connect= MysqlJDBC.getConnection();
    		DocketMySQLAccess dio = new DocketMySQLAccess();
    		Docket docket = new Docket("xtzzzzzzzz", 2);
    		dio.insertDocket(docket);
    		assertEquals(true, dio.deleteDocketById(1));
    	}
    	catch(Exception e){
    		fail("Exception unexpected");
    	}
    }

   
}