package di;

public class DIExceptionHandler extends Exception {

	String message;

	public DIExceptionHandler(String errMessage) {
		message = errMessage;
	}

	public String getMessage() {
		return message;
	}
}
