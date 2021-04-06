
package DeliveryOrder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import OrderReport.OrderReportFrame;
import base.MysqlJDBC;
import publications.JDBCUtils;
import publications.Publication;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class DeliveryOrderFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dateField;
	private JTextField idField;
	private JButton btnCreate = new JButton("Create");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnClose = new JButton("Close");
	private JButton btnDisplay = new JButton("Display");
	private JTextField resultField;
	static DeliveryOrderMySQLAccess dao;
	private JLabel lblDeliveryOrder = new JLabel("Delivery Order");
	private JButton btnUpdate = new JButton("Update");
	private JLabel lblCust5 = new JLabel("DeliveryOrder Id");
	private JLabel lblCust1 = new JLabel("Name");
	private JLabel lblCust2 = new JLabel("Publication");
	private final JButton btnUpdateReport = new JButton("Update Report");
	private final JButton btnDeleteReport = new JButton("Delete Report");
	private JComboBox pubcomboBox = new JComboBox();
	private JTextField addressField;
	private String pubChoice;
	private String monthChoice;
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
	private JComboBox comboMonthBox = new JComboBox(months);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dao = new DeliveryOrderMySQLAccess();
					dao.getConnectionStatement();

					DeliveryOrderFrame frame = new DeliveryOrderFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeliveryOrderFrame() throws SQLException {
		Connection connection = MysqlJDBC.getConnection();
		Statement statement = connection.createStatement();
		String querySql = "SELECT name FROM NewsAgent.publication";
		ResultSet rs = statement.executeQuery(querySql);
		ArrayList<String> pubnames = new ArrayList<>();
		while (rs.next()) {
			String name = rs.getString("publicationName");
			pubcomboBox.addItem(name);
		}
		connection.close();
		setTitle("DeliveryOrder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameField = new JTextField();
		nameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		nameField.setBounds(129, 50, 100, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		lblCust1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust1.setBounds(10, 50, 120, 20);
		contentPane.add(lblCust1);

		lblCust2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust2.setBounds(10, 80, 120, 20);
		contentPane.add(lblCust2);

		dateField = new JTextField();
		dateField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		dateField.setBounds(357, 314, 100, 20);
		contentPane.add(dateField);
		dateField.setColumns(10);

		JLabel lblCust3 = new JLabel("Date");
		lblCust3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust3.setBounds(10, 110, 120, 20);
		contentPane.add(lblCust3);

		idField = new JTextField();
		idField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		idField.setBounds(377, 50, 100, 20);
		contentPane.add(idField);
		idField.setColumns(10);
 
		lblCust5.setHorizontalAlignment(SwingConstants.LEFT);
		lblCust5.setFont(new Font("Times New Roman", Font.PLAIN, 18)); 
		lblCust5.setBounds(241, 50, 135, 20);
		contentPane.add(lblCust5);

		resultField = new JTextField();
		resultField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		resultField.setBounds(57, 242, 400, 30);
		contentPane.add(resultField);
		resultField.setColumns(10);
		btnCreate.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		btnCreate.setBounds(57, 199, 90, 30);
		contentPane.add(btnCreate);
		btnCreate.addActionListener(this);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		btnDelete.setBounds(261, 199, 90, 30);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		btnClose.setBounds(203, 285, 90, 30);
		contentPane.add(btnClose);
		btnClose.addActionListener(this);
		btnDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		btnDisplay.setBounds(367, 199, 90, 30);
		contentPane.add(btnDisplay);

		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate.setBounds(159, 199, 90, 30);
		contentPane.add(btnUpdate); 

		lblDeliveryOrder.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		lblDeliveryOrder.setBounds(176, 13, 160, 30);
		contentPane.add(lblDeliveryOrder);
		btnUpdateReport.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdateReport.setBounds(307, 83, 150, 30);
		
		contentPane.add(btnUpdateReport);
		btnDeleteReport.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDeleteReport.setBounds(307, 126, 150, 30);
	
		contentPane.add(btnDeleteReport);
		pubcomboBox.setBounds(129, 80, 100, 21);
		contentPane.add(pubcomboBox);
		
		addressField = new JTextField();
		addressField.setEditable(false);
		addressField.setBounds(97, 147, 196, 19);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 150, 77, 13);
		contentPane.add(lblNewLabel);
		
		comboMonthBox.setBounds(129, 110, 100, 21);
		contentPane.add(comboMonthBox);
		btnDisplay.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDeleteReport.addActionListener(this);
		btnUpdateReport.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == btnDisplay) {
			DeliveryOrderFrameDisplay newdisplay = DeliveryOrderFrameDisplay.getInstance();
			newdisplay.setVisible(true);
		}
		if (target == btnCreate) {
			try {
				PreparedStatement preparedStatement = null;
				String doName = nameField.getText();
				String doPublication = pubcomboBox.getSelectedItem() + "";
				//String doDate = dateField.getText();
				String doDate = (String) comboMonthBox.getSelectedItem();
				String doAddress = null;
				Connection connect = MysqlJDBC.getConnection();
				preparedStatement = connect.prepareStatement("SELECT Address FROM NewsAgent.Customer where Name = ?");
				preparedStatement.setString(1, doName);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					doAddress += rs.getString("Address");
				}
				connect.close();
				DeliveryOrder DoObj = new DeliveryOrder(doName, doAddress, doPublication, doDate);

				// Insert DeliveryOrder Details into the database
				boolean insertResult = dao.insertDeliveryOrderDetailsAccount(DoObj);
				if (insertResult == true) {

					resultField.setText("DeliveryOrder Details Saved");
					System.out.println("DeliveryOrder Details Saved");
				} else {
					resultField.setText("ERROR: DeliveryOrder Details NOT Saved");
					System.out.println("ERROR: DeliveryOrder Details NOT Saved");
				}
			} catch (DeliveryOrderExceptionHandler | SQLException e1) {
				resultField.setText(e1.getMessage());
			}
		}
		if (target == btnDelete) {
			try {
				int deleteDoId = Integer.parseInt(idField.getText());

				boolean deleteResult = dao.deleteDeliveryOrderById(deleteDoId);
				if ((deleteResult == true) && (deleteDoId == -99)) {
					resultField.setText("DeliveryOrder Table Emptied");

				}

				else if (deleteResult == true) {
					resultField.setText("DeliveryOrder Deleted");

				}

			} catch (Exception ex) {
				resultField.setText("ERROR: DeliveryOrder Details NOT Deleted or Do Not Exist");

			}

		}
		if (target == btnUpdate) {
			try {
				PreparedStatement preparedStatement = null;
				int doId = Integer.parseInt(idField.getText());
				String doName = nameField.getText();
				String doPublication = pubcomboBox.getSelectedItem() + "";
				//String doDate = dateField.getText();
				String doDate = (String) comboMonthBox.getSelectedItem();
				String doAddress = null;
				Connection connect = MysqlJDBC.getConnection();
				preparedStatement = connect.prepareStatement("SELECT Address FROM NewsAgent.Customer where Name = ?");
				preparedStatement.setString(1, doName);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					doAddress += rs.getString("publicationName");
				}
				connect.close();
				boolean updateResult = dao.updateDeliveryOrderById(doId, doName, doAddress, doPublication, doDate);
				if (updateResult == true) {
					resultField.setText("DeliveryOrder Updated");
					System.out.println("DeliveryOrder Updated");
				} else {
					resultField.setText("ERROR: DeliveryOrder Details NOT Updated or Do Not Exist");
					System.out.println("ERROR: DeliveryOrder Details NOT Updated or Do Not Exist");
				}
			} catch (Exception ex) {
				resultField.setText("Invalid Input");
			}

		}
		if (target == btnClose) {
			this.dispose();
		}
		if (target == btnUpdateReport) {
			OrderReportFrame OrderReportDisplay = OrderReportFrame.getInstance(1);
			OrderReportDisplay.setVisible(true);
		}
		if (target == btnDeleteReport) {
			OrderReportFrame OrderReportDisplay = OrderReportFrame.getInstance(2);
			OrderReportDisplay.setVisible(true);
		}
	}
}
