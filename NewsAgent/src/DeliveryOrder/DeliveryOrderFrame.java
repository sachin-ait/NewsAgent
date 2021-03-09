
package DeliveryOrder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import OrderReport.OrderReport;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class DeliveryOrderFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField publicationField;
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
	public DeliveryOrderFrame() {
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

		publicationField = new JTextField();
		publicationField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		publicationField.setBounds(129, 80, 100, 20);
		contentPane.add(publicationField);
		publicationField.setColumns(10);

		lblCust2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust2.setBounds(10, 80, 120, 20);
		contentPane.add(lblCust2);

		dateField = new JTextField();
		dateField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		dateField.setBounds(129, 110, 100, 20);
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
				String doName = nameField.getText();
				String doPublication = publicationField.getText();
				String doDate = dateField.getText();

				DeliveryOrder DoObj = new DeliveryOrder(doName, doPublication, doDate);

				// Insert DeliveryOrder Details into the database
				boolean insertResult = dao.insertDeliveryOrderDetailsAccount(DoObj);
				if (insertResult == true) {

					resultField.setText("DeliveryOrder Details Saved");
					System.out.println("DeliveryOrder Details Saved");
				} else {
					resultField.setText("ERROR: DeliveryOrder Details NOT Saved");
					System.out.println("ERROR: DeliveryOrder Details NOT Saved");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (target == btnDelete) {
			try {
				int deleteDoId = Integer.parseInt(idField.getText());

				boolean deleteResult = dao.deleteDeliveryOrderById(deleteDoId);
				if ((deleteResult == true) && (deleteDoId == -99)) {
					resultField.setText("DeliveryOrder Table Emptied");
					System.out.println("DeliveryOrder Table Emptied");
				}

				else if (deleteResult == true) {
					resultField.setText("DeliveryOrder Deleted");
					System.out.println("DeliveryOrder Deleted");
				}

			} catch (Exception ex) {
				resultField.setText("ERROR: DeliveryOrder Details NOT Deleted or Do Not Exist");
				System.out.println("ERROR: DeliveryOrder Details NOT Deleted or Do Not Exist");
			}

		}
		if (target == btnUpdate) {
			try {
				int doId = Integer.parseInt(idField.getText());
				String doName = nameField.getText();
				String doPublication = publicationField.getText();
				String doDate = dateField.getText();


				boolean updateResult = dao.updateDeliveryOrderById(doId, doName, doPublication, doDate);
				if (updateResult == true) {
					resultField.setText("DeliveryOrder Updated");
					System.out.println("DeliveryOrder Updated");
				} else {
					resultField.setText("ERROR: DeliveryOrder Details NOT Updated or Do Not Exist");
					System.out.println("ERROR: DeliveryOrder Details NOT Updated or Do Not Exist");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		if (target == btnClose) {
			this.dispose();
		}
		if (target == btnDeleteReport) {
			OrderReport.readDeletedReport();
		}
		if (target == btnUpdateReport) {
			OrderReport.readUpdatedReport();
		}
	}

}
