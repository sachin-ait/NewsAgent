package DeliveryOrder;

import da.DA;
import da.DAExceptionHandler;

public class DeliveryOrder {
	
	private String name;
	private int custID;
	private String address;
	private String publication;
	private String date;
	private int pubamount;
	
	public String getName() {
		return name;
	}
	
	public int getCustId() {
		return custID;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setCustId(int cid) {
		this.custID = cid;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
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
	public int getOrderamount() {
        return pubamount;
    }

    public void setOrderAmount(int oa) {
        this.pubamount = oa;
    }

	public DeliveryOrder(String custName, int CustID,String caddress, String publicationName, String deliveryDate, int oa) throws Exception  {
		
		
		// Validate Input
		try {
			
			validateName(custName);
			validatePublication(publicationName);
			validateDate(deliveryDate);
			DA.validateLoggedHrs(oa);
			
		}
		catch (DeliveryOrderExceptionHandler | DAExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		name = custName;
		custID = CustID;
		publication = publicationName;
		date = deliveryDate;
		address = caddress;
		pubamount = oa;
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
		else if (deliveryDate.length() < 1)
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
