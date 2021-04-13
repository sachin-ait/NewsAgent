/*
 * Created by JFormDesigner on Mon Mar 08 01:15:16 GMT 2021
 */

package login;

import com.mysql.cj.util.StringUtils;

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
        if (aBoolean) {
            label4.setText("delete user successful");
        } else {
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
        if (update) {
            label4.setText("update user successful");
        } else {
            label4.setText("update user failure");
        }
        ResultSet rSet = LoginController.retrieveAllUserAccounts();
        TableModel.refreshFromDB(rSet);
        System.out.println();
    }

    private void button4ActionPerformed(ActionEvent e) {
        // TODO add your code here
        ResultSet rSet = null;
        String id = ID_textField1.getText();
        if (StringUtils.isNullOrEmpty(id)) {
            rSet = LoginController.retrieveAllUserAccounts();
        } else {
            rSet = LoginController.retrieveAllUserById(id);
        }

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
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable(TableModel);
        panel2 = new JPanel();
        label2 = new JLabel();
        ID_textField1 = new JTextField();
        label3 = new JLabel();
        userName_textField2 = new JTextField();
        label5 = new JLabel();
        passwordField1 = new JPasswordField();
        label4 = new JLabel();
        button1 = new JButton();
        button3 = new JButton();
        button2 = new JButton();
        button4 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("User");
        label1.setFont(new Font("Lucida Grande", Font.BOLD, 20));

        //======== panel1 ========
        {

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(14, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

            //---- label4 ----
            label4.setText("text");
            label4.setForeground(Color.red);

            //---- button1 ----
            button1.setText("Create");
            button1.addAncestorListener(new AncestorListener() {
                @Override
                public void ancestorAdded(AncestorEvent e) {
                    button1AncestorAdded(e);
                }

                @Override
                public void ancestorMoved(AncestorEvent e) {
                }

                @Override
                public void ancestorRemoved(AncestorEvent e) {
                }
            });
            button1.addActionListener(e -> button1ActionPerformed(e));

            //---- button3 ----
            button3.setText("Update");
            button3.addActionListener(e -> button3ActionPerformed(e));

            //---- button2 ----
            button2.setText("Delete by ID");
            button2.addActionListener(e -> button2ActionPerformed(e));

            //---- button4 ----
            button4.setText("View");
            button4.addActionListener(e -> button4ActionPerformed(e));

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addGroup(panel2Layout.createParallelGroup()
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(33, 33, 33)
                                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(button4, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(panel2Layout.createSequentialGroup()
                                                            .addGroup(panel2Layout.createParallelGroup()
                                                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                            .addComponent(label2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                                                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                                                            .addGap(27, 27, 27)
                                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(ID_textField1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(userName_textField2, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))))
                                    .addContainerGap(22, Short.MAX_VALUE))
            );
            panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(ID_textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label2))
                                    .addGap(9, 9, 9)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(userName_textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label3))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label5))
                                    .addGap(23, 23, 23)
                                    .addComponent(label4)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(button2)
                                            .addComponent(button1))
                                    .addGap(18, 18, 18)
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(button4)
                                            .addComponent(button3))
                                    .addContainerGap(23, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 289, Short.MAX_VALUE))
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(43, Short.MAX_VALUE))
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
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel2;
    private JLabel label2;
    private JTextField ID_textField1;
    private JLabel label3;
    private JTextField userName_textField2;
    private JLabel label5;
    private JPasswordField passwordField1;
    private JLabel label4;
    private JButton button1;
    private JButton button3;
    private JButton button2;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
