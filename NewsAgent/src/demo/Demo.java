package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.DataModel;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Demo {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldAge;
	private JTable jtable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo window = new Demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Demo() {
		initialize();
		// init_table();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1051, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(35, 86, 70, 15);
		frame.getContentPane().add(lblName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(35, 150, 70, 15);
		frame.getContentPane().add(lblAge);

		textFieldName = new JTextField();
		textFieldName.setBounds(177, 69, 114, 19);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);

		textFieldAge = new JTextField();
		textFieldAge.setBounds(177, 148, 114, 19);
		frame.getContentPane().add(textFieldAge);
		textFieldAge.setColumns(10);

		jtable = new JTable();

		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jtable.setFillsViewportHeight(true);
		jtable.setBounds(537, 71, 230, 115);
		init_table();
		JScrollPane jScrollPane = new JScrollPane(jtable);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(jtable);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds(73, 255, 117, 25);
		frame.getContentPane().add(btnAdd);

	}

	private void init_table() {
		Statement stmt = MysqlJdbc.getConnectionStatement();

		String[] columns = { "Name ", "Age" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		model.setNumRows(3);
		jtable.setModel(model);
	}

}
