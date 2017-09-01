package exception;

public class MessageException extends Exception {
	
	public MessageException(String s) {
		super(s);
	}
	
	public MessageException(String s,Throwable c) {
		super(s,c);
	}


}
