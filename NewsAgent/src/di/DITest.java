package di;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import base.MysqlJDBC;
import da.AgentMySQLAccess;
import da.DA;
import da.DAExceptionHandler;
import junit.framework.TestCase;
public class DITest extends TestCase{
	
public void testDI001() {
		
		//Create the Invoice Object
		
		
		try {
			
			//Call method under test
			DI DIObj = new DI(28,"February",2021, 12, 10, 40.40);
			
			// Use getters to check for object creation
			assertEquals(0, DIObj.getId());
			assertEquals("28/February/2021", DIObj.getDate());
			assertEquals(12, DIObj.getSuccess());
			assertEquals(10, DIObj.getFailed());
			assertEquals(40.40, DIObj.getPay());
		}
		catch (DIExceptionHandler e) {
			System.out.println(e.getMessage());
			fail("Exception not expected");
		}
		
	}

public void testValidateDateNULL001() {
	
	try {
			
		//Call method under test
		DI.validateDateNULL(0,"February",2021);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Invalid amount of days", e.getMessage());	
	}
}

public void testValidateDateNULL011() {
	
	try {
			
		//Call method under test
		DI.validateDateNULL(-1,"February",2021);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Invalid amount of days", e.getMessage());	
	}
}

public void testValidateDateNULL012() {
	
	try {
			
		//Call method under test
		DI.validateDateNULL(32,"February",2021);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Invalid amount of days", e.getMessage());	
	}
}

public void testValidateDateNULL123() {
	
	try {
			
		//Call method under test
		DI.validateDateNULL(33,"February",2021);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Invalid amount of days", e.getMessage());	
	}
}

public void testValidateDateNULL021() {
	
	try {
			
		//Call method under test
		DI.validateDateNULL(15,"",2021);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Month is not specified", e.getMessage());	
	}
}
public void testValidateDateMONTH012() {
	
	try {
			
		//Call method under test
		DI.validateDateMONTH("  ");
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Month NOT valid", e.getMessage());	
	}
}

public void testValidateDateNULL003() {
	
	try {
			
		//Call method under test
		DI.validateDateNULL(15,"February",0);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Year NOT specified", e.getMessage());	
	}
}
public void testValidateDateNULL031() {
	
	try {
			
		//Call method under test
		DI.validateDateNULL(15,"February",-1);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Year NOT specified", e.getMessage());	
	}
}

public void testValidateDateSPECIAL001() {
	
	try {
			
		//Call method under test
		DI.validateDateSPECIAL(30,"February",2004);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("February cant have more than 29 days", e.getMessage());	
	}
}

public void testValidateDateSPECIAL002() {
	
	try {
			
		//Call method under test
		DI.validateDateSPECIAL(29,"February",2005);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("February only has 28 days", e.getMessage());	
	}
}

public void testValidateDateSPECIAL003() {
	
	try {
			
		//Call method under test
		DI.validateDateSPECIAL(31,"April",2005);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("April only has 30 days", e.getMessage());	
	}
}

public void testValidateDateSPECIAL004() {
	
	try {
			
		//Call method under test
		DI.validateDateSPECIAL(31,"June",2005);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("June only has 30 days", e.getMessage());	
	}
}

public void testValidateDateSPECIAL005() {
	
	try {
			
		//Call method under test
		DI.validateDateSPECIAL(31,"September",2005);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("September only has 30 days", e.getMessage());	
	}
}

public void testValidateDateSPECIAL006() {
	
	try {
			
		//Call method under test
		DI.validateDateSPECIAL(31,"November",2005);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("November only has 30 days", e.getMessage());	
	}
}

public void testValidateSUCCESS001() {
	
	try {
			
		//Call method under test
		DI.validateSuccess(-1);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Successful deliveries cannot be negative", e.getMessage());	
	}
}

public void testValidateFailed001() {
	
	try {
			
		//Call method under test
		DI.validateFailed(-1);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Failed deliveries cannot be negative", e.getMessage());	
	}
}

public void testValidatePayment001() {
	
	try {
			
		//Call method under test
		DI.validatePay(-0.1);
		fail("Exception expected");
	}
	catch (DIExceptionHandler e) {
		assertEquals("Payment cannot be negative", e.getMessage());	
	}
}

public void testinsertDInvoiceDetails001() {
	try {
		boolean insert = true;
		Connection connect = null;
		connect= MysqlJDBC.getConnection();
		InvoiceMySQLAccess dio = new InvoiceMySQLAccess();
		DI DIObj = new DI(28,"February",2021, 12, 10, 40.40);
		assertEquals(insert,InvoiceMySQLAccess.insertDInvoiceDetails(DIObj));
	}
	catch(DIExceptionHandler e){
		fail("Exception unexpected");
	}
}

public void testretrieveAllDInvoices001() {
	try {
		boolean insert = true;
		Connection connect = null;
		connect= MysqlJDBC.getConnection();
		AgentMySQLAccess dao = new AgentMySQLAccess();
		Statement statement = connect.createStatement();
		ResultSet rs=statement.executeQuery("Select * from newsagent.DeliveryInvoices");
		 assertTrue(InvoiceMySQLAccess.retrieveAllDInvoices() != null);
	} catch (SQLException | DAExceptionHandler e) {
		fail("Exception unexpected");
		
	}
}

public void testdeleteDIById001() {
	try {
		boolean delete = true;
		Connection connect = null;
		connect= MysqlJDBC.getConnection();
		AgentMySQLAccess dao = new AgentMySQLAccess();
		int id = 1;
		assertEquals(delete,InvoiceMySQLAccess.deleteDIById(id));
	}
	catch(DAExceptionHandler e){
		fail("Exception unexpected");
	}
}

public void testdeleteDIById002() {
	try {
		boolean delete = true;
		Connection connect = null;
		connect= MysqlJDBC.getConnection();
		AgentMySQLAccess dao = new AgentMySQLAccess();
		int id =-99;
		assertEquals(delete,InvoiceMySQLAccess.deleteDIById(id));
	}
	catch(DAExceptionHandler e){
		fail("Exception unexpected");
	}
}

public void testupdateDIById001() {
	try {
		boolean update = true;
		Connection connect = null;
		connect= MysqlJDBC.getConnection();
		InvoiceMySQLAccess dao = new InvoiceMySQLAccess();
		int DIID =1;
		int DISuccess = 23;
		int DIFailure = 56;
		double DIPay = 10.00;
		assertEquals(update, InvoiceMySQLAccess.updateDIById(DIID,DISuccess,DIFailure,DIPay));
	}
	catch(DIExceptionHandler e){
		fail("Exception unexpected");
	}
}
}
