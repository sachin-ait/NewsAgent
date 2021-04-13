package login;

import Customer.CustomerFrame;
import DeliveryOrder.DeliveryOrderFrame;
import da.DaFrame;
import docket.DocketFrame;
import docket.DocketFrameNew;
import invoice.InvoiceFrame;
import publications.PublicationFrame;

import java.sql.ResultSet;

public class LoginController {

	private static LoginMySQLAccess dao = null;

	static {
		try {
			dao = new LoginMySQLAccess();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet retrieveAllUserAccounts() {
		ResultSet resultSet = dao.retrieveAllUserAccounts();
		return resultSet;
	}

	public static Boolean update(int ID, String updatePassword) {
		boolean updateResult = dao.updateUserDetailsAccount(ID, updatePassword);
		return updateResult;
	}

	public static Boolean deleteUserByID(String ID) {
		boolean deleteResult = dao.deleteCustomerById(Integer.parseInt(ID));
		return deleteResult;
	}

	public static Boolean createUser(String userName, String password) throws Exception {
		User user = new User(userName, password);
		// Insert Customer Details into the database
		boolean insertResult = dao.insertUserDetailsAccount(user);
		if (insertResult == true)
			System.out.println("Customer Details Saved");
		else
			System.out.println("ERROR: Customer Details NOT Saved");
		return insertResult == true ? true : false;
	}

	public static int login(String userName, String password, String accessModel) throws Exception {
		ResultSet resultSet = dao.retrieveUser(userName, password);
		if (resultSet.next()) {
			System.out.println("Login success");
			switch (accessModel) {
			case "Customer":
				// Launch Billing Frame
				CustomerFrame custFrame = new CustomerFrame();
				custFrame.setVisible(true);
				System.out.println("User");
				break;
			case "DeliveryAgent":
				DaFrame daFrame = new DaFrame();
				daFrame.setVisible(true);
				System.out.println("DeliveryAgent");
				break;
			case "DeliveryOrder":
				DeliveryOrderFrame deliveryOrderFrame = new DeliveryOrderFrame();
				deliveryOrderFrame.setVisible(true);
				System.out.println("DeliveryOrder");
				break;
			case "DeliveryInvoice":
				InvoiceFrame invoiceFrame = new InvoiceFrame();
				invoiceFrame.setVisible(true);
				System.out.println("DI");
				break;
			case "Docket":
				System.out.println("Docket");
				DocketFrameNew doFrame = new DocketFrameNew();
				doFrame.setVisible(true);
				break;		
			case "User":
				// Launch Billing Frame
				UserFrame userFrame = new UserFrame();
				userFrame.setVisible(true);
				System.out.println("User");
				break;
			case "Publication":
				System.out.println("Publication");
				PublicationFrame pframe= new PublicationFrame();
				pframe.setVisible(true);
				break;
//			case "Report":
//				ReportFrame reportFrame = new ReportFrame();
//				reportFrame.setVisible(true);
//				System.out.println("Report");
//				break;
			}
			return 0;
		} else
			System.out.println("ERROR: Login Unsuccess");
		return 1;
	}
}
