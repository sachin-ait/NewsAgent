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
        label1 = new JLabel();
        label2 = new JLabel();
        ID_textField1 = new JTextField();
        label3 = new JLabel();
        label5 = new JLabel();
        userName_textField2 = new JTextField();
        passwordField1 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label4 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("User");
        label1.setFont(new Font("Lucida Grande", Font.BOLD, 20));

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

        //---- label4 ----
        label4.setText("text");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(95, 95, 95)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                            .addGap(22, 22, 22)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(passwordField1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addComponent(ID_textField1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addComponent(userName_textField2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button3, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                .addComponent(button1, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                            .addGap(64, 64, 64)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button2, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                .addComponent(button4, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                        .addComponent(label4, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(label1)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(ID_textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label3)
                                .addComponent(userName_textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(label5))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGap(12, 12, 12)
                            .addComponent(label4)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(button1)
                                .addComponent(button2))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button3)
                                .addComponent(button4)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(scrollPane1)))
                    .addContainerGap(186, Short.MAX_VALUE))
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
    private JLabel label1;
    private JLabel label2;
    private JTextField ID_textField1;
    private JLabel label3;
    private JLabel label5;
    private JTextField userName_textField2;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
