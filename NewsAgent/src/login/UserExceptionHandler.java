package login;

public class UserExceptionHandler extends Exception {
    String message;

    public UserExceptionHandler(String message) {
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
