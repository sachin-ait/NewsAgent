package publications;

import DeliveryOrder.DeliveryOrderExceptionHandler;
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

    public Publication(String pubName, int pubAmount, double pubprice, String freq) throws PubExceptionHandler {

        id = 0;

        // Validate Input
        try {

            validateName(pubName);
            validateAmount(pubAmount);
            validateFreq(freq);
            validatePrice(pubprice);

        } catch (PubExceptionHandler e) {
            throw e;
        }

        // Set Attributes
        name = pubName;
        amount = pubAmount;
        frequency = freq;
        price = pubprice;
    }

    public static void validateName(String pubName) throws PubExceptionHandler {
        if (pubName.isEmpty())
            throw new PubExceptionHandler("Publication Name NOT specified");
        else if (isNumeric(pubName))
            throw new PubExceptionHandler("Publication Name contains Numeric");
        else if (pubName.length() < 2)
            throw new PubExceptionHandler("Publication Name does not meet minimum length requirements");
        else if (pubName.length() > 20)
            throw new PubExceptionHandler("Publication Name does not exceeds maximum length requirements");
    }

    public static void validateAmount(int pamount) throws PubExceptionHandler {
        if (pamount < 0)
            throw new PubExceptionHandler("Publication amount does not meet minimum length requirements");
        else if (pamount > 20000)
            throw new PubExceptionHandler("Publication amount does not exceeds maximum length requirements");
    }

    public static void validateFreq(String pfreq) throws PubExceptionHandler {
        if (!(pfreq.equals("Daily") || pfreq.equals("Weekly") || pfreq.equals("Monthly")))
            throw new PubExceptionHandler("Publication frequency NOT specified");

    }

    public static void validatePrice(double pprice) throws PubExceptionHandler {
        if (pprice < 0)
            throw new PubExceptionHandler("Publication amount does not meet minimum length requirements");
        else if (pprice > 1000)
            throw new PubExceptionHandler("Publication amount does not exceeds maximum length requirements");
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
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
