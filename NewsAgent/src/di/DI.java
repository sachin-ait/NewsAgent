package di;

import java.util.Arrays;

public class DI {

	private int id;
	private String date;
	private int amountSuccess;
	private int amountFailed;
	private double paydue;

	void setId(int invoiceId) {
		id = invoiceId;
	}

	void setDate(String invoiceDate) {
		date = invoiceDate;
	}

	void setSuccess(int invoiceSuccess) {
		amountSuccess = invoiceSuccess;
	}

	void setFailed(int invoiceFailed) {
		amountFailed = invoiceFailed;
	}

	void setPay(double pay) {
		paydue = pay;
	}
	
	int getId() {
		return id;
	}

	String getDate() {
		return date;
	}

	int getSuccess() {
		return amountSuccess;
	}

	int getFailed() {
		return amountFailed;
	}

	double getPay() {
		return paydue;
	}
	
	public DI(int invDate1, String invDate2, int invDate3, int invSuccess, int invFailed, double Pay)
			throws DIExceptionHandler {

		id = 0;

		// Validate Input
		try {

			validateDateNULL(invDate1, invDate2, invDate3);
			validateDateMONTH(invDate2);
			validateDateSPECIAL(invDate1, invDate2, invDate3);
			validateSuccess(invSuccess);
			validateFailed(invFailed);
			validatePay(Pay);

		} catch (DIExceptionHandler e) {
			throw e;
		}

		// Set Attributes
		date = invDate1 + "/" + invDate2 + "/" + invDate3;
		amountSuccess = invSuccess;
		amountFailed = invFailed;
		paydue = Pay;
	}

	public static void validateDateNULL(int day, String month, int year) throws DIExceptionHandler {

		// Agree Formating Rules on "Invoice Date"
		// Invalid values for all cases
		
		if (year <= 0)
			throw new DIExceptionHandler("Year NOT specified");
		else if (month.length() == 0 || month.isEmpty() || month == " ")
			throw new DIExceptionHandler("Month is not specified");
		else if (day <= 0 || day >= 32)
			throw new DIExceptionHandler("Invalid amount of days");
		
	}
	public static void validateDateMONTH(String month) throws DIExceptionHandler {

		// Agree Formating Rules on "Invoice Date"
		// Invalid values for all cases
		// TODO: Replace handler for month to check through list for a match of the 12 months
					String months[] = new String[] {
							"January",
							"February",
							"March",
							"April",
							"May",
							"June",
							"July",
							"August",
							"September",
							"October",
							"November",
							"December"
							};
					boolean monthPresent = Arrays.asList(months).contains(month);
		
					if (monthPresent == false)
						throw new DIExceptionHandler("Month NOT valid");

	}
	
	public static void validateDateSPECIAL(int day, String month, int year) throws DIExceptionHandler {

		// Agree Formating Rules on "Invoice Date"
		// Invalid values for specific months

		if (month == "February") {
		    if (year % 4 == 0 && year % 100 != 0 && day > 29) {
		        throw new DIExceptionHandler("Leap year, February only has 29 days");
		    } else if (day > 28) {
		        throw new DIExceptionHandler("February only has 28 days");
		    }
		}
		if (month == "April")
			if(day > 30)
				throw new DIExceptionHandler("April only has 30 days");
		if (month == "June")
			if(day > 30)
				throw new DIExceptionHandler("June only has 30 days");
		if (month == "September") 
			if(day > 30)
				throw new DIExceptionHandler("September only has 30 days");
		if (month == "November")
			if(day > 30)
				throw new DIExceptionHandler("November only has 30 days");
	}

	public static void validateSuccess(int success) throws DIExceptionHandler {

		// Agree Formating Rules on "Invoice successes"
		// Amount of successes must be a whole positive number

		if (success < 0)
			throw new DIExceptionHandler("Successful deliveries cannot be negative");
	}

	public static void validateFailed(int failure) throws DIExceptionHandler {

		// Agree Formating Rules on "Invoice failures"
		// Amount of failures must be a whole positive number

		if (failure < 0)
			throw new DIExceptionHandler("Failed deliveries cannot be negative");
	}

	public static void validatePay(double payment) throws DIExceptionHandler {

		// Agree Formating Rules on "Delivery hours logged"

		if (payment < 0)
			throw new DIExceptionHandler("Payment cannot be negative");

	}

}
