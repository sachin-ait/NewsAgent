package di;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class CommandLine {

	private static void listDIFuctionalityAvailable() {

		// Present Customer with Functionality Options

		System.out.println(" ");
		System.out.println("=============================================");
		System.out.println("Please choose ONE of the following options:");
		System.out.println("1. Create Invoice");
		System.out.println("2. View ALL Invoices");
		System.out.println("3. Delete Invoice by Date");
		System.out.println("4. Update Invoice by Date");
		System.out.println("99. Go back to Main Menu");
		System.out.println("=============================================");
		System.out.println(" ");

	}
	
	private static boolean printDITable(ResultSet rs) throws Exception {

		// Print The Contents of the Full Customer Table

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Table: " + rs.getMetaData().getTableName(1));
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.printf("%30s", rs.getMetaData().getColumnName(i));
		}
		System.out.println();
		while (rs.next()) {
			int Invoice_ID = rs.getInt("Invoice_ID");
			String Invoice_Date = rs.getString("Invoice_Date");
			int Success_Delivery = rs.getInt("Success_Delivery");
			int Failed_Delivery = rs.getInt("Failed_Delivery");
			double Pay_Due = rs.getDouble("Pay_Due");
			System.out.printf("%30s", Invoice_ID);
			System.out.printf("%30s", Invoice_Date);
			System.out.printf("%30s", Success_Delivery);
			System.out.printf("%30s", Failed_Delivery);
			System.out.printf("%30s", Pay_Due);
			System.out.println();
		} // end while
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		return true;

	}

	public static void main(String[] args) {

		// Create the Database Object

		try {

			InvoiceMySQLAccess dio = new InvoiceMySQLAccess();

			// Configure System for Running
			Scanner keyboard = new Scanner(System.in);
			String functionNumber = "-99";
			boolean keepAppOpen = true;

			while (keepAppOpen == true) {

				// Present list of functionality and get selection
				listDIFuctionalityAvailable();
				functionNumber = keyboard.next();

				switch (functionNumber) {

				case "1":
					// Get Customer Details from the User
					System.out.printf("Enter Invoice date of day: \n");
					int DIDate1 = keyboard.nextInt();
					System.out.printf("Enter Invoice month: \n");
					String DIDate2 = keyboard.next();
					System.out.printf("Enter Invoice year: \n");
					int DIDate3 = keyboard.nextInt();
					System.out.printf("Enter the amount of successful deliveries: \n");
					int deliverySuccess = keyboard.nextInt();
					System.out.printf("Enter the amount of failed deliveries:: \n");
					int deliveryFailed = keyboard.nextInt();
					System.out.printf("Enter Payment due: \n");
					double invoicePayment = keyboard.nextDouble();
					DI DIObj = new DI(DIDate1, DIDate2, DIDate3, deliverySuccess, deliveryFailed, invoicePayment);
					// Insert Customer Details into the database
					boolean insertResult = dio.insertDInvoiceDetails(DIObj);
					if (insertResult == true)
						System.out.println("Invoice Details Saved");
					else
						System.out.println("ERROR: Invoice Details NOT Saved");
					break;

				case "2":
					// Retrieve ALL Customer Records
					ResultSet rSet = dio.retrieveAllDInvoices();
					if (rSet == null) {
						System.out.println("No Records Found");
						break;
					} else {
						boolean tablePrinted = printDITable(rSet);
						if (tablePrinted == true)
							rSet.close();
					}
					break;

				case "3":
					// Delete Invoice Record by ID
					System.out.println("Enter Delivery Invoice Id to be deleted or -99 to Clear all Rows (Option -99 is not functional)");
					String deleteDIId = keyboard.next();
					boolean deleteResult = dio.deleteDIById(Integer.parseInt(deleteDIId));
					if ((deleteResult == true) && (deleteDIId.equals("-99")))
						System.out.println("Invoice Table Emptied");
					else if (deleteResult == true)
						System.out.println("Invoice Deleted");
					else
						System.out.println("ERROR: Invoice Details NOT Deleted or Do Not Exist");
					break;

				case "4":
					// Update Customer Record by ID
					System.out.println("Enter Delivery Invoice Id to be Updated");
					int updateDIId = keyboard.nextInt();
					System.out.printf("Enter amount of successes: \n");
					int DIsuccessU = keyboard.nextInt();
					System.out.printf("Enter amount of failures: \n");
					int DIfailU = keyboard.nextInt();
					System.out.printf("Enter Payment Due: \n");
					double DIpayU = keyboard.nextDouble();
					boolean updateResult = dio.updateDIById(updateDIId, DIsuccessU, DIfailU, DIpayU);
					if (updateResult == true)
						System.out.println("Invoice Table Updated");
					else
						System.out.println("ERROR: Invoice Details NOT Updated or Do Not Exist");
					break;

				case "99":
					keepAppOpen = false;
					System.out.println("Back to Main Menu");
					break;

				default:
					System.out.println("No Valid Function Selected");
					break;
				} // end switch

			} // end while

			// Tidy up Resources
			//keyboard.close();

		}

		catch (Exception e) {
			System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
		} // end try-catch

	} // end main

}