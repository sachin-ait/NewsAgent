package report;

import DeliveryOrder.DeliveryOrder;
import biling.Bill;
import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BillMySQLAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public BillMySQLAccess() throws Exception {
        connect = base.MysqlJDBC.getConnection();
    }

    public List<DeliveryOrder> showDeliveryReport(String startDate, String endDate) {
        //Add Code here to call embedded SQL to view Customer Details
        ArrayList<DeliveryOrder> list = new ArrayList();
        try {
            statement = connect.createStatement();
            if (StringUtils.isNullOrEmpty(startDate) && StringUtils.isNullOrEmpty(endDate)) {
                resultSet = statement.executeQuery("SELECT * FROM newsagent.DeliveryOrder where DeliveryDate between '" + startDate + "' and '" + endDate + "'");
            } else {
                resultSet = statement.executeQuery("SELECT * FROM newsagent.DeliveryOrder;");
            }

            while (resultSet.next()) {
                int DeliveryOrderId = resultSet.getInt("DeliveryOrderId");
                int CustomerOrderId = resultSet.getInt("DeliveryOrderId");
                String CustName = resultSet.getString("CustName");
                String DeliveryAddress = resultSet.getString("DeliveryAddress");
                String PublicationName = resultSet.getString("PublicationName");
                String DeliveryDate = resultSet.getString("DeliveryDate");
                String DeliveryStatus = resultSet.getString("DeliveryStatus");
                DeliveryOrder deliveryOrder = new DeliveryOrder(CustName, 2,  DeliveryAddress, PublicationName, DeliveryDate);
                list.add(deliveryOrder);
                System.out.println();
            }// end while

        } catch (Exception e) {
            resultSet = null;
        }
        return null;
    }

}// end Class

