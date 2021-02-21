package cust;
public class Customer {
	
	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	private double payment;
	private String area;
	
	static int testInt;
	static double testDouble;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Customer(String custName, String custAddr, String custPhone, Double custPayment,String custArea) throws CustomerExceptionHandler  {
		
		id = 0;
		
		// Validate Input
		try {
			
			validateName(custName);
			validateAddress(custAddr);
			validatePhoneNumber(custPhone);
			validateArea(custArea);
			
		}
		catch (CustomerExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		name = custName;
		address = custAddr;
		phoneNumber = custPhone;
		payment = custPayment;
		area = custArea;
		
	}
	
	public static void validateName(String custName) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Name"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (custName.isEmpty())
			throw new CustomerExceptionHandler("Customer Name NOT specified");
		else if (custName.length() < 2)
			throw new CustomerExceptionHandler("Customer Name does not meet minimum length requirements");
		else if (custName.length() > 15)
			throw new CustomerExceptionHandler("Customer Name does not exceeds maximum length requirements");
		
	}
	
	public static void validateAddress(String custAddr) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Address"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (custAddr.isEmpty())
			throw new CustomerExceptionHandler("Customer Address NOT specified");
		else if (custAddr.length() < 5)
			throw new CustomerExceptionHandler("Customer Address does not meet minimum length requirements");
		else if (custAddr.length() > 60)
			throw new CustomerExceptionHandler("Customer Address does not exceeds maximum length requirements");
		
	}
	
	public static void validatePhoneNumber(String custPhone) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer PhoneNumber"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
		if (custPhone.isEmpty())
			throw new CustomerExceptionHandler("Customer PhoneNumber NOT specified");
		else if (custPhone.length() < 9)
			throw new CustomerExceptionHandler("Customer PhoneNumber does not meet minimum length requirements");
		else if (custPhone.length() > 12)
			throw new CustomerExceptionHandler("Customer PhoneNumber does not exceeds maximum length requirements");
		
	}
	
	public static void validateArea(String custArea) throws CustomerExceptionHandler {
		
		//Agree Formating Rules on "Customer Area"
		
		if (custArea.isEmpty())
			throw new CustomerExceptionHandler("Customer Area NOT specified");
	
	}
	
	

}
