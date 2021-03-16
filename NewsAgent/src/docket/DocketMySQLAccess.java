package docket;

import java.sql.*;


public class DocketMySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

<<<<<<< HEAD

=======
    final private String host = "localhost:3306";
    final private String user = "root";
    final private String password = "root1234";
>>>>>>> origin/dev-Weijiang


    public DocketMySQLAccess() throws Exception {

<<<<<<< HEAD
       connect= base.MysqlJDBC.getConnection();
=======
        try {

            //Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://" + host + "/newsagent?" + "user=" + user + "&password=" + password);
        } catch (Exception e) {
            throw e;
        }

>>>>>>> origin/dev-Weijiang

    }

    public boolean insertDocket(Docket docket) {

        boolean insertSucessfull = true;
        //Add Code here to call embedded SQL to insert Customer into DB
        try {
            //Create prepared statement to issue SQL query to the database
            preparedStatement = connect.prepareStatement("insert into newsagent.docket values (default ,?, ?)");
            preparedStatement.setString(1, docket.getDocketName());
            preparedStatement.setInt(2, docket.getNum());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            insertSucessfull = false;
        }

        return insertSucessfull;

    }// end insertCustomerDetailsAccount

    public ResultSet retrieveAllDocket() {

        //Add Code here to call embedded SQL to view Customer Details

        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("Select * from newsagent.docket");

        } catch (Exception e) {
            resultSet = null;
        }
        return resultSet;
    }

    public boolean deleteDocketById(int docketId) {
        boolean deleteSucessfull = true;
        //Add Code here to call embedded SQL to insert Customer into DB
        try {
            //Create prepared statement to issue SQL query to the database
            if (docketId == -99)
                //Delete all entries in Table
                preparedStatement = connect.prepareStatement("delete from newsagent.docket");
            else
                //Delete a particular Customer
                preparedStatement = connect.prepareStatement("delete from newsagent.docket where id = " + docketId);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            deleteSucessfull = false;
        }
        return deleteSucessfull;
    }

    public boolean updateBill(int id, Docket docket) {
        boolean updateSucessfull = true;
        //Add Code here to call embedded SQL to insert Customer into DB
        try {
            preparedStatement = connect.prepareStatement("UPDATE newsagent.docket SET name = ?,num=? WHERE id=?;");
            preparedStatement.setString(1, docket.getDocketName());
            preparedStatement.setInt(2, docket.getNum());
            preparedStatement.setString(3, String.valueOf(id));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            updateSucessfull = false;
        }
        return updateSucessfull;
    }// end insertCustomerDetailsAccount

}// end Class

