package docket;


import java.sql.ResultSet;
import java.util.Scanner;

public class CommandLine {

    private static void listCustomerFuctionalityAvailable() {

        //Present Customer with Functionality Options

        System.out.println(" ");
        System.out.println("=============================================");
        System.out.println("Please choose ONE of the following options:");
        System.out.println("1. Create Docket Account");
        System.out.println("2. View Docket Records");
        System.out.println("3. Delete Docket Record by ID");
        System.out.println("4. Modify Docket INFO ");
<<<<<<< HEAD
        System.out.println("99. Go Back to Main Menu");
=======
        System.out.println("99. Close the NewsAgent Application");
>>>>>>> origin/dev-Weijiang
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
            String docketName = rs.getString("docketName");
            String num = rs.getString("num");
            System.out.printf("%30s", id);
            System.out.printf("%30s", docketName);
            System.out.printf("%30s", num);
            System.out.println();
        }// end while
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        return true;

    }

    public static void main(String[] args) {

        //Create the Database Object

        try {

            DocketMySQLAccess dao = new DocketMySQLAccess();

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
                        //Get docket Details from the User
                        System.out.printf("Enter docket name: \n");
                        String userName = keyboard.next();
                        System.out.printf("Enter docket num: \n");
                        String num = keyboard.next();
                        Docket docket = new Docket(userName, Integer.parseInt(num));

                        //Insert docket Details into the database
                        boolean insertResult = dao.insertDocket(docket);
                        if (insertResult == true)
                            System.out.println("Bill docket Saved");
                        else
                            System.out.println("ERROR: docket NOT Saved");
                        break;

                    case "2":
                        //Retrieve ALL docket Records
                        ResultSet rSet = dao.retrieveAllDocket();
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
                        System.out.println("Enter docket Id to be deleted or -99 to Clear all Rows");
                        String deleteDocketId = keyboard.next();
                        boolean deleteResult = dao.deleteDocketById(Integer.parseInt(deleteDocketId));
                        if ((deleteResult == true) && (deleteDocketId.equals("-99")))
                            System.out.println("docket Table Emptied");
                        else if (deleteResult == true)
                            System.out.println("docket Deleted");
                        else
                            System.out.println("ERROR: docket Details NOT Deleted or Do Not Exist");
                        break;

                    case "4":
                        //update  Bill Info
                        System.out.printf("Enter Docket Id: \n");
                        int Id = Integer.parseInt(keyboard.next());
                        System.out.printf("Enter Docket new Name: \n");
                        String name = keyboard.next();
                        System.out.printf("Enter Docket new num: \n");
                        String num1 = keyboard.next();

                        Docket updateBill = new Docket(name, Integer.parseInt(num1));
                        boolean updateResult = dao.updateBill(Id, updateBill);

                        if (updateResult == true)
                            System.out.println("docket update success");
                        else
                            System.out.println("ERROR:Docket update Unsuccess");
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
<<<<<<< HEAD
           // keyboard.close();
=======
            keyboard.close();
>>>>>>> origin/dev-Weijiang

        } catch (Exception e) {
            System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
        } // end try-catch


    } // end main


}