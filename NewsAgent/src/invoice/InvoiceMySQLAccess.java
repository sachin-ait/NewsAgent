package invoice;

import base.MysqlJDBC;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class InvoiceMySQLAccess {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;

    public InvoiceMySQLAccess() throws Exception {
        connect = MysqlJDBC.getConnection();
    }

    public PreparedStatement retrieveAllInvoices(String customerName, String frequency) {
        List<Invoice> invoiceList = new ArrayList<>();
        String whereCondition = "";
        if ((!StringUtils.isNullOrEmpty(customerName)) && (!StringUtils.isNullOrEmpty(frequency))) {
            whereCondition = "where CustName=? and frequency=?";
        } else if ((!StringUtils.isNullOrEmpty(customerName))) {
            whereCondition = "where CustName=?";
        } else if ((!StringUtils.isNullOrEmpty(frequency))) {
            whereCondition = "where frequency=?";
        }
        try {
            String sql = "select DeliveryOrderId,CustName,CustID,amount,name,price,frequency,(amount*price) as money from (\n" +
                    "SELECT DeliveryOrderId,CustName,CustID,amount,name,price,frequency FROM newsagent.DeliveryOrder \n" +
                    "inner join newsagent.publication\n" +
                    "on DeliveryOrder.PublicationId=publication.id\n" +
                    whereCondition +
                    ") as newvewer1\n" +
                    ";";

            preparedStatement = connect.prepareStatement(sql);
            if ((!StringUtils.isNullOrEmpty(customerName)) && (!StringUtils.isNullOrEmpty(frequency))) {
                preparedStatement.setString(1, customerName);
                preparedStatement.setString(2, frequency);
            } else if ((!StringUtils.isNullOrEmpty(customerName))) {
                preparedStatement.setString(1, customerName);
            } else if ((!StringUtils.isNullOrEmpty(frequency))) {
                preparedStatement.setString(1, frequency);
            }
        } catch (Exception e) {
        }
        return preparedStatement;
    }

    public PreparedStatement retrieveInvoice(String customerName, String frequency) {
        //Add Code here to call embedded SQL to view Customer Details
        Invoice invoice = null;
        try {
            String whereCondition = "";
            if ((!StringUtils.isNullOrEmpty(customerName)) && (!StringUtils.isNullOrEmpty(frequency))) {
                whereCondition = "where CustName=? and frequency=?";
            } else if ((!StringUtils.isNullOrEmpty(customerName))) {
                whereCondition = "where CustName=?";
            } else if ((!StringUtils.isNullOrEmpty(frequency))) {
                whereCondition = "where frequency=?";
            }
            String sql2 = "SELECT CustID,CustName,sum(money) totalMoney from (\n" +
                    "select DeliveryOrderId,CustName,CustID,amount,name,price,frequency,(amount*price) as money from (\n" +
                    "SELECT DeliveryOrderId,CustName,CustID,amount,name,price,frequency FROM newsagent.DeliveryOrder \n" +
                    "inner join newsagent.publication\n" +
                    "on DeliveryOrder.PublicationId=publication.id\n" +
                    whereCondition +
                    ") as newvewer1\n" +
                    ")as newvewer2 group by CustID\n" +
                    ";";

            /*--------------*/
            /*  The following code is ok and works normally. */
            /*--------------*/
            preparedStatement = connect.prepareStatement(sql2);
            if ((!StringUtils.isNullOrEmpty(customerName)) && (!StringUtils.isNullOrEmpty(frequency))) {
                preparedStatement.setString(1, customerName);
                preparedStatement.setString(2, frequency);
            } else if ((!StringUtils.isNullOrEmpty(customerName))) {
                preparedStatement.setString(1, customerName);
            } else if ((!StringUtils.isNullOrEmpty(frequency))) {
                preparedStatement.setString(1, frequency);
            }


            /*resultSet = preparedStatement.executeQuery();
            int id = 1;
            while (resultSet.next()) {
                int DeliveryOrderId = resultSet.getInt("CustID");
                String CustName = resultSet.getString("CustName");
                String totalMoney = resultSet.getString("totalMoney");

                invoice = new Invoice();
                invoice.setId(id);
                invoice.setName(CustName);
                invoice.setMoneyAmount(Double.parseDouble(totalMoney));
                System.out.println();
                id++;
            }// end while*/

        } catch (Exception e) {
        }
        return preparedStatement;
    }


}// end Class

