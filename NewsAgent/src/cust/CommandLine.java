package cust;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class CommandLine {
	
	private static void listCustomerFuctionalityAvailable() {
		
		//Present Customer with Functionality Options
		
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Customer Account");
		System.out.println("2. View ALL Customer Records");
		System.out.println("3. Delete Customer Record by ID");
		System.out.println("4. Update Customer Record by ID");
	//	System.out.println("99. Close the NewsAgent Application");
        System.out.println("99. Go Back to Main Menu");
		System.out.println("=============================================");
		System.out.println(" ");
		
	}

	
	private static boolean printCustomerTable(ResultSet rs) throws Exception {
		
		//Print The Contents of the Full Customer Table
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s",rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			int id = rs.getInt("CustID");
			String name = rs.getString("Name");
			String addr = rs.getString("Address");
			String phone = rs.getString("PhoneNumber");
			double payment = rs.getDouble("Payment");
			String area = rs.getString("Area");
			System.out.printf("%30s", id);
			System.out.printf("%30s", name);
			System.out.printf("%30s", addr);
			System.out.printf("%30s", phone);
			System.out.printf("%30s", payment);
			System.out.printf("%30s", area);
			System.out.println();
		}// end while
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		
		return true;
		
	}

	public static void main(String[] args) {
		
		//Create the Database Object
		
		try {
			
			JDBC dao = new JDBC();
			dao.getConnectionStatement();
			
		
			// Configure System for Running
			Scanner keyboard = new Scanner(System.in); 
			String functionNumber = "-99";
			boolean keepAppOpen = true;
		
			while (keepAppOpen == true) {
			
				//Present list of functionality and get selection
				listCustomerFuctionalityAvailable();
				functionNumber = keyboard.next();
		
				switch (functionNumber) {
		
				case "1":
					//Get Customer Details from the User
					System.out.printf("Enter Customer Name: \n");
					String custName = keyboard.next();
					System.out.printf("Enter Customer Address: \n");
					String custAddr = keyboard.next();
					System.out.printf("Enter Customer PhoneNumber: \n");
					String custphoneNumber = keyboard.next();
					System.out.printf("Enter Customer Payment: \n");
					Double custPayment = Double.parseDouble(keyboard.next());
					System.out.printf("Enter Customer Area: \n");
					String custArea = keyboard.next();
				
					Customer custObj = new Customer(custName,custAddr,custphoneNumber,custPayment,custArea);
				
					//Insert Customer Details into the database
					boolean insertResult = dao.insertCustomerDetailsAccount(custObj);
					if (insertResult == true)
						System.out.println("Customer Details Saved");
					else 
						System.out.println("ERROR: Customer Details NOT Saved");
					break;
					
				case "2": 
					//Retrieve ALL Customer Records
					ResultSet rSet = dao.retrieveAllCustomerAccounts();
					if (rSet == null) {
						System.out.println("No Records Found");
						break;
					}
					else {
						boolean tablePrinted = printCustomerTable(rSet);
						if (tablePrinted == true)
							rSet.close();
					}
					break;
					
				case "3":
					//Delete Customer Record by ID
					System.out.println("Enter Customer Id to be deleted or -99 to Clear all Rows");
					String deleteCustId = keyboard.next();
					boolean deleteResult = dao.deleteCustomerById(Integer.parseInt(deleteCustId));
					if ((deleteResult == true) && (deleteCustId.equals("-99")))
						System.out.println("Customer Table Emptied");
					else if (deleteResult == true)
						System.out.println("Customer Deleted");
					else 
						System.out.println("ERROR: Customer Details NOT Deleted or Do Not Exist");
					break;
			
				case "4":
					//Update Customer Record by ID
					System.out.println("Enter Customer Id to be updated");
					String updateCustId = keyboard.next();
					updateDetail();
					
					String updateChoice=""	;			
					String adjustion="";					
					String caseNumber = "-99";
					
					caseNumber = keyboard.next();
					switch(caseNumber) {
					
					case "1" :
						System.out.println("Please enter the new Name:");
						adjustion = keyboard.next();
						updateChoice="Name";
						break;
					
					case "2" :
						System.out.println("Please enter the new Address:");
						adjustion = keyboard.next();
						updateChoice="Address";
						break;
					
					case "3" :
						System.out.println("Please enter the new Phone Number:");
						adjustion = keyboard.next();
						updateChoice="PhoneNumber";
						break;					
					
					case "4" :
						System.out.println("Please enter the new Payment:");
						adjustion = keyboard.next();
						updateChoice="Payment";
						break;
					
					case "5" :
						System.out.println("Please enter the new Area:");
						adjustion = keyboard.next();
						updateChoice="Area";
						break;
						
					case "99" :
						break;
					}
					boolean updateResult = dao.updateCustomerById(Integer.parseInt(updateCustId),updateChoice,adjustion);
					if (updateResult == true)
						System.out.println("Customer Updated");
					else 
						System.out.println("ERROR: Customer Details NOT Updated or Do Not Exist");
					break;
					
				case "99":
					keepAppOpen = false;
					System.out.println("Closing the Application");
					break;
			
				default:
					System.out.println("No Valid Function Selected");
					break;
				} // end switch
		
			}// end while
		
			//Tidy up Resources
		//	keyboard.close();
		
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
			
		}// end try-catch
		
	

	} // end main
	
	private static void updateDetail() {
		
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Edit Name");
		System.out.println("2. Edit Address");
		System.out.println("3. Edit Phone Number");
		System.out.println("4. Edit Payment");
		System.out.println("5. Edit Area");		
		System.out.println("99. Back");
		
		
	}
	
}