package DeliveryOrder;
public class DeliveryOrder {
	
	private String name;
	private String publication;
	private String date;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DeliveryOrder(String custName, String publicationName, String deliveryDate) throws DeliveryOrderExceptionHandler  {
		
		
		// Validate Input
		try {
			
			validateName(custName);
			validatePublication(publicationName);
			validateDate(deliveryDate);
			
		}
		catch (DeliveryOrderExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		name = custName;
		publication = publicationName;
		date = deliveryDate;
	}
	
	public static void validateName(String custName) throws DeliveryOrderExceptionHandler {
		
		//Agree Formating Rules on "Customer Name"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (custName.isEmpty())
			throw new DeliveryOrderExceptionHandler("DeliveryOrder Name NOT specified");
		else if(isNumeric(custName))
			throw new DeliveryOrderExceptionHandler("DeliveryOrder Name contains Numeric");
		else if (custName.length() < 2)
			throw new DeliveryOrderExceptionHandler("DeliveryOrder Name does not meet minimum length requirements");
		else if (custName.length() > 30)
			throw new DeliveryOrderExceptionHandler("DeliveryOrder Name does not exceeds maximum length requirements");
		
	}
	
	public static void validatePublication(String publicationName) throws DeliveryOrderExceptionHandler {
		
		//Agree Formating Rules on "Publication Name"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (publicationName.isEmpty())
			throw new DeliveryOrderExceptionHandler("Publication Name NOT specified");
		else if(isNumeric(publicationName))
			throw new DeliveryOrderExceptionHandler("Publication Name contains Numeric");
		else if (publicationName.length() < 2)
			throw new DeliveryOrderExceptionHandler("Publication Name does not meet minimum length requirements");
		else if (publicationName.length() > 30)
			throw new DeliveryOrderExceptionHandler("Publication Name does not exceeds maximum length requirements");
		
	}
	
	public static void validateDate(String deliveryDate) throws DeliveryOrderExceptionHandler {
		
		//Agree Formating Rules on "Customer PhoneNumber"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
		if (deliveryDate.isEmpty())
			throw new DeliveryOrderExceptionHandler("Delivery Date NOT specified");
		else if (deliveryDate.length() < 9)
			throw new DeliveryOrderExceptionHandler("Delivery Date does not meet minimum length requirements");
		else if (deliveryDate.length() > 11)
			throw new DeliveryOrderExceptionHandler("Delivery Date does not exceeds maximum length requirements");
		
	}
	
	public static boolean isNumeric(String string) {
		int intValue;

		try {
			intValue = Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {

		}
		return false;
	}

}
