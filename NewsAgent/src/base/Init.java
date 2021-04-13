package base;

import java.util.Scanner;

import login.User;

public class Init {

	public static void main(String[] args) throws Exception{
		System.out.println("Welcome to NewsAgent Application \n"
				+ "Please login into the system \n");

		while(true)
		  if (login.CommandLine.login() == 0)
			break;
		Scanner keybrd = new Scanner(System.in);
		boolean running= true;
		String input="";
		//User login= new User(null, null);
		while(running)
		{
			main_menu_show();
			if (keybrd.hasNext())
			  input= keybrd.next();
			switch (input) {
			case "1":
				login.CommandLine.main(null);
				break;
			case "2":
				da.CommandLine.main(null);
				break;
//			case "3":
//				biling.BillCommandLine.main(null);
//				break;
////			case "4":
//				cust.CommandLine.main(null);
//				break;
			case"5":
				publications.PublicationController.main(null);
				break;
			case "6":
				docket.CommandLine.main(null);
				break;
			case "7":
				DeliveryOrder.DeliveryOrderCommandLine.main(null);
				break;
			case "8":
				di.CommandLine.main(null);
				break;
			case "100":
				running= false;
				break;
			default:
				System.out.println("Please choose correct option");
				break;
			}

		}


		keybrd.close();
	}

	public static void main_menu_show() {
		  System.out.println(" ");
	        System.out.println("=============================================");
	        System.out.println("Please choose ONE of the following options:");
	        System.out.println("1. Edit Login users details");
	        System.out.println("2. Delivery Agents Menu");
	        System.out.println("3. Billing Menu");
	        System.out.println("4. Customer Menu");
	        System.out.println("5. View Publications Menu");
	        System.out.println("6. Delivery Dockets Menu");
	        System.out.println("7. Delivery Order Menu");
	        System.out.println("8. Delivery Invoice Menu");
	        System.out.println("100 Exit");
	        System.out.println("=============================================");
	        System.out.println(" ");
	}
}
