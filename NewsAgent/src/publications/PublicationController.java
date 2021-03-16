package publications;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class PublicationController {


    public static void main(String[] args) {

        //Create the Database Object

        try {

//            MySQLAccess dao = new MySQLAccess();

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
                        System.out.printf("Enter publication Name: \n");
                        String name = keyboard.next();
                        System.out.printf("Enter publication account: \n");
                        int amount = Integer.parseInt(keyboard.next() + "");

                        Publication publication = new Publication();
                        publication.setName(name);
                        publication.setAmount(amount);

                        boolean insertResult = insert(publication);
                        //Insert Customer Details into the database
                        if (insertResult == true)
                            System.out.println("Publication Details Saved");
                        else
                            System.out.println("ERROR: Publication Details NOT Saved");
                        break;

                    case "2":
                        //Retrieve ALL Customer Records
                        ArrayList<Publication> publications = queryResource();
                        if (publications.size() < 0) {
                            System.out.println("No Records Found");
                            break;
                        } else {
                            boolean tablePrinted = printCustomerTable(publications);
                        }
                        break;

                    case "3":
                        //Delete Customer Record by ID
                        System.out.println("Enter Publication Id to be deleted Rows");
                        int deleteCustId = Integer.parseInt(keyboard.next() + "");
                        boolean delete = delete(deleteCustId);
                        if (delete == true)
                            System.out.println("Publication Deleted");
                        else
                            System.out.println("ERROR: Publication Details NOT Deleted or Do Not Exist");
                        break;


                    case "4":
                        //Delete Customer Record by ID
                        System.out.printf("Enter publication id: \n");
                        int deleteCustId1 = Integer.parseInt(keyboard.next() + "");
                        System.out.printf("Enter publication update Name: \n");
                        String name1 = keyboard.next();
                        System.out.printf("Enter publication update account: \n");
                        int amount1 = Integer.parseInt(keyboard.next() + "");

                        Publication publication1 = new Publication();
                        publication1.setName(name1);
                        publication1.setAmount(amount1);
                        update(publication1, deleteCustId1);
                        System.out.println("update successed");
                        break;
<<<<<<< HEAD
                    
                    case "99":
                    	keepAppOpen= false;
                    	break;
=======
>>>>>>> origin/dev-Weijiang
                } // end switch

            }// end while

            //Tidy up Resources
<<<<<<< HEAD
         //   keyboard.close();
=======
            keyboard.close();
>>>>>>> origin/dev-Weijiang

        } catch (Exception e) {
            System.out.println("PROGRAM TERMINATED - ERROR MESSAGE:" + e.getMessage());
        } // end try-catch
    }


    private static void listCustomerFuctionalityAvailable() {

        //Present Customer with Functionality Options

        System.out.println(" ");
        System.out.println("=============================================");
        System.out.println("Please choose ONE of the following options:");
        System.out.println("1. Create Publication Account");
        System.out.println("2. View ALL Publication Records");
        System.out.println("3. Delete Publication Record by ID");
        System.out.println("4. update Publication Record by ID");
<<<<<<< HEAD
       // System.out.println("99. Close the NewsAgent Application");
        System.out.println("99. Go Back to Main Menu");
=======
        System.out.println("99. Close the NewsAgent Application");
>>>>>>> origin/dev-Weijiang
        System.out.println("=============================================");
        System.out.println(" ");

    }

    private static boolean printCustomerTable(ArrayList<Publication> publications) throws Exception {

        for (int i = 0; i < publications.size(); i++) {
            Publication publication = publications.get(i);
            int id = publication.getId();
            String name = publication.getName();
            int amount = publication.getAmount();
            System.out.printf("%30s", id);
            System.out.printf("%30s", name);
            System.out.printf("%30s", amount);
            System.out.println();
        }// end while
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        return true;

    }


    public static boolean insert(Publication publication) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String name = publication.getName();
        int amount = publication.getAmount();
        String insertSql = "insert into newsagent.publication (name,amount) values ('" + name + "'," + amount + ")";
        statement.execute(insertSql);
        System.out.println();
        return true;
    }

    public static boolean delete(int id) {
        boolean flag = false;
        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            String insertSql = "DELETE FROM publication WHERE id='" + id + "'";
            statement.execute(insertSql);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static boolean update(Publication publication, int id) {
        boolean flag = false;

        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            String insertSql = "UPDATE publication SET name = '" + publication.getName() + "', amount= " + publication.getAmount() + " WHERE id = " + id + "";
            statement.execute(insertSql);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static ArrayList<Publication> queryResource() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String querySql = "SELECT * FROM newsagent.publication";
        ResultSet rs = statement.executeQuery(querySql);
        ArrayList<Publication> publications = new ArrayList<>();
        while (rs.next()) { //Each time next() is performed, the value of a row of attributes will be traversed
            Publication publication = new Publication();
            int id = rs.getInt("id"); //Get the data of the first column (id)
            String name = rs.getString("name"); //Get the data in the second column (userName)
            int amount = rs.getInt("amount");

            publication.setId(id);
            publication.setName(name);
            publication.setAmount(amount);
            publications.add(publication);
        }
        return publications;
    }
}
