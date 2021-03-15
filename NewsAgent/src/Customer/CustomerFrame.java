
package Customer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CustomerFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField paymentField;
	private JTextField idField;
	private JButton btnCreate = new JButton("Create");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnClose = new JButton("Close");
	private JButton btnDisplay = new JButton("Display");
	private JTextField resultField;
	private JTextField areaField;
	static CustomerMySQLAccess dao;
	private JLabel lblCustomer = new JLabel("Customer");
	private JButton btnUpdate = new JButton("Update");
	private JLabel lblCust6 = new JLabel("Area");
	private JLabel lblCust5 = new JLabel("Customer Id");
	private JLabel lblCust4 = new JLabel("Payment");
	private JLabel lblCust1 = new JLabel("Name");
	private JLabel lblCust2 = new JLabel("Address");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dao = new CustomerMySQLAccess();
					dao.getConnectionStatement();

					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame() {
		setTitle("Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 437);
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

		addressField = new JTextField();
		addressField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addressField.setBounds(129, 80, 100, 20);
		contentPane.add(addressField);
		addressField.setColumns(10);

		lblCust2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust2.setBounds(10, 80, 120, 20);
		contentPane.add(lblCust2);

		phoneField = new JTextField();
		phoneField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		phoneField.setBounds(129, 110, 100, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);

		JLabel lblCust3 = new JLabel("Phone Number");
		lblCust3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust3.setBounds(10, 110, 120, 20);
		contentPane.add(lblCust3);

		paymentField = new JTextField();
		paymentField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		paymentField.setBounds(129, 140, 100, 20);
		contentPane.add(paymentField);
		paymentField.setColumns(10);

		lblCust4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust4.setBounds(10, 140, 120, 20);
		contentPane.add(lblCust4);

		idField = new JTextField();
		idField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		idField.setBounds(364, 50, 100, 20);
		contentPane.add(idField);
		idField.setColumns(10);

		lblCust5.setHorizontalAlignment(SwingConstants.LEFT);
		lblCust5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust5.setBounds(241, 50, 120, 20);
		contentPane.add(lblCust5);

		resultField = new JTextField();
		resultField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		resultField.setBounds(44, 267, 400, 30);
		contentPane.add(resultField);
		resultField.setColumns(10);

		lblCust6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCust6.setBounds(10, 170, 120, 20);
		contentPane.add(lblCust6);

		areaField = new JTextField();
		areaField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		areaField.setColumns(10);
		areaField.setBounds(129, 170, 100, 20);
		contentPane.add(areaField);
		btnCreate.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		btnCreate.setBounds(57, 224, 90, 30);
		contentPane.add(btnCreate);
		btnCreate.addActionListener(this);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		btnDelete.setBounds(255, 224, 90, 30);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		btnClose.setBounds(202, 310, 90, 30);
		contentPane.add(btnClose);
		btnClose.addActionListener(this);
		btnDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		btnDisplay.setBounds(354, 224, 90, 30);
		contentPane.add(btnDisplay);

		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate.setBounds(158, 224, 90, 30);
		contentPane.add(btnUpdate);

		lblCustomer.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		lblCustomer.setBounds(192, 17, 100, 20);
		contentPane.add(lblCustomer);
		btnDisplay.addActionListener(this);
		btnUpdate.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == btnDisplay) {
			CustomerFrameDisplay newdisplay = CustomerFrameDisplay.getInstance();
			newdisplay.setVisible(true);
		}
		if (target == btnCreate) {
			try {
				String custName = nameField.getText();
				String custAddr = addressField.getText();
				String custphoneNumber = phoneField.getText();
				double custPayment = Double.parseDouble(paymentField.getText());
				String custArea = areaField.getText();
				Customer custObj = new Customer(custName, custAddr, custphoneNumber, custPayment, custArea);

				// Insert Customer Details into the database
				boolean insertResult = dao.insertCustomerDetailsAccount(custObj);
				if (insertResult == true) {

					resultField.setText("Customer Details Saved");
					System.out.println("Customer Details Saved");
				} else {
					resultField.setText("ERROR: Customer Details NOT Saved");
					System.out.println("ERROR: Customer Details NOT Saved");
				}
			} catch (CustomerExceptionHandler e1) {
				resultField.setText(e1.getMessage());
			}catch(NumberFormatException e2) {
				resultField.setText("Fields are Empty");
			}
		}
		if (target == btnDelete) { 
			try {
				int deleteCustId = Integer.parseInt(idField.getText());

				boolean deleteResult = dao.deleteCustomerById(deleteCustId);
				if ((deleteResult == true) && (deleteCustId == -99)) {
					resultField.setText("Customer Table Emptied");
					System.out.println("Customer Table Emptied");
				}

				else if (deleteResult == true) {
					resultField.setText("Customer Deleted");
					System.out.println("Customer Deleted");
				}

			} catch (Exception e2) {
				resultField.setText("Customer Id Input Empty");
			}

		}
		if (target == btnUpdate) {
			try {
				int custId = Integer.parseInt(idField.getText());
				String custName = nameField.getText();
				String custAddr = addressField.getText();
				String custphoneNumber = phoneField.getText();
				double custPayment = Double.parseDouble(paymentField.getText());
				String custArea = areaField.getText();

				boolean updateResult = dao.updateCustomerById(custId, custName, custAddr, custphoneNumber, custPayment,
						custArea);
				if (updateResult == true) {
					resultField.setText("Customer Updated");
					System.out.println("Customer Updated");
				} else {
					resultField.setText("ERROR: Customer Details NOT Updated or Do Not Exist");
					System.out.println("ERROR: Customer Details NOT Updated or Do Not Exist");
				}
			} catch (Exception e3) {
				resultField.setText("Invalid Input");
			}

		}
		if (target == btnClose) {
			this.dispose();
		}

	}

}
