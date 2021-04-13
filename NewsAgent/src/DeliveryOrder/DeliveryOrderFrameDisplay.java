package DeliveryOrder;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;

public class DeliveryOrderFrameDisplay extends JFrame implements ActionListener {
	private static DeliveryOrderFrameDisplay single;
	private static boolean created = false;
	private JPanel contentPane;
	private JTable table_1;
	private JButton closeButton;
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex)); 
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();

		if (target == closeButton) {
			this.dispose();
		}
	}

	private DeliveryOrderFrameDisplay(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 13, 515, 430);
		contentPane.add(panel);
		
		table_1 = new JTable();
		panel.add(table_1);
		
		closeButton = new JButton("Close");
		closeButton.setBounds(218, 465, 85, 21);
		contentPane.add(closeButton);
		closeButton.addActionListener(this);
		try {
			DeliveryOrderMySQLAccess dao = new DeliveryOrderMySQLAccess();
			dao.getConnectionStatement();
			ResultSet rSet = dao.retrieveAllDeliveryOrderAccounts();
			if (rSet == null) {
				System.out.println("No Records Found");
			}
			else {
				JTable table_1 = new JTable(buildTableModel(rSet));
				TableColumnModel columnModel = table_1.getColumnModel();
				columnModel.getColumn(0).setPreferredWidth(25);
				columnModel.getColumn(1).setPreferredWidth(25);
				columnModel.getColumn(2).setPreferredWidth(100);
				columnModel.getColumn(3).setPreferredWidth(100); 
				panel.add(table_1);
				rSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DeliveryOrderFrameDisplay getInstance() {
		if (!created) {
			single = new DeliveryOrderFrameDisplay();
		}
		return single;
	}
}