package publications;
public class PubExceptionHandler extends Exception {
	
	String message;
	
	public PubExceptionHandler(String errMessage){
		message = errMessage;
	}
	
	public String getMessage() {
		return message;
	}
}
