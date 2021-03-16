package docket;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import da.AgentMySQLAccess;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class DocketFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton createButton = new JButton("Create");
	private JButton deleteButton = new JButton("Delete");
	private JButton closeButton = new JButton("Close");
	private JButton displayButton = new JButton("Display");
	private JTextField resultField;
	private JTextField docketId;
	private JTextField monthField;
	private JTextField docketName;
	private JTextField docketItems;
	private JTextField idField;
	private JButton updateButton = new JButton("Update");
	DocketMySQLAccess dAccess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocketFrame frame = new DocketFrame();
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
	public DocketFrame() {
		try {
			dAccess = new DocketMySQLAccess();
		} catch (Exception e) {

		}
		setTitle("Delivery Agents");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		monthField = new JTextField();

		JLabel lblNewLabel = new JLabel("Docket id");
		lblNewLabel.setBounds(10, 52, 120, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Docket Name");
		lblNewLabel_1.setBounds(33, 75, 97, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Items");
		lblNewLabel_2.setBounds(43, 98, 87, 13);
		contentPane.add(lblNewLabel_2);

		idField = new JTextField();
		idField.setBounds(349, 72, 96, 19);
		contentPane.add(idField);
		idField.setColumns(10);

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

		docketId = new JTextField();
		docketId.setBounds(140, 49, 96, 19);
		contentPane.add(docketId);
		docketId.setColumns(10);

		docketName = new JTextField();
		docketName.setBounds(140, 72, 96, 19);
		contentPane.add(docketName);
		docketName.setColumns(10);

		docketItems = new JTextField();
		docketItems.setBounds(140, 95, 96, 19);
		contentPane.add(docketItems);
		docketItems.setColumns(10);

		updateButton.setBounds(254, 94, 85, 21);
		contentPane.add(updateButton);
		displayButton.addActionListener(this);
		updateButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == displayButton) {
			DocketFrameDisplay newdisplay = DocketFrameDisplay.getInstance();
			newdisplay.setVisible(true);
		}
		if (target == createButton) {
			try {

				int dID = Integer.parseInt(docketId.getText());
				String dName = docketName.getText();
				int dNums = Integer.parseInt(docketItems.getText());
				Docket tempDocket = new Docket(dName, dNums);
				boolean insertResult = dAccess.insertDocket(tempDocket);
				if (insertResult == true)
					resultField.setText(" Details Saved");
				else
					resultField.setText("ERROR:  Details NOT Saved");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (target == deleteButton) {
			try {
				int id = Integer.parseInt(idField.getText());
				String allID = "" + id;
				boolean deleteResult = dAccess.deleteDocketById(id);
				if ((deleteResult == true) && (allID.equals("-99")))
					resultField.setText("Table Emptied");
				else if (deleteResult == true)
					resultField.setText("Docket Deleted");
				else
					resultField.setText("ERROR:  Details NOT Deleted or Do Not Exist");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (target == updateButton) {
			try {
				int id = Integer.parseInt(idField.getText());
				int date1 = Integer.parseInt(docketId.getText());
				String name = docketName.getText();
				int items = Integer.parseInt(docketItems.getText());
				boolean updateResult = dAccess.updateBill(id, new Docket(name, items));
				if (updateResult == true)
					resultField.setText(" Details Updated");
				else
					resultField.setText("Details NOT Updated or do not exist");
			} catch (Exception e1) {
				// e1.printStackTrace();
				resultField.setText("Invalid Inputs");
			}
		}
		if (target == closeButton) {
			this.dispose();
		}
	}
}
