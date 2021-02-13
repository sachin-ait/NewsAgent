package DeliveryOrder;
public class DeliveryOrderExceptionHandler extends Exception {
	
	String message;
	
	public DeliveryOrderExceptionHandler(String errMessage){
		message = errMessage;
	}
	
	public String getMessage() {
		return message;
	}
}
