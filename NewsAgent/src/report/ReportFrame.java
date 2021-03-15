/*
 * Created by JFormDesigner on Tue Mar 09 18:07:43 GMT 2021
 */

package report;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.util.Vector;

/**
 * @author kmzjhpt pbev
 */
public class ReportFrame extends JFrame {
    public ReportFrame() {
        initComponents();
    }


    public void renderData(){

        Vector<String> vName = new Vector<String>();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Delivery Roport");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(218, 332, Short.MAX_VALUE)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
                    .addGap(159, 159, 159))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(222, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(label1)
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
