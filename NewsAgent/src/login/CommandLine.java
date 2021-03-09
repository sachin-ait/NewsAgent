package login;

import java.sql.ResultSet;
import java.util.Scanner;

public class CommandLine {

    private static void listCustomerFuctionalityAvailable() {

        //Present Customer with Functionality Options

        System.out.println(" ");
        System.out.println("=============================================");
        System.out.println("Please choose ONE of the following options:");
        System.out.println("1. Create User Account");
        System.out.println("2. View ALL User Records");
        System.out.println("3. Delete User Record by ID");
        System.out.println("4. Modify User password ");
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
            String userName = rs.getString("userName");
            String password = rs.getString("password");
            System.out.printf("%30s", id);
            System.out.printf("%30s", userName);
            System.out.printf("%30s", password);
            System.out.println();
        }// end while
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        return true;

    }

    public static void main(String[] args) {

        //Create the Database Object

        try {

            LoginMySQLAccess dao = new LoginMySQLAccess();

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
                        System.out.printf("Enter User Name: \n");
                        String userName = keyboard.next();
                        System.out.printf("Enter User password: \n");
                        String password = keyboard.next();
                        User user = new User(userName,password);

                        //Insert Customer Details into the database
                        boolean insertResult = dao.insertUserDetailsAccount(user);
                        if (insertResult == true)
                            System.out.println("Customer Details Saved");
                        else
                            System.out.println("ERROR: Customer Details NOT Saved");
                        break;

                    case "2":
                        //Retrieve ALL Customer Records
                        ResultSet rSet = dao.retrieveAllUserAccounts();
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
                        System.out.println("Enter user Id to be deleted or -99 to Clear all Rows");
                        String deleteUserId = keyboard.next();
                        boolean deleteResult = dao.deleteCustomerById(Integer.parseInt(deleteUserId));
                        if ((deleteResult == true) && (deleteUserId.equals("-99")))
                            System.out.println("User Table Emptied");
                        else if (deleteResult == true)
                            System.out.println("User Deleted");
                        else
                            System.out.println("ERROR: User Details NOT Deleted or Do Not Exist");
                        break;

                    case "4":
                        //update password
                        System.out.printf("Enter User Id: \n");
                        int Id = Integer.parseInt(keyboard.next());
                        System.out.printf("Enter User new  password: \n");
                        String updatePassword = keyboard.next();
                        boolean updateResult = dao.updateUserDetailsAccount(Id, updatePassword);

                        if (updateResult == true)
                            System.out.println("user password set success");
                        else
                            System.out.println("ERROR: user password set Unsuccess");
                        break;



                    case "99":
                        keepAppOpen = false;
                        System.out.println("Main Menu");
                        break;

                    default:
                        System.out.println("No Valid Function Selected");
                        break;
                } // end switch

            }// end while

            //Tidy up Resources
            // not needed init.java will take care of it
            //keyboard.close();

        } catch (Exception e) {
            System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
        } // end try-catch


    } // end main

    public static int login() throws Exception {
    	 LoginMySQLAccess dao = new LoginMySQLAccess();

         // Configure System for Running
        Scanner keyboard = new Scanner(System.in);
        System.out.printf("Enter userName: \n");
        String userName2 = keyboard.next();
        System.out.printf("Enter password: \n");
        String password2 = keyboard.next();
        ResultSet resultSet2 = dao.retrieveUser(userName2,password2);
        if (resultSet2.next())
        {
            System.out.println("Login success");
            return 0;
        }
        else
            System.out.println("ERROR: Login Unsuccess");
        return 1;
	}

}