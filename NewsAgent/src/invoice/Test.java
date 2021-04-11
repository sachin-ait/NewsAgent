package invoice;

public class Test {
    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
//        InvoiceMySQLAccess invoiceMySQLAccess = new InvoiceMySQLAccess();
//        String customerName = "Jack";
//        String frequence = "";
//        invoiceMySQLAccess.retrieveInvoice(customerName, frequence);

        InvoiceFrame invoiceFrame = new InvoiceFrame();
        invoiceFrame.setVisible(true);
    }
}
