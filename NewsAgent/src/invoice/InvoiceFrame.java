/*
 * Created by JFormDesigner on Sat Apr 10 22:37:20 IST 2021
 */

package invoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author kmzjhpt pbev
 */
public class InvoiceFrame extends JFrame {
    private static QueryTableModel TableModel = new QueryTableModel();
    private String months[] = new String[] {
			"January",
			"February",
			"March",
			"April",
			"May",
			"June",
			"July",
			"August",
			"September",
			"October",
			"November",
			"December"
			};
    public InvoiceFrame() throws Exception {
        initComponents();
        initComboBox();
        refreshTable();
    }

    private void initComboBox() {
        comboBox1.addItem("select user");
        comboBox1.addItem("All");
        try {
            InvoiceMySQLAccess invoiceMySQLAccess = new InvoiceMySQLAccess();
            List<String> users = invoiceMySQLAccess.retrieveAllUsers();
            for(String userName:users){
                comboBox1.addItem(userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //comboBox2.addItem("select Month");
        //comboBox2.addItem("All");
        try {
            InvoiceMySQLAccess invoiceMySQLAccess = new InvoiceMySQLAccess();
           // List<String> users = invoiceMySQLAccess.retrieveAllFrequency();
            //comboBox2.addItem(months);
            //for(String frequency:users){
            //    comboBox2.addItem(frequency);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshTable() throws Exception {
        table1.getTableHeader().setBackground(Color.white);
        InvoiceMySQLAccess invoiceMySQLAccess = new InvoiceMySQLAccess();
        PreparedStatement stmt = invoiceMySQLAccess.retrieveAllInvoices(null, null);
        TableModel.refreshFromDB(stmt);
    }

    private void comboBox1ActionPerformed(ActionEvent e) {

    }

    private void comboBox2ActionPerformed(ActionEvent e) {
//        // TODO add your code here
//        try {
//            InvoiceMySQLAccess invoiceMySQLAccess = new InvoiceMySQLAccess();
//            String frequence = comboBox2.getSelectedItem() + "";
//            if ("select frequency".equalsIgnoreCase(frequence)) {
//                frequence = "";
//            }
////            PreparedStatement stmt = invoiceMySQLAccess.retrieveAllInvoices(frequence);
////            TableModel.refreshFromDB(stmt);
//            System.out.println();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            InvoiceMySQLAccess invoiceMySQLAccess = new InvoiceMySQLAccess();
            PreparedStatement stmt = null;
            String customerName = comboBox1.getSelectedItem() + "";
            String frequency = comboBox2.getSelectedItem() + "";
            if (!"select user".equalsIgnoreCase(customerName) && "All".equalsIgnoreCase(frequency)) {
                stmt = invoiceMySQLAccess.retrieveInvoice(customerName, null);
            } else {
                if ("select user".equalsIgnoreCase(customerName) || "All".equalsIgnoreCase(customerName)) {
                    customerName = null;
                }
                if ("select frequency".equalsIgnoreCase(frequency) || "All".equalsIgnoreCase(frequency)) {
                    frequency = null;
                }
                stmt = invoiceMySQLAccess.retrieveAllInvoices(customerName, frequency);
            }
            TableModel.refreshFromDB(stmt);
            System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        panel2 = new JPanel();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox(months);
        button1 = new JButton();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable(TableModel);

        //======== this ========
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- label1 ----
            label1.setText("Invoice");
            label1.setFont(new Font("Lucida Grande", Font.BOLD, 22));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(305, 305, 305)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(352, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
            );
        }

        //======== panel2 ========
        {

            //---- comboBox1 ----
            comboBox1.addActionListener(e -> comboBox1ActionPerformed(e));

            //---- comboBox2 ----
            comboBox2.addActionListener(e -> comboBox2ActionPerformed(e));

            //---- button1 ----
            button1.setText("Search");
            button1.addActionListener(e -> button1ActionPerformed(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                                    .addGap(47, 47, 47)
                                    .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38)
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(151, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button1, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                            .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 6, Short.MAX_VALUE))
            );
        }

        //======== panel3 ========
        {

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                    .addContainerGap(35, Short.MAX_VALUE)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24))
            );
            panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(30, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(5, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(53, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton button1;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
