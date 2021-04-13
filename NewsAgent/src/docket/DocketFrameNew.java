package docket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.mysql.cj.protocol.Resultset;

import da.AgentMySQLAccess;
import da.DAExceptionHandler;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

public class DocketFrameNew {

	private JFrame frame;
	JComboBox cmbBoxNames;
	Vector<String> da;
	JDatePickerImpl datePicker;
	HashMap<Integer, Integer> pub_amount = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocketFrameNew window = new DocketFrameNew();
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
	public DocketFrameNew() {
		initialize();
		init_comobox();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 803, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		UtilDateModel model = new UtilDateModel();
		// model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(523, 112, 250, 250);
		frame.getContentPane().add(datePicker);

		JLabel lblPleaseSelectYour = new JLabel("Please Select Your Name");
		lblPleaseSelectYour.setBounds(98, 39, 230, 27);
		frame.getContentPane().add(lblPleaseSelectYour);

		JButton btnPrintDocket = new JButton("Print Docket");
		btnPrintDocket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = da.get(cmbBoxNames.getSelectedIndex());
				String id = str.substring(str.indexOf(',') + 1);
				Date date = (Date) datePicker.getModel().getValue();
				if (date == null) {
					JOptionPane.showMessageDialog(frame, "Please select date", "", 0);
					return;
				}
				int day = date.getDay();
				int Tdate = date.getDate();
				print_docket(Integer.parseInt(id), day, Tdate);
			}
		});
		btnPrintDocket.setBounds(97, 153, 183, 27);
		frame.getContentPane().add(btnPrintDocket);
	}

	private void init_comobox() {
		try {
			AgentMySQLAccess dao = new AgentMySQLAccess();
			ResultSet rs = dao.retrieveAllDAAccounts();
			da = new Vector<>();
			while (rs.next()) {
				da.add(rs.getString("Agent_Name") + "," + rs.getString("Agent_ID"));
			}
			cmbBoxNames = new JComboBox();
			cmbBoxNames.setModel(new DefaultComboBoxModel(da));
			cmbBoxNames.setBounds(361, 40, 217, 24);
			frame.getContentPane().add(cmbBoxNames);

			JLabel lblSelectDate = new JLabel("Select Date");
			lblSelectDate.setBounds(359, 129, 119, 15);
			frame.getContentPane().add(lblSelectDate);

			JButton btnDocketDelivered = new JButton("Docket Delivered");
			btnDocketDelivered.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (pub_amount.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please select a Date, Or nothing to deliver today", "",
								0);
					} else {
						try {
							Statement sm = base.MysqlJDBC.getConnection().createStatement();
							for (Map.Entry m : pub_amount.entrySet()) {
								sm.execute("update publication set stock = stock -" + m.getValue() + " where id = "
										+ m.getKey() + ";");
							}
							JOptionPane.showMessageDialog(frame, "Stock Updated", "", 2);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			});
			btnDocketDelivered.setBounds(98, 236, 177, 25);
			frame.getContentPane().add(btnDocketDelivered);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void print_docket(int da_id, int day, int date) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = base.MysqlJDBC.getConnection().createStatement();
			Statement stmt2 = base.MysqlJDBC.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from DeliveryOrder where CustID in "
					+ "(select CustID from Customer where Area like (select "
					+ "Agent_Area from DeliveryAgents where Agent_ID = " + da_id + "));");
			while (rs.next()) {
				String pub_name = rs.getString("PublicationName");
				ResultSet pub_rs = stmt2
						.executeQuery("select * from publication where " + "name like \"" + pub_name + "\"");
				pub_rs.next();
				String freq = pub_rs.getString("frequence");
				if (freq.equals("Daily")) {
					System.out.println("Publication =" + pub_name + "\n Customer Name = " + rs.getString("CustName")
							+ "\n Customer Address = " + rs.getString("CustAddress") + "\n Amount = "
							+ rs.getString("PAmount"));
					int pub_id = pub_rs.getInt("id");
					Integer total_amount = pub_amount.get(pub_id);
					if (total_amount == null) {
						pub_amount.put(pub_id, rs.getInt("PAmount"));
					} else {
						pub_amount.put(pub_id, total_amount + rs.getInt("PAmount"));
					}
					if(pub_amount.get(pub_id) > pub_rs.getInt("Stock"))
					{
						JOptionPane.showMessageDialog(frame, "We do not have Stock for today delivery", "", 0);
						pub_amount.clear();
						return;
					}
				}
				// Monday
				else if (freq.equals("Weekly") && day == 1) {
					System.out.println("Publication =" + pub_name + "\n Customer Name = " + rs.getString("CustName")
							+ "\n Customer Address = " + rs.getString("CustAddress") + "\n Amount = "
							+ rs.getString("PAmount"));
					int pub_id = pub_rs.getInt("id");
					Integer total_amount = pub_amount.get(pub_id);
					if (total_amount == null) {
						pub_amount.put(pub_id, rs.getInt("PAmount"));
					} else {
						pub_amount.put(pub_id, total_amount + rs.getInt("PAmount"));
					}
					if(pub_amount.get(pub_id) > pub_rs.getInt("Stock"))
					{
						JOptionPane.showMessageDialog(frame, "We do not have Stock for today delivery", "", 0);
						pub_amount.clear();
						return;
					}
				}
				// Start of month
				else if (freq.equals("Monthly") && date == 1) {
					System.out.println("Publication = " + pub_name + "\n Customer Name = " + rs.getString("CustName")
							+ "\n Customer Address = " + rs.getString("CustAddress") + "\n Amount = "
							+ rs.getString("PAmount"));
					int pub_id = pub_rs.getInt("id");
					Integer total_amount = pub_amount.get(pub_id);
					if (total_amount == null) {
						pub_amount.put(pub_id, rs.getInt("PAmount"));
					} else {
						pub_amount.put(pub_id, total_amount + rs.getInt("PAmount"));
					}
					if(pub_amount.get(pub_id) > pub_rs.getInt("Stock"))
					{
						JOptionPane.showMessageDialog(frame, "We do not have Stock for today delivery", "", 0);
						pub_amount.clear();
						return;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}

class DateLabelFormatter extends AbstractFormatter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

}