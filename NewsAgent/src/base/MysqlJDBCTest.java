package base;
import java.sql.Connection;
import da.AgentMySQLAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.exceptions.SQLError;

import base.MysqlJDBC;
import junit.framework.TestCase;

public class MysqlJDBCTest extends TestCase {
	Connection connect = null;
	Connection connect2 = null;
	
				public void testGetConnection001() {
					try {
						connect= MysqlJDBC.getConnection();
					}
					catch(Exception e){
						fail("Exception unexpected");
					}
				}
				public void testGetConnection002() {
					try {
						connect= MysqlJDBC.getConnection();
						connect.close();
						AgentMySQLAccess aa= new AgentMySQLAccess();
						aa.retrieveAllDAAccounts();
						fail("Exception expected");
					} catch (Exception e) {
						System.out.println("Error");
						
					}
				}
				public void testCloseConnection001() {
					try {
						connect= MysqlJDBC.getConnection();
						connect.close();
					}
					catch(Exception e){
						fail("Exception unexpected");
					}
				}
				public void testCloseConnection002() {
					try {
						Connection connect = null;
						connect.close();
						fail("Exception unexpected");
					}
					catch(Exception e){
						System.out.println("Error");
					}
				}

}

		

