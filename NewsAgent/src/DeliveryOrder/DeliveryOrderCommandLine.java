package DeliveryOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;
import OrderReport.OrderReport;
public class DeliveryOrderCommandLine {

	private static void listCustomerFuctionalityAvailable() {

		// Present Customer with Functionality Options

		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Delivery Order");
		System.out.println("2. View ALL Delivery Order Records");
		System.out.println("3. Delete Delivery Order Record by ID");
		System.out.println("4. Update Delivery Order Record by ID");
		System.out.println("5. Deleted Delivery Order Report");
		System.out.println("6. Updated Delivery Order Report");
		System.out.println("99. Close the NewsAgent Application");
		System.out.println("=============================================");
		System.out.println(" ");

	}

	private static boolean printDeliveryOrder(ResultSet rs) throws Exception {

		// Print The Contents of the Full Delivery order Table

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s", rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			int id = rs.getInt("DeliveryOrderId");
			String custName = rs.getString("CustName");
			String publicationName = rs.getString("PublicationName");
			String deliveryDate = rs.getString("DeliveryDate");
			System.out.printf("%30s", id);
			System.out.printf("%30s", custName);
			System.out.printf("%30s", publicationName);
			System.out.printf("%30s", deliveryDate);
			System.out.println();
		} // end while
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	public static void main(String[] args) {

		// Create the Database Object

		try {

			DeliveryOrderMySQLAccess dao = new DeliveryOrderMySQLAccess();
			dao.getConnectionStatement();

			// Configure System for Running
			Scanner keyboard = new Scanner(System.in);
			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen == true) {

				// Present list of functionality and get selection
				listCustomerFuctionalityAvailable();
				functionNumber = keyboard.next();

				switch (functionNumber) {

				case "1":
					// Get Customer Details from the User
					System.out.printf("Enter Customer Name: \n");
					String custName = keyboard.next();
					System.out.printf("Enter Publication Name: \n");
					String publicationName = keyboard.next();
					System.out.printf("Enter Delivery Date : \n");
					String deliveryDate = keyboard.next();

					DeliveryOrder deliveryObj = new DeliveryOrder(custName, publicationName, deliveryDate);

					// Insert Customer Details into the database
					boolean insertResult = dao.insertDeliveryOrderDetailsAccount(deliveryObj);
					if (insertResult == true)
						System.out.println("Delivery Order Details Saved");
					else
						System.out.println("ERROR: Delivery Order Details NOT Saved");
					break;

				case "2":
					// Retrieve ALL Customer Records
					ResultSet rSet = dao.retrieveAllDeliveryOrderAccounts();
					if (rSet == null) {
						System.out.println("No Records Found");
						break;
					} else {
						boolean tablePrinted = printDeliveryOrder(rSet);
						if (tablePrinted == true)
							rSet.close();
					}
					break;

				case "3":
					// Delete Customer Record by ID
					System.out.println("Enter Delivery Order Id to be deleted or -99 to Clear all Rows");
					String deleteDeliveryOrderId = keyboard.next();
					boolean deleteResult = dao.deleteDeliveryOrderById(Integer.parseInt(deleteDeliveryOrderId));
					if ((deleteResult == true) && (deleteDeliveryOrderId.equals("-99")))
						System.out.println("Delivery Order Table Emptied");
					else if (deleteResult == true)
						System.out.println("Delivery Order Deleted");
					else
						System.out.println("ERROR: Delivery Order Details NOT Deleted or Do Not Exist");
					break;
				case "4":
					// Update Customer Record by ID
					System.out.println("Enter Delivery Order Id to be updated");
					String updateDeliveryOrderId = keyboard.next();
					updateDetail();

					String updateChoice = "";
					String adjustion = "";
					String caseNumber = "-99";

					caseNumber = keyboard.next();
					switch (caseNumber) {

					case "1":
						System.out.println("Please enter the new Name:");
						adjustion = keyboard.next();
						updateChoice = "CustName";
						break;

					case "2":
						System.out.println("Please enter the new Publication Name:");
						adjustion = keyboard.next();
						updateChoice = "PublicationName";
						break;

					case "3":
						System.out.println("Please enter the new Delivery Date:");
						adjustion = keyboard.next();
						updateChoice = "DeliveryDate";
						break;

					case "99":
						break;
					}
					boolean updateResult = dao.updateDeliveryOrderById(Integer.parseInt(updateDeliveryOrderId), updateChoice,
							adjustion);
					if (updateResult == true)
						System.out.println("Delivery Order Updated");
					else
						System.out.println("ERROR: Delivery Order Details NOT Updated or Do Not Exist");
					break;
					
				case "5":
					OrderReport.readDeletedReport();
					break;
				case "6":
					OrderReport.readUpdatedReport();
					break;
				case "99":
					keepAppOpen = false;
					System.out.println("Closing the Application");
					break;

				default:
					System.out.println("No Valid Function Selected");
					break;
				} // end switch

			} // end while

			// Tidy up Resources
			keyboard.close();

		}

		catch (Exception e) {
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
		} // end try-catch

	} // end main

	private static void updateDetail() {

		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Edit Name");
		System.out.println("2. Edit Publication Number");
		System.out.println("3. Edit Date");
		System.out.println("99. Back");

	}
}