package publications;

import da.DAExceptionHandler;

public class Publication {
    private int id;
    private String name;
    private int amount;
    private double price;
    private String frequency;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getFreq() {
        return frequency;
    }

    public void setFreq(String freq) {
        this.frequency = freq;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
public Publication(String pubName, int pubAmount, double pubprice, String freq) throws DAExceptionHandler  {
		
		id = 0;
		
		// Validate Input
		try {
			
			validateName(pubName);
			validateAmount(pubAmount);
			validateFreq(freq);
			validatePrice(pubprice);
			
		}
		catch (DAExceptionHandler e) {
			throw e;
		}
		
		// Set Attributes
		name = pubName;
		amount = pubAmount;
		frequency = freq;
		price = pubprice;
	}

	public static void validateName(String pubName) throws DAExceptionHandler {
		
	}
	public static void validateAmount(int pamount) throws DAExceptionHandler {
		
	}
	public static void validateFreq(String pfreq) throws DAExceptionHandler {
		
	}
	public static void validatePrice(double pprice) throws DAExceptionHandler {
		
	}

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
