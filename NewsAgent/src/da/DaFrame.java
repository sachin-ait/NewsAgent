package da;

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

public class DaFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField areaField;
	private JTextField payField;
	private JTextField hourField;
	private JTextField idField;
	private JButton createButton = new JButton("Create");
	private JButton deleteButton = new JButton("Delete");
	private JButton closeButton = new JButton("Close");
	private JButton displayButton = new JButton("Display");
	private JTextField resultField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DaFrame frame = new DaFrame();
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
	public DaFrame() {
		setTitle("Delivery Agents");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameField = new JTextField();
		nameField.setBounds(90, 49, 96, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Agent Name:");
		lblNewLabel.setBounds(10, 52, 70, 13);
		contentPane.add(lblNewLabel);

		areaField = new JTextField();
		areaField.setBounds(90, 78, 96, 19);
		contentPane.add(areaField);
		areaField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Agent Area:");
		lblNewLabel_1.setBounds(10, 81, 63, 13);
		contentPane.add(lblNewLabel_1);

		payField = new JTextField();
		payField.setBounds(90, 107, 96, 19);
		contentPane.add(payField);
		payField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Pay Rate:");
		lblNewLabel_2.setBounds(20, 110, 63, 13);
		contentPane.add(lblNewLabel_2);

		hourField = new JTextField();
		hourField.setBounds(90, 136, 96, 19);
		contentPane.add(hourField);
		hourField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Hours Logged:");
		lblNewLabel_3.setBounds(10, 139, 70, 13);
		contentPane.add(lblNewLabel_3);

		idField = new JTextField();
		idField.setBounds(298, 78, 96, 19);
		contentPane.add(idField);
		idField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Agent ID:");
		lblNewLabel_4.setBounds(242, 81, 46, 13);
		contentPane.add(lblNewLabel_4);

		createButton.setBounds(53, 179, 85, 21);
		contentPane.add(createButton);
		createButton.addActionListener(this);

		deleteButton.setBounds(308, 106, 85, 21);
		contentPane.add(deleteButton);
		deleteButton.addActionListener(this);

		closeButton.setBounds(165, 265, 85, 21);
		contentPane.add(closeButton);
		closeButton.addActionListener(this);

		displayButton.setBounds(309, 179, 85, 21);
		contentPane.add(displayButton);

		resultField = new JTextField();
		resultField.setBounds(63, 219, 303, 19);
		contentPane.add(resultField);
		resultField.setColumns(10);
		displayButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();
		if (target == displayButton) {
			DaFrameDisplay newdisplay = DaFrameDisplay.getInstance();
			newdisplay.setVisible(true);
		}
		if (target == createButton) {
			try {
				MySQLAccess dao = new MySQLAccess();
				String nme = nameField.getText();
				String area = areaField.getText();
				int payrate = Integer.parseInt(payField.getText());
				int hours = Integer.parseInt(hourField.getText());
				DA DAObj = new DA(nme, area, payrate, hours);
				boolean insertResult = dao.insertDADetailsAccount(DAObj);
				if (insertResult == true)
					resultField.setText("Agent Details Saved");
				else
					resultField.setText("ERROR: Agent Details NOT Saved");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (target == deleteButton) {
				try {
					MySQLAccess dao = new MySQLAccess();
					int id = Integer.parseInt(idField.getText());
					String allID = "" + id;
					boolean deleteResult = dao.deleteDAById(id);
					if ((deleteResult == true) && (allID.equals("-99")))
						resultField.setText("Agent Table Emptied");
					else if (deleteResult == true)
						resultField.setText("Agent Deleted");
					else
						resultField.setText("ERROR: Agent Details NOT Deleted or Do Not Exist");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
		if (target == closeButton) {
			this.dispose();
			}
			}
		}
