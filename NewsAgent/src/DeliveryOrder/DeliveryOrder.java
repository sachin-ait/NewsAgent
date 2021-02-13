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

	public DeliveryOrder(String custName, String publicationName, String deliverDate) throws DeliveryOrderExceptionHandler  {
		
		
		// Validate Input
		try {
			
			validateName(custName);
			validatePublication(publicationName);
			validateDate(deliverDate);
			
		}
		catch (DeliveryOrderExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		name = custName;
		publication = publicationName;
		date = deliverDate;
	}
	
	public static void validateName(String custName) throws DeliveryOrderExceptionHandler {
		
		//Agree Formating Rules on "Customer Name"
		//E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
		
		if (custName.isEmpty())
			throw new DeliveryOrderExceptionHandler("Customer Name NOT specified");
		else if (custName.length() < 2)
			throw new DeliveryOrderExceptionHandler("Customer Name does not meet minimum length requirements");
		else if (custName.length() > 30)
			throw new DeliveryOrderExceptionHandler("Customer Name does not exceeds maximum length requirements");
		
	}
	
	public static void validatePublication(String publicationName) throws DeliveryOrderExceptionHandler {
		
		//Agree Formating Rules on "Publication Name"
		//E.G. Name String must be a minimum of 5 characters and a maximum of 60 characters
		
		if (publicationName.isEmpty())
			throw new DeliveryOrderExceptionHandler("Publication Name NOT specified");
		else if (publicationName.length() < 2)
			throw new DeliveryOrderExceptionHandler("Publication Name does not meet minimum length requirements");
		else if (publicationName.length() > 30)
			throw new DeliveryOrderExceptionHandler("Publication Name does not exceeds maximum length requirements");
		
	}
	
	public static void validateDate(String deliverDate) throws DeliveryOrderExceptionHandler {
		
		//Agree Formating Rules on "Customer PhoneNumber"
		//E.G. Name String must be a minimum of 7 characters and a maximum of 15 characters
		
		if (deliverDate.isEmpty())
			throw new DeliveryOrderExceptionHandler("Deliver Date NOT specified");
		else if (deliverDate.length() < 9)
			throw new DeliveryOrderExceptionHandler("Deliver Date does not meet minimum length requirements");
		else if (deliverDate.length() > 11)
			throw new DeliveryOrderExceptionHandler("Deliver Date does not exceeds maximum length requirements");
		
	}
	

}
