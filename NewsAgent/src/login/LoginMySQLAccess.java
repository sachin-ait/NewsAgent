package login;

import java.sql.*;

import base.MysqlJDBC;


public class LoginMySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    final private String host = "localhost:3306";
    final private String user = "root";
    final private String password = "root1234";


    public LoginMySQLAccess() throws Exception {

        connect = MysqlJDBC.getConnection();

    }

    public boolean insertUserDetailsAccount(User user) {

        boolean insertSucessfull = true;

        //Add Code here to call embedded SQL to insert Customer into DB

        try {
            //Create prepared statement to issue SQL query to the database
            preparedStatement = connect.prepareStatement("insert into newsagent.user values (default ,?, ?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                insertSucessfull = false;
            }

        } catch (Exception e) {
            insertSucessfull = false;
        }

        return insertSucessfull;

    }// end insertCustomerDetailsAccount

    public ResultSet retrieveAllUserAccounts() {

        //Add Code here to call embedded SQL to view Customer Details

        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("Select * from newsagent.user");

        } catch (Exception e) {
            resultSet = null;
        }
        return resultSet;
    }


    public ResultSet retrieveUser(String userName, String password) {

        //Add Code here to call embedded SQL to view Customer Details

        try {
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("select * from newsagent.user where userName  = ? and password = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            resultSet = null;
        }
        return resultSet;
    }

    public boolean deleteCustomerById(int userId) {

        boolean deleteSucessfull = true;

        //Add Code here to call embedded SQL to insert Customer into DB

        try {

            //Create prepared statement to issue SQL query to the database
            if (userId == -99)
                //Delete all entries in Table
                preparedStatement = connect.prepareStatement("delete from newsagent.user");
            else
                //Delete a particular Customer
                preparedStatement = connect.prepareStatement("delete from newsagent.user where id = " + userId);
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                deleteSucessfull = false;
            }

        } catch (Exception e) {
            deleteSucessfull = false;
        }

        return deleteSucessfull;

    }

    public boolean updateUserDetailsAccount(int id, String password) {

        boolean updateSucessfull = true;

        //Add Code here to call embedded SQL to insert Customer into DB

        try {
            preparedStatement = connect.prepareStatement("UPDATE newsagent.user SET password = ? WHERE id=?;");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, String.valueOf(id));
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                updateSucessfull = false;
            }

        } catch (Exception e) {
            updateSucessfull = false;
        }

        return updateSucessfull;

    }// end insertCustomerDetailsAccount


}// end Class

