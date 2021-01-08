package exceptions;

public class WrongTrackNumberException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public WrongTrackNumberException(String msg) {
		super(msg);
	}
}
