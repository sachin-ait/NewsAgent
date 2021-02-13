package biling;

import java.util.Calendar;
import java.util.Date;

public class Bill {
    private int id;
    private String customerName;
    private String customerAddress;
    private double fee;
    private Date billDate;


    public Bill(String customerName, String customerAddress, double fee, Date billDate) throws BillExceptionHandler {

        try {
            validateCustomerName(customerName);
            validateCustomerAddress(customerAddress);
            validateFee(fee);
            validateBillDate(billDate);
        } catch (BillExceptionHandler billExceptionHandler) {
            throw billExceptionHandler;
        }

        id = 0;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.fee = fee;
        this.billDate = billDate;
    }

    static void validateBillDate(Date billDate) throws BillExceptionHandler {
        //Agree Formating Rules on "Bill Date"
        //E.G. Bill Date The billing date must be 30 days before or 30 days after the current date.

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(java.util.Calendar.DAY_OF_MONTH, -30);
        Date targetTime = cal.getTime();
        int compareTo = targetTime.compareTo(billDate);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(now);
        cal1.add(java.util.Calendar.DAY_OF_MONTH, 30);
        Date targetTime1 = cal.getTime();
        int compareTo2 = billDate.compareTo(targetTime1);

        if (compareTo > 0) {
            throw new BillExceptionHandler("Fee date does not meet minimum Date requirements");
        } else if (compareTo2 < 0) {
            throw new BillExceptionHandler("Fee date does not meet maximum Date requirements");
        }
    }


    static void validateFee(double fee) throws BillExceptionHandler {
        //Agree Formating Rules on "Fee"
        //E.G. Fee number must be a minimum of 0 and a maximum of 100,000
        if (fee < 0) {
            throw new BillExceptionHandler("Fee does not meet minimum length requirements");
        } else if (fee > 100000) {
            throw new BillExceptionHandler("Fee does not meet maximum length requirements");
        }
    }


    static void validateCustomerAddress(String customerAddress) throws BillExceptionHandler {
        //Agree Formating Rules on "Customer Address"
        //E.G. Address String must be a minimum of 10 characters and a maximum of 200 characters
        if (customerAddress.length() == 0 || customerAddress.isEmpty()) {
            throw new BillExceptionHandler("Customer Address NOT specified");
        } else if (customerAddress.length() < 10) {
            throw new BillExceptionHandler("Customer Address does not meet minimum length requirements");
        } else if (customerAddress.length() > 200) {
            throw new BillExceptionHandler("Customer Address exceeds maximum length requirements");
        }
    }

    static void validateCustomerName(String customerName) throws BillExceptionHandler {
        //Agree Formating Rules on "Biling Name"
        //E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
        if (customerName.length() == 0 || customerName.isEmpty()) {
            throw new BillExceptionHandler("Customer Name NOT specified");
        } else if (customerName.length() < 3) {
            throw new BillExceptionHandler("Customer Name does not meet minimum length requirements");
        } else if (customerName.length() > 50) {
            throw new BillExceptionHandler("Customer Name exceeds maximum length requirements");
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}
