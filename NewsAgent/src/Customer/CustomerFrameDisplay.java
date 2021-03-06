package Customer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.ScrollPane;

public class CustomerFrameDisplay extends JFrame implements ActionListener {
	private static CustomerFrameDisplay single;
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

	private CustomerFrameDisplay(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 13, 515, 240);
		contentPane.add(panel);
			
		closeButton = new JButton("Close");
		closeButton.setBounds(225, 266, 85, 21);
		contentPane.add(closeButton);
		closeButton.addActionListener(this);
		try {
			CustomerMySQLAccess dao = new CustomerMySQLAccess();
			dao.getConnectionStatement();
			ResultSet rSet = dao.retrieveAllCustomerAccounts();
			if (rSet == null) {
				System.out.println("No Records Found");
			}
			else {
				table_1 = new JTable(buildTableModel(rSet));
				TableColumnModel columnModel = table_1.getColumnModel();
				columnModel.getColumn(0).setPreferredWidth(25);
				columnModel.getColumn(1).setPreferredWidth(100);
				columnModel.getColumn(2).setPreferredWidth(100);
				columnModel.getColumn(3).setPreferredWidth(100);
				columnModel.getColumn(4).setPreferredWidth(50);
				columnModel.getColumn(5).setPreferredWidth(100);
				rSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		panel.add(new JScrollPane(table_1));

	}
	
	public static CustomerFrameDisplay getInstance() {
		if (!created) {
			single = new CustomerFrameDisplay();
		}
		return single;
	}
}