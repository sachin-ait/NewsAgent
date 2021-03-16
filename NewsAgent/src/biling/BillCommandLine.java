package biling;


import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BillCommandLine {

    private static void listCustomerFuctionalityAvailable() {

        //Present Customer with Functionality Options

        System.out.println(" ");
        System.out.println("=============================================");
        System.out.println("Please choose ONE of the following options:");
        System.out.println("1. Create Bill Account");
        System.out.println("2. View Bill Records");
        System.out.println("3. Delete Bill Record by ID");
        System.out.println("4. Modify Bill INFO ");
      //  System.out.println("99. Close the NewsAgent Application");
        System.out.println("99. Go Back to Main Menu");
        System.out.println("=============================================");
        System.out.println(" ");

    }


    private static boolean printUserTable(ResultSet rs) throws Exception {

        //Print The Contents of the Full Customer Table

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Table: " + rs.getMetaData().getTableName(1));
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.printf("%30s", rs.getMetaData().getColumnName(i));
        }
        System.out.println();
        while (rs.next()) {
            int id = rs.getInt("id");
            String customerName = rs.getString("customerName");
            String customerAddress = rs.getString("customerAddress");
            String fee = rs.getString("fee");
            String billDate = rs.getString("billDate");
            System.out.printf("%30s", id);
            System.out.printf("%30s", customerName);
            System.out.printf("%30s", customerAddress);
            System.out.printf("%30s", fee);
            System.out.printf("%30s", billDate);
            System.out.println();
        }// end while
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        return true;

    }

    public static void main(String[] args) {

        //Create the Database Object

        try {

            BillMySQLAccess dao = new BillMySQLAccess();

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
                        System.out.printf("Enter Bill Customer Name: \n");
                        String userName = keyboard.next();
                        System.out.printf("Enter Bill Customer Address: \n");
                        String address = keyboard.next();
                        System.out.printf("Enter Bill Fee: \n");
                        String fee = keyboard.next();
                        System.out.printf("Enter Bill Date: pattern: 03-12-2020 \n");
                        String billDate1 = keyboard.next();
                        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        Date billDate = dateFormat.parse(billDate1);

                        Bill bill = new Bill(userName, address, Double.parseDouble(fee), billDate);

                        //Insert Customer Details into the database
                        boolean insertResult = dao.insertBill(bill);
                        if (insertResult == true)
                            System.out.println("Bill Details Saved");
                        else
                            System.out.println("ERROR: Bill Details NOT Saved");
                        break;

                    case "2":
                        //Retrieve ALL Customer Records
                        ResultSet rSet = dao.retrieveAllBills();
                        if (rSet == null) {
                            System.out.println("No Records Found");
                            break;
                        } else {
                            boolean tablePrinted = printUserTable(rSet);
                            if (tablePrinted == true)
                                rSet.close();
                        }
                        break;

                    case "3":
                        //Delete Customer Record by ID
                        System.out.println("Enter Bill Id to be deleted or -99 to Clear all Rows");
                        String deleteBillId = keyboard.next();
                        boolean deleteResult = dao.deleteBillById(Integer.parseInt(deleteBillId));
                        if ((deleteResult == true) && (deleteBillId.equals("-99")))
                            System.out.println("User Table Emptied");
                        else if (deleteResult == true)
                            System.out.println("Bill Deleted");
                        else
                            System.out.println("ERROR: Bill Details NOT Deleted or Do Not Exist");
                        break;

                    case "4":
                        //update  Bill Info
                        System.out.printf("Enter Bill Id: \n");
                        int Id = Integer.parseInt(keyboard.next());
                        System.out.printf("Enter customer new Name: \n");
                        String customerName = keyboard.next();
                        System.out.printf("Enter customer new Address: \n");
                        String customerAddress = keyboard.next();
                        System.out.printf("Enter customer new fee: \n");
                        String fee1 = keyboard.next();
                        System.out.printf("Enter customer new bill Date: pattern: 03-12-2020 \n");
                        String billDateStr = keyboard.next();
                        DateFormat dateFormat2 = new SimpleDateFormat("MM-dd-yyyy");
                        Date billDate2 = dateFormat2.parse(billDateStr);
                        Bill updateBill = new Bill(customerName, customerAddress, Double.parseDouble(fee1), billDate2);
                        boolean updateResult = dao.updateBill(Id, updateBill);

                        if (updateResult == true)
                            System.out.println("Bill update success");
                        else
                            System.out.println("ERROR:Bill update Unsuccess");
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
      //      keyboard.close();

        } catch (Exception e) {
            System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
        } // end try-catch


    } // end main


}