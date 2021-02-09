
public class DA {
	
	private int id;
	private String name;
	private String area;
	private int payRate;
	private int hrslogged;
	
	void setId(int employeeId) {
		id = employeeId;
	}
	
	void setName(String employeeName) {
		name = employeeName;
	}
	
	void setArea(String deliveryArea) {
		area = deliveryArea;
	}
	
	void setPayRate(int employeePayRate) {
		payRate = employeePayRate;
	}
	
	void setHrsLogged(int agentHrs) {
		hrslogged = agentHrs;
	}
	
	int getId() {
		return id;
	}
	
	String getName() {
		return name;
	}
	
	String getArea() {
		return area;
	}
	
	int getPayRate() {
		return payRate;
	}
	int getHrsLogged() {
		return hrslogged;
	}
	
	public DA(String employeeName, String deliveryArea, int employeePayRate, int agentHrs) throws DAExceptionHandler  {
		
		id = 0;
		
		// Validate Input
		try {
			
			validateName(employeeName);
			validateArea(deliveryArea);
			validatePayRate(employeePayRate);
			validateLoggedHrs(agentHrs);
			
		}
		catch (DAExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		name = employeeName;
		area = deliveryArea;
		payRate = employeePayRate;
		hrslogged = agentHrs;
	}
	
	public static void validateName(String employeeName) throws DAExceptionHandler {
		
		//Agree Formating Rules on "Delivery Agent Name"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (employeeName.length() == 0 || employeeName.isEmpty())
			throw new DAExceptionHandler("Agent Name NOT specified");
		else if (employeeName.length() < 3)
			throw new DAExceptionHandler("Agent Name does not meet minimum length requirements");
		else if (employeeName.length() > 50)
			throw new DAExceptionHandler("Agent Name exceeds maximum length requirements");
		
	}
	
	public static void validateArea(String deliveryArea) throws DAExceptionHandler {
		
		//Agree Formating Rules on "Delivery Agent"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (deliveryArea.length() == 0 || deliveryArea.isEmpty())
			throw new DAExceptionHandler("Delivery area is NOT specified");
		else if (deliveryArea.length() < 4)
			throw new DAExceptionHandler("Delivery area does not meet minimum length requirements");
		else if (deliveryArea.length() > 60)
			throw new DAExceptionHandler("Delivery area exceeds maximum length requirements");
		
	}
	
	public static void validatePayRate(int employeePayRate) throws DAExceptionHandler {
		
		//Agree Formating Rules on "Employee Pay Rate"
		
		if (employeePayRate == 0)
			throw new DAExceptionHandler("Agents pay is NOT specified");
		else if (employeePayRate < 0)
			throw new DAExceptionHandler("Agents pay can not be negative");
	}
	
public static void validateLoggedHrs(int agentHrs) throws DAExceptionHandler {
		
		//Agree Formating Rules on "Delivery hours logged"

		
		if (agentHrs < 0)
			throw new DAExceptionHandler("Agents hours can not be negative");
		
	}

}
