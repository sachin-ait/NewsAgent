package report;


import DeliveryOrder.DeliveryOrder;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportController {

    public static Object[][] showReport() {
        ReportSQLAccess reportSQLAccess = new ReportSQLAccess();
        ResultSet resultSet = reportSQLAccess.retrieveAllDeliveryOrderAccounts();
        Object[][] deliveryOrders = null;
        try {
            int dataLength = dataLength(resultSet);
            resultSet = reportSQLAccess.retrieveAllDeliveryOrderAccounts();
            deliveryOrders = packageData(resultSet, dataLength);
        } catch (Exception deliveryOrderExceptionHandler) {
            deliveryOrderExceptionHandler.printStackTrace();
        }
        return deliveryOrders;
    }


    private static int dataLength(ResultSet rs) throws Exception {
        int length = 0;
        while (rs.next()) {
            length++;
        }// end while
        return length;
    }

    private static Object[][] packageData(ResultSet rs, int dataLength) throws Exception {
        List<DeliveryOrder> list = new ArrayList<DeliveryOrder>();
        Object[][] deliveryData = new Object[dataLength][3];
        System.out.println();
        int row = 0;
        int column = 0;
        while (rs.next()) {
//            int id = rs.getInt("DeliveryOrderId");
//            deliveryData[row][column] = id;
//            column++;
            String custName = rs.getString("CustName");
            deliveryData[row][column] = custName;
            column++;
            String publicationName = rs.getString("PublicationName");
            deliveryData[row][column] = publicationName;
            column++;
            String deliveryDate = rs.getString("DeliveryDate");
            deliveryData[row][column] = deliveryDate;
            column = 0;
            row++;
            System.out.println();
        }// end while

        return deliveryData;
    }


}
