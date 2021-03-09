/*
 * Created by JFormDesigner on Mon Mar 08 19:39:38 GMT 2021
 */

package biling;

import java.awt.event.*;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author kmzjhpt pbev
 */
public class BillFrame extends JFrame {
    public BillFrame() {
        initComponents();
        textArea1.setLineWrap(true);
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            String userName = textField3.getText();
            String address = textField4.getText();
            String fee = textField5.getText();
            String billDate1 = textField6.getText();
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date billDate = dateFormat.parse(billDate1);
            Boolean bill = BillController.createBill(userName, address, fee, billDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String ID = textField1.getText();
        BillController.deleteBillByID(ID);

    }

    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            int ID = Integer.parseInt(textField1.getText());
            String customerName = textField3.getText();
            String customerAddress = textField4.getText();
            Double fee = Double.parseDouble(textField5.getText());
            String billDate1 = textField6.getText();
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date billDate = dateFormat.parse(billDate1);
            Bill bill = new Bill(customerName, customerAddress, fee, billDate);
            BillController.update(ID, bill);
        } catch (Exception billExceptionHandler) {
            billExceptionHandler.printStackTrace();
        }

    }

    private void button4ActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            ResultSet resultSet = BillController.retrieveAllUserAccounts();
            printUserTable(resultSet);
            System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private boolean printUserTable(ResultSet rs) throws Exception {
        System.out.println("Table: " + rs.getMetaData().getTableName(1));
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//            System.out.printf("%20s", rs.getMetaData().getColumnName(i));
        }
        System.out.println();
        StringBuffer stringBuffer = new StringBuffer();
        while (rs.next()) {
            int id = rs.getInt("id");
            String CustomerName = rs.getString("CustomerName");
            String CustomerAddress = rs.getString("CustomerAddress");
            String fee = rs.getString("fee");
            String billDate = rs.getString("billDate");
            stringBuffer.append(id + "         " + CustomerName + "          " + CustomerAddress + "      " + fee + "      " + billDate + "      ");

            System.out.println();
        }// end while
        textArea1.setText(stringBuffer.toString());
        return true;

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        textField3 = new JTextField();
        label3 = new JLabel();
        textField4 = new JTextField();
        label4 = new JLabel();
        textField5 = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        textField6 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Bill");

        //---- label2 ----
        label2.setText("Id:");

        //---- label3 ----
        label3.setText("cusAddress:");

        //---- label4 ----
        label4.setText("fee:");

        //---- label5 ----
        label5.setText("cusName:");

        //---- label6 ----
        label6.setText("billDate:");

        //---- button1 ----
        button1.setText("Create");
        button1.addActionListener(e -> button1ActionPerformed(e));

        //---- button2 ----
        button2.setText("Delete by ID");
        button2.addActionListener(e -> button2ActionPerformed(e));

        //---- button3 ----
        button3.setText("Update");
        button3.addActionListener(e -> button3ActionPerformed(e));

        //---- button4 ----
        button4.setText("View");
        button4.addActionListener(e -> button4ActionPerformed(e));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(button1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(button3, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(label2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(label5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(label6, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                        .addComponent(label4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField5, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField6, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addGap(222, 222, 222))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(label1)
                                .addGap(26, 26, 26)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(button3))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label2))
                                                .addGap(27, 27, 27)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label5)
                                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(29, 29, 29)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(label3)
                                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label4)
                                                        .addComponent(textField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label6)
                                                        .addComponent(textField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(35, 35, 35)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(button1)
                                                        .addComponent(button2))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(button4)))
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField3;
    private JLabel label3;
    private JTextField textField4;
    private JLabel label4;
    private JTextField textField5;
    private JLabel label5;
    private JLabel label6;
    private JTextField textField6;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
