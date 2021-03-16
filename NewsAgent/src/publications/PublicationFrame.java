package publications;

import di.DI;
import di.DiFrameDisplay;
import di.InvoiceMySQLAccess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PublicationFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton createButton = new JButton("Create");
	private JButton deleteButton = new JButton("Delete");
	private JButton closeButton = new JButton("Close");
	private JButton displayButton = new JButton("Display");
	private JTextField resultField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField successField;
	private JTextField failedField;
	private JTextField payField;
	private JTextField idField;
	private JButton updateButton= new JButton("Update");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PublicationFrame frame = new PublicationFrame();
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
	public PublicationFrame() {
		setTitle("Publication Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		monthField = new JTextField();

		JLabel lblNewLabel = new JLabel(" date of day:");
		lblNewLabel.setBounds(10, 52, 120, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(" month:");
		lblNewLabel_1.setBounds(33, 75, 97, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(" year:");
		lblNewLabel_2.setBounds(43, 98, 87, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(" ID:");
		lblNewLabel_3.setBounds(246, 75, 93, 13);
		contentPane.add(lblNewLabel_3);

		idField = new JTextField();
		idField.setBounds(349, 72, 96, 19);
		contentPane.add(idField);
		idField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Success:");
		lblNewLabel_4.setBounds(63, 121, 67, 13);
		contentPane.add(lblNewLabel_4);

		createButton.setBounds(55, 188, 85, 21);
		contentPane.add(createButton);
		createButton.addActionListener(this);

		deleteButton.setBounds(359, 94, 85, 21);
		contentPane.add(deleteButton);
		deleteButton.addActionListener(this);

		closeButton.setBounds(165, 265, 85, 21);
		contentPane.add(closeButton);
		closeButton.addActionListener(this);

		displayButton.setBounds(309, 188, 85, 21);
		contentPane.add(displayButton);

		resultField = new JTextField();
		resultField.setBounds(63, 219, 303, 19);
		contentPane.add(resultField);
		resultField.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Failed:");
		lblNewLabel_5.setBounds(73, 144, 57, 13);
		contentPane.add(lblNewLabel_5);

		dayField = new JTextField();
		dayField.setBounds(140, 49, 96, 19);
		contentPane.add(dayField);
		dayField.setColumns(10);

		monthField = new JTextField();
		monthField.setBounds(140, 72, 96, 19);
		contentPane.add(monthField);
		monthField.setColumns(10);

		yearField = new JTextField();
		yearField.setBounds(140, 95, 96, 19);
		contentPane.add(yearField);
		yearField.setColumns(10);

		successField = new JTextField();
		successField.setBounds(140, 118, 96, 19);
		contentPane.add(successField);
		successField.setColumns(10);

		failedField = new JTextField();
		failedField.setBounds(140, 141, 96, 19);
		contentPane.add(failedField);
		failedField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Paydue:");
		lblNewLabel_6.setBounds(65, 167, 65, 13);
		contentPane.add(lblNewLabel_6);

		payField = new JTextField();
		payField.setBounds(140, 164, 96, 19);
		contentPane.add(payField);
		payField.setColumns(10);
		
		updateButton.setBounds(254, 94, 85, 21);
		contentPane.add(updateButton);
		displayButton.addActionListener(this);
		updateButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == displayButton) {
			DiFrameDisplay newdisplay = DiFrameDisplay.getInstance();
			newdisplay.setVisible(true);
		}
		if (target == createButton) {
			try {
				InvoiceMySQLAccess dio = new InvoiceMySQLAccess();
				int date1 = Integer.parseInt(dayField.getText());
				String date2 = monthField.getText();
				int date3 = Integer.parseInt(yearField.getText());
				int aSucc = Integer.parseInt(successField.getText());
				int afail = Integer.parseInt(failedField.getText());
				double paydue = Integer.parseInt(payField.getText());
				DI DIObj = new DI(date1, date2, date3, aSucc, afail, paydue);
				boolean insertResult = dio.insertDInvoiceDetails(DIObj);
				if (insertResult == true)
					resultField.setText("Invoice Details Saved");
				else
					resultField.setText("ERROR: Invoice Details NOT Saved");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (target == deleteButton) {
			try {
				InvoiceMySQLAccess dio = new InvoiceMySQLAccess();
				int id = Integer.parseInt(idField.getText());
				String allID = "" + id;
				boolean deleteResult = dio.deleteDIById(id);
				if ((deleteResult == true) && (allID.equals("-99")))
					resultField.setText("Invoice Table Emptied");
				else if (deleteResult == true)
					resultField.setText("Invoices Deleted");
				else
					resultField.setText("ERROR: Invoice Details NOT Deleted or Do Not Exist");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (target == updateButton) {
			try {
				InvoiceMySQLAccess dio = new InvoiceMySQLAccess();
				int id = Integer.parseInt(idField.getText());
				int date1 = Integer.parseInt(dayField.getText());
				String date2 = monthField.getText();
				int date3 = Integer.parseInt(yearField.getText());
				int aSucc = Integer.parseInt(successField.getText());
				int afail = Integer.parseInt(failedField.getText());
				double paydue = Integer.parseInt(payField.getText());
				boolean updateResult = dio.updateDIById(id, aSucc, afail, paydue);
				if (updateResult == true)
					resultField.setText("Agent Details Updated");
				else
					resultField.setText("ERROR: Agent Details NOT Updated or do not exist");
			} catch (Exception e1) {
				//e1.printStackTrace();
				resultField.setText("Invalid Inputs");
			}
	}
		if (target == closeButton) {
			this.dispose();
		}
	}
}
