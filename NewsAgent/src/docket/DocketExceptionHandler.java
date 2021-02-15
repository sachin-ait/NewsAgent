package docket;

public class DocketExceptionHandler extends Exception {
    String message;

    public DocketExceptionHandler(String message) {
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
