package exception;

public class MessageException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageException(String s) {
		super(s);
	}
	
	public MessageException(String s,Throwable c) {
		super(s,c);
	}


}
