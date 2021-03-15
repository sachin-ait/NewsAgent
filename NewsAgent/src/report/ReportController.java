package report;

import DeliveryOrder.DeliveryOrder;

import java.sql.ResultSet;

public class ReportController {

    public static DeliveryOrder showReport() {

        ReportSQLAccess reportSQLAccess=new ReportSQLAccess();
        ResultSet resultSet = reportSQLAccess.retrieveAllDeliveryOrderAccounts();

        return null;
    }



}
