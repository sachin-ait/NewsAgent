package biling;

import java.sql.ResultSet;
import java.util.Date;

public class BillController {

    private static BillMySQLAccess dao = null;

    static {
        try {
            dao = new BillMySQLAccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet retrieveAllUserAccounts() {
        ResultSet resultSet = dao.retrieveAllBills();
        return resultSet;
    }


    public static Boolean update(int ID, Bill bill) {
        boolean updateResult = dao.updateBill(ID, bill);
        return updateResult;
    }

    public static Boolean deleteBillByID(String ID) {
        boolean deleteResult = dao.deleteBillById(Integer.parseInt(ID));
        return deleteResult;
    }

    public static Boolean createBill(String userName, String address, String fee, Date billDate) throws Exception {
        Bill bill = new Bill(userName, address, Double.parseDouble(fee), billDate);
        //Insert Customer Details into the database
        boolean insertResult = dao.insertBill(bill);
        return insertResult;
    }

}
