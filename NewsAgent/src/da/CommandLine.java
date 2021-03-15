package da;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class CommandLine {
	
	private static void listDAFuctionalityAvailable() {
		
		//Present Customer with Functionality Options
		
		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Agent Account");
		System.out.println("2. View ALL Agent Records");
		System.out.println("3. Delete Agent Record by ID");
		//System.out.println("99. Close the NewsAgent Application");
        System.out.println("99. Go Back to Main Menu");
		System.out.println("=============================================");
		System.out.println(" ");
		
	}

	
	private static boolean printDATable(ResultSet rs) throws Exception {
		
		//Print The Contents of the Full Customer Table
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s",rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			int Agent_ID = rs.getInt("Agent_ID");
			String Agent_Name = rs.getString("Agent_Name");
			String Agent_Area = rs.getString("Agent_Area");
			int Agent_Pay_Rate = rs.getInt("Agent_Pay_Rate");
			int Agent_Hours_Logged = rs.getInt("Agent_Hours_Logged");
			System.out.printf("%30s", Agent_ID);
			System.out.printf("%30s", Agent_Name);
			System.out.printf("%30s", Agent_Area);
			System.out.printf("%30s", Agent_Pay_Rate);
			System.out.printf("%30s", Agent_Hours_Logged);
			System.out.println();
		}// end while
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		
		return true;
		
	}

	public static void main(String[] args) {
		
		//Create the Database Object
		
		try {
			
			AgentMySQLAccess dao = new AgentMySQLAccess();
		
			// Configure System for Running
			Scanner keyboard = new Scanner(System.in); 
			String functionNumber = "-99";
			boolean keepAppOpen = true;
		
			while (keepAppOpen == true) {
			
				//Present list of functionality and get selection
				listDAFuctionalityAvailable();
				functionNumber = keyboard.next();
		
				switch (functionNumber) {
	
				case "1":
					//Get Customer Details from the User
					System.out.printf("Enter Agents Name: \n");
					String DAName = keyboard.next();
					System.out.printf("Enter Area to deliver to: \n");
					String deliveryArea = keyboard.next();
					System.out.printf("Enter Agents wage: \n");
					int DAPayRate = keyboard.nextInt();
					System.out.printf("Enter Agents amount of logged hours: \n");
					int DAHrsLogged = keyboard.nextInt();
					DA DAObj = new DA(DAName,deliveryArea,DAPayRate,DAHrsLogged);
					//Insert Customer Details into the database
					boolean insertResult = dao.insertDADetailsAccount(DAObj);
					if (insertResult == true)
						System.out.println("Agent Details Saved");
					else 
						System.out.println("ERROR: Agent Details NOT Saved");
					break;
					
				case "2": 
					//Retrieve ALL Customer Records
					ResultSet rSet = dao.retrieveAllDAAccounts();
					if (rSet == null) {
						System.out.println("No Records Found");
						break;
					}
					else {
						boolean tablePrinted = printDATable(rSet);
						if (tablePrinted == true)
							rSet.close();
					}
					break;
				
				case "3":
					//Delete Customer Record by ID
					System.out.println("Enter Delivery Agent Id to be deleted or -99 to Clear all Rows (Option -99 is not functional)");
					String deleteDAId = keyboard.next();
					boolean deleteResult = dao.deleteDAById(Integer.parseInt(deleteDAId));
					if ((deleteResult == true) && (deleteDAId.equals("-99")))
						System.out.println("Agent Table Emptied");
					else if (deleteResult == true)
						System.out.println("Agent Deleted");
					else 
						System.out.println("ERROR: Agent Details NOT Deleted or Do Not Exist");
					break;
					
				case "4":
					//Delete Customer Record by ID
					System.out.println("Enter Delivery Agent Id to be Updated");
					int updateDAId = keyboard.nextInt();
					System.out.printf("Enter Agents Name: \n");
					String DANameU = keyboard.next();
					System.out.printf("Enter Area to deliver to: \n");
					String deliveryAreaU = keyboard.next();
					System.out.printf("Enter Agents wage: \n");
					int DAPayRateU = keyboard.nextInt();
					System.out.printf("Enter Agents amount of logged hours: \n");
					int DAHrsLoggedU = keyboard.nextInt();
					boolean updateResult = dao.updateDAById(updateDAId,DANameU,deliveryAreaU,DAPayRateU,DAHrsLoggedU);
					if (updateResult == true)
						System.out.println("Agent Table Updated");
					else 
						System.out.println("ERROR: Agent Details NOT Updated or Do Not Exist");
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
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
		} // end try-catch
		

	} // end main
	
	
}
