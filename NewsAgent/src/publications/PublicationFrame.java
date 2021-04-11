package publications;

import di.DiFrameDisplay;
import di.InvoiceMySQLAccess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import da.AgentMySQLAccess;

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
	private JTextField monthField;
	private JTextField nameField;
	private JTextField stockField;
	private JTextField priceField;
	private JTextField idField;
	private JButton updateButton= new JButton("Update");
	private String months[] = new String[] {
			"Daily",
			"Weekly",
			"Monthly"
	};
	private JComboBox freqcomboBox = new JComboBox(months);

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
	 * @throws PubExceptionHandler 
	 */
	public PublicationFrame() throws PubExceptionHandler {
		PubMySQLAccess dao = new PubMySQLAccess();
		setTitle("Publication Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		monthField = new JTextField();

		JLabel lblNewLabel = new JLabel("Publication ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 52, 120, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Publication Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(20, 75, 110, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Publication Stock:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 98, 120, 13);
		contentPane.add(lblNewLabel_2);

		idField = new JTextField();
		idField.setBounds(140, 49, 96, 19);
		contentPane.add(idField);
		idField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Publication Price:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(10, 121, 120, 13);
		contentPane.add(lblNewLabel_4);

		createButton.setBounds(254, 94, 85, 21);
		contentPane.add(createButton);
		createButton.addActionListener(this);

		deleteButton.setBounds(254, 48, 85, 21);
		contentPane.add(deleteButton);
		deleteButton.addActionListener(this);

		closeButton.setBounds(165, 265, 85, 21);
		contentPane.add(closeButton);
		closeButton.addActionListener(this);

		displayButton.setBounds(165, 188, 85, 21);
		contentPane.add(displayButton);

		resultField = new JTextField();
		resultField.setBounds(63, 219, 303, 19);
		contentPane.add(resultField);
		resultField.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Publication Frequency:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(10, 144, 120, 13);
		contentPane.add(lblNewLabel_5);

		nameField = new JTextField();
		nameField.setBounds(140, 72, 96, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);

		stockField = new JTextField();
		stockField.setBounds(140, 95, 96, 19);
		contentPane.add(stockField);
		stockField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(140, 118, 96, 19);
		contentPane.add(priceField);
		priceField.setColumns(10);
		
		updateButton.setBounds(254, 71, 85, 21);
		contentPane.add(updateButton);
		
		freqcomboBox.setBounds(140, 140, 96, 21);
		contentPane.add(freqcomboBox);
		displayButton.addActionListener(this);
		updateButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == displayButton) {
			PublicationFrameDisplay newdisplay = PublicationFrameDisplay.getInstance();
			newdisplay.setVisible(true);
		}
		if (target == createButton) {
			try {
				//InvoiceMySQLAccess dio = new InvoiceMySQLAccess();
				//int pid = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				int stock = Integer.parseInt(stockField.getText());
				double price = Double.parseDouble(priceField.getText());
				String frequency = (String) freqcomboBox.getSelectedItem();
				Publication DIObj = new Publication(name, stock, price, frequency);
				boolean insertResult = PubMySQLAccess.insertPub(DIObj);
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
				boolean deleteResult = PubMySQLAccess.deletePubById(id);
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
				//InvoiceMySQLAccess dio = new InvoiceMySQLAccess();
				int id = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				int amount = Integer.parseInt(stockField.getText());
				double price = Integer.parseInt(priceField.getText());
				String frequency = (String) freqcomboBox.getSelectedItem();
				boolean updateResult = PubMySQLAccess.updatePubById(id, name, amount, price, frequency);
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
