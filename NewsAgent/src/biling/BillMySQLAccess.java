package biling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class BillMySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    final private String host = "localhost:3306";
    final private String user = "root";
    final private String password = "root1234";


    public BillMySQLAccess() throws Exception {

    	connect= base.MysqlJDBC.getConnection();

    }

    public boolean insertBill(Bill bill) {

        boolean insertSucessfull = true;

        //Add Code here to call embedded SQL to insert Customer into DB

        try {
            //Create prepared statement to issue SQL query to the database
            preparedStatement = connect.prepareStatement("insert into newsagent.bill values (default ,?, ?,?,?)");
            preparedStatement.setString(1, bill.getCustomerName());
            preparedStatement.setString(2, bill.getCustomerAddress());
            preparedStatement.setDouble(3, bill.getFee());
            preparedStatement.setDate(4, new java.sql.Date(bill.getBillDate().getTime()));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            insertSucessfull = false;
        }

        return insertSucessfull;

    }// end insertCustomerDetailsAccount

    public ResultSet retrieveAllBills() {

        //Add Code here to call embedded SQL to view Customer Details

        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("Select * from newsagent.bill");

        } catch (Exception e) {
            resultSet = null;
        }
        return resultSet;
    }

    public boolean deleteBillById(int billId) {

        boolean deleteSucessfull = true;

        //Add Code here to call embedded SQL to insert Customer into DB

        try {

            //Create prepared statement to issue SQL query to the database
            if (billId == -99)
                //Delete all entries in Table
                preparedStatement = connect.prepareStatement("delete from newsagent.bill");
            else
                //Delete a particular Customer
                preparedStatement = connect.prepareStatement("delete from newsagent.bill where id = " + billId);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            deleteSucessfull = false;
        }

        return deleteSucessfull;

    }

    public boolean updateBill(int id, Bill bill) {

        boolean updateSucessfull = true;

        //Add Code here to call embedded SQL to insert Customer into DB

        try {
            preparedStatement = connect.prepareStatement("UPDATE newsagent.bill SET customerName = ?,customerAddress=?,fee=?,billDate=? WHERE id=?;");
            preparedStatement.setString(1, bill.getCustomerName());
            preparedStatement.setString(2, bill.getCustomerAddress());
            preparedStatement.setDouble(3, bill.getFee());
            preparedStatement.setDate(4, new java.sql.Date(bill.getBillDate().getTime()));

            preparedStatement.setString(5, String.valueOf(id));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            updateSucessfull = false;
        }

        return updateSucessfull;

    }// end insertCustomerDetailsAccount


}// end Class

