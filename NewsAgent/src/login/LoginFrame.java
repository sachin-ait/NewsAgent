/*
 * Created by JFormDesigner on Mon Mar 08 00:10:38 GMT 2021
 */

package login;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author kmzjhpt pbev
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        initComponents();
        initComboBox1();
    }

    private void initComboBox1() {
        comboBox1.addItem("Customer");
        comboBox1.addItem("DeliveryAgent");
        comboBox1.addItem("DeliveryOrder");
        comboBox1.addItem("DeliveryInvoice");
        comboBox1.addItem("User");
        comboBox1.addItem("Docket");
        comboBox1.addItem("Publication");
        comboBox1.addItem("Report");
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String userName = userName_textField.getText();
        char[] passwordChar = passwordField1.getPassword();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < passwordChar.length; i++) {
            stringBuffer.append(passwordChar[i]);
        }
        String password = stringBuffer.toString();
        String accessModel = comboBox1.getSelectedItem() + "";
        try {
            LoginController.login(userName, password, accessModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        userName_textField = new JTextField();
        label2 = new JLabel();
        passwordField1 = new JPasswordField();
        label3 = new JLabel();
        comboBox1 = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();
        label4 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label1 ----
                label1.setText("Newsagent Manager System");
                label1.setFont(new Font("Lucida Grande", Font.BOLD, 22));

                //---- userName_textField ----
                userName_textField.setFont(new Font("Lucida Grande", Font.BOLD, 14));

                //---- label2 ----
                label2.setText("Password:");
                label2.setFont(new Font("Lucida Grande", Font.BOLD, 14));

                //---- passwordField1 ----
                passwordField1.setFont(new Font("Lucida Grande", Font.BOLD, 14));

                //---- label3 ----
                label3.setText("Model:");
                label3.setFont(new Font("Lucida Grande", Font.BOLD, 14));

                //---- comboBox1 ----
                comboBox1.setFont(new Font("Lucida Grande", Font.BOLD, 14));

                //---- button1 ----
                button1.setText("Login");
                button1.setFont(new Font("Lucida Grande", Font.BOLD, 18));
                button1.addActionListener(e -> button1ActionPerformed(e));

                //---- button2 ----
                button2.setText("Close");
                button2.setFont(new Font("Lucida Grande", Font.BOLD, 18));

                //---- label4 ----
                label4.setText("UserName:");
                label4.setFont(new Font("Lucida Grande", Font.BOLD, 14));

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(94, 94, 94)
                                    .addComponent(label1))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(71, 71, 71)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)
                                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(userName_textField)
                                                .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(114, 114, 114)
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85, 85, 85)
                                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(71, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(userName_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4))
                            .addGap(18, 18, 18)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3))
                            .addGap(32, 32, 32)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button1)
                                .addComponent(button2))
                            .addContainerGap(164, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField userName_textField;
    private JLabel label2;
    private JPasswordField passwordField1;
    private JLabel label3;
    private JComboBox comboBox1;
    private JButton button1;
    private JButton button2;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
