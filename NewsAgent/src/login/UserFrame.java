/*
 * Created by JFormDesigner on Mon Mar 08 01:15:16 GMT 2021
 */

package login;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.*;

/**
 * @author kmzjhpt pbev
 */
public class UserFrame extends JFrame {
    private static QueryTableModel TableModel = new QueryTableModel();

    public UserFrame() {
        initComponents();
        ResultSet rSet = LoginController.retrieveAllUserAccounts();
        TableModel.refreshFromDB(rSet);
//        textArea1.setLineWrap(true);
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String userName = userName_textField2.getText();
        char[] passwordChar = passwordField1.getPassword();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < passwordChar.length; i++) {
            stringBuffer.append(passwordChar[i]);
        }
        String password = stringBuffer.toString();
        try {
            Boolean createUser = LoginController.createUser(userName, password);
            if (createUser) {
                ResultSet rSet = LoginController.retrieveAllUserAccounts();
                TableModel.refreshFromDB(rSet);
                System.out.println();
                label4.setText("create user successful");
            } else {
                System.out.println();
                label4.setText("create user failure");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void button1AncestorAdded(AncestorEvent e) {
        // TODO add your code here
    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String id = ID_textField1.getText();
        Boolean aBoolean = LoginController.deleteUserByID(id);
        if(aBoolean){
            label4.setText("delete user successful");
        }else{
            label4.setText("delete user failure");
        }
        ResultSet rSet = LoginController.retrieveAllUserAccounts();
        TableModel.refreshFromDB(rSet);
    }

    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        int ID = Integer.parseInt(ID_textField1.getText());
        char[] passwordChar = passwordField1.getPassword();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < passwordChar.length; i++) {
            stringBuffer.append(passwordChar[i]);
        }
        String password = stringBuffer.toString();
        Boolean update = LoginController.update(ID, password);
        if(update){
            label4.setText("update user successful");
        }else{
            label4.setText("update user failure");
        }
        ResultSet rSet = LoginController.retrieveAllUserAccounts();
        TableModel.refreshFromDB(rSet);
        System.out.println();
    }

    private void button4ActionPerformed(ActionEvent e) {
        // TODO add your code here
        ResultSet rSet = LoginController.retrieveAllUserAccounts();
        if (rSet == null) {
            System.out.println("No Records Found");
        } else {
            try {
                TableModel.refreshFromDB(rSet);
                boolean tablePrinted = printUserTable(rSet);
                if (tablePrinted == true)
                    rSet.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable(TableModel);
        panel2 = new JPanel();
        ID_textField1 = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        label5 = new JLabel();
        passwordField1 = new JPasswordField();
        userName_textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button4 = new JButton();
        button3 = new JButton();
        label4 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(table1);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {

            //---- label2 ----
            label2.setText("ID:");

            //---- label3 ----
            label3.setText("UserName");

            //---- label5 ----
            label5.setText("Password");

            //---- button1 ----
            button1.setText("Create");
            button1.addAncestorListener(new AncestorListener() {
                @Override
                public void ancestorAdded(AncestorEvent e) {
                    button1AncestorAdded(e);
                }
                @Override
                public void ancestorMoved(AncestorEvent e) {}
                @Override
                public void ancestorRemoved(AncestorEvent e) {}
            });
            button1.addActionListener(e -> button1ActionPerformed(e));

            //---- button2 ----
            button2.setText("Delete by ID");
            button2.addActionListener(e -> button2ActionPerformed(e));

            //---- button4 ----
            button4.setText("View");
            button4.addActionListener(e -> button4ActionPerformed(e));

            //---- button3 ----
            button3.setText("Update");
            button3.addActionListener(e -> button3ActionPerformed(e));

            //---- label4 ----
            label4.setText("");
            label4.setForeground(Color.red);

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(button3, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(button1, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label5, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(passwordField1, GroupLayout.Alignment.LEADING)
                                    .addComponent(userName_textField2, GroupLayout.Alignment.LEADING)
                                    .addComponent(ID_textField1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button4, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(ID_textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(userName_textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(label4)
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button1)
                            .addComponent(button2))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button3)
                            .addComponent(button4))
                        .addGap(20, 20, 20))
            );
        }

        //---- label1 ----
        label1.setText("User");
        label1.setFont(new Font("Lucida Grande", Font.BOLD, 20));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(71, Short.MAX_VALUE)
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(331, 331, 331)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(338, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(label1)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(63, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
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
            String userName = rs.getString("userName");
            String password = rs.getString("password");
            stringBuffer.append(id + "         " + userName + "         " + password + "      ");

            System.out.println();
        }// end while
//        textArea1.setText(stringBuffer.toString());
        return true;

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel panel2;
    private JTextField ID_textField1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label5;
    private JPasswordField passwordField1;
    private JTextField userName_textField2;
    private JButton button1;
    private JButton button2;
    private JButton button4;
    private JButton button3;
    private JLabel label4;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
