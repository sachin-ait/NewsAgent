package OrderReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderReport {

	static ArrayList<String> deleteList = new ArrayList<String>();
	static ArrayList<String> updateList = new ArrayList<String>();
	static String changedDetails;

	public static void OrderReport(ResultSet rs, int choice) {
		try {
			while (rs.next()) {
				int id = rs.getInt("DeliveryOrderId");
				String custName = rs.getString("CustName");
				String publicationName = rs.getString("PublicationName");
				String deliveryDate = rs.getString("DeliveryDate");
				changedDetails = "(" + id + ")(" + custName + ")(" + publicationName + ")(" + deliveryDate + ")";

				if (choice == 0) {
					deleteList.add(changedDetails);
				} else if (choice == 1) {
					updateList.add(changedDetails);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void readUpdatedReport() {
		System.out.println("Updated Delivery Order Report \n");
		System.out.println(updateList);
	}

	public static void readDeletedReport() {
		System.out.println("Deleted Delivery Order Report \n");
		System.out.println(deleteList);
	}
}
