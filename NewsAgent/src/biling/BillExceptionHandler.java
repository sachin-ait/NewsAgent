package biling;

public class BillExceptionHandler extends Exception {
    String message;

    public BillExceptionHandler(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
