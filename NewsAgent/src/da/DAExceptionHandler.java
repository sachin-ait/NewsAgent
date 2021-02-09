package da;
public class DAExceptionHandler extends Exception {
	
	String message;
	
	public DAExceptionHandler(String errMessage){
		message = errMessage;
	}
	
	public String getMessage() {
		return message;
	}
}
