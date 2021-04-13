package invoice;

import base.MysqlJDBC;
import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InvoiceMySQLAccess {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;

    public InvoiceMySQLAccess() throws Exception {
        connect = MysqlJDBC.getConnection();
    }

    public List<String> retrieveAllUsers() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT CustName FROM newsagent.DeliveryOrder group by CustName;";
        preparedStatement = connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String CustName = resultSet.getString("CustName");
            list.add(CustName);
        }
        return list;
    }

    public List<String> retrieveAllFrequency() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT frequence FROM newsagent.publication group by frequence;";
        preparedStatement = connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String CustName = resultSet.getString("frequence");
            list.add(CustName);
        }
        return list;
    }

    public PreparedStatement retrieveAllInvoices(String customerName, String frequence) {
        List<Invoice> invoiceList = new ArrayList<>();
        String whereCondition = "";
        if ((!StringUtils.isNullOrEmpty(customerName)) && (!StringUtils.isNullOrEmpty(frequence))) {
            whereCondition = "where CustName=? and frequence=?";
        } else if ((!StringUtils.isNullOrEmpty(customerName))) {
            whereCondition = "where CustName=?";
        } else if ((!StringUtils.isNullOrEmpty(frequence))) {
            whereCondition = "where frequence=?";
        }
        try {
            String sql = "select DeliveryOrderId,CustName,CustID,pAmount,name,price,frequence,(pAmount*price) as money from (\n" +
                    "SELECT DeliveryOrderId,CustName,CustID,pAmount,name,price,frequence FROM newsagent.DeliveryOrder \n" +
                    "inner join newsagent.publication\n" +
                    "on DeliveryOrder.PublicationName=publication.name\n" +
                    whereCondition +
                    ") as newvewer1\n" +
                    ";";

            preparedStatement = connect.prepareStatement(sql);
            if ((!StringUtils.isNullOrEmpty(customerName)) && (!StringUtils.isNullOrEmpty(frequence))) {
                preparedStatement.setString(1, customerName);
                preparedStatement.setString(2, frequence);
            } else if ((!StringUtils.isNullOrEmpty(customerName))) {
                preparedStatement.setString(1, customerName);
            } else if ((!StringUtils.isNullOrEmpty(frequence))) {
                preparedStatement.setString(1, frequence);
            }
        } catch (Exception e) {
        }
        return preparedStatement;
    }

    public PreparedStatement retrieveInvoice(String customerName, String frequence) {
        //Add Code here to call embedded SQL to view Customer Details
        Invoice invoice = null;
        try {
            String whereCondition = "";
            if ((!StringUtils.isNullOrEmpty(customerName)) && (!StringUtils.isNullOrEmpty(frequence))) {
                whereCondition = "where CustName=? and frequence=?";
            } else if ((!StringUtils.isNullOrEmpty(customerName))) {
                whereCondition = "where CustName=?";
            } else if ((!StringUtils.isNullOrEmpty(frequence))) {
                whereCondition = "where frequence=?";
            }
            String sql2 = "SELECT CustID,CustName,sum(money) totalMoney from (\n" +
                    "select DeliveryOrderId,CustName,CustID,pAmount,name,price,frequence,(pAmount*price) as money from (\n" +
                    "SELECT DeliveryOrderId,CustName,CustID,pAmount,name,price,frequence FROM newsagent.DeliveryOrder \n" +
                    "inner join newsagent.publication\n" +
                    "on DeliveryOrder.PublicationName=publication.name\n" +
                    whereCondition +
                    ") as newvewer1\n" +
                    ")as newvewer2 group by CustID\n" +
                    ";";

            /*--------------*/
            /*  The following code is ok and works normally. */
            /*--------------*/
            preparedStatement = connect.prepareStatement(sql2);
            if ((!StringUtils.isNullOrEmpty(customerName)) && (!StringUtils.isNullOrEmpty(frequence))) {
                preparedStatement.setString(1, customerName);
                preparedStatement.setString(2, frequence);
            } else if ((!StringUtils.isNullOrEmpty(customerName))) {
                preparedStatement.setString(1, customerName);
            } else if ((!StringUtils.isNullOrEmpty(frequence))) {
                preparedStatement.setString(1, frequence);
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

