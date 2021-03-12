package base;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIbase extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnNewButton_1 = new JButton("Close");
	DefaultTableModel tablMod = new DefaultTableModel();
	String[] menuChoices = { "Login users", "Delivery Agents", "Billing", "Customer", "Publications", "Dockets",
			"Orders", "Invoices" };
	JComboBox comboBox = new JComboBox(menuChoices);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIbase frame = new GUIbase();
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
	public GUIbase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNewButton_1.setBounds(86, 85, 85, 21);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);

		String[] menuChoices = { "Login users", "Delivery Agents", "Billing", "Customer", "Publications", "Dockets",
				"Orders", "Invoices" };
		JComboBox comboBox = new JComboBox(menuChoices);
		comboBox.setBounds(74, 40, 120, 21);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectModule = (String) combo.getSelectedItem();

				if (selectModule.equals("Login users")) {
					login.CommandLine.main(null);
				} 
				else if (selectModule.equals("Delivery Agents")) {
					da.DaFrame.main(null);
				}
				else if (selectModule.equals("Billing")) {
					biling.CommandLine.main(null);
				}
				else if (selectModule.equals("Customer")) {
					Customer.CustomerFrame.main(null);
				}
				else if (selectModule.equals("Publications")) {
					publications.PublicationController.main(null);
				}
				else if (selectModule.equals("Dockets")) {
					docket.CommandLine.main(null);
				}
				else if (selectModule.equals("Orders")) {
					DeliveryOrder.DeliveryOrderCommandLine.main(null);
				}
				else if (selectModule.equals("Invoices")) {
					di.DiFrame.main(null);
				}
			}
		});
	}


	public void end() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		setVisible(false);
		dispose();
		System.exit(0);
	}

	public void actionPerformed(ActionEvent e) {
		Object target = e.getSource();

		if (target == btnNewButton_1) {
			end();
		}
	}
}
