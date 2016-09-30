package smart.sys.platform.exception;

public class AppException extends Exception {

	private String message = "";
	
	public AppException() {
	}
	public AppException(String message) {
		super(message);
		this.message = message;
	}
	public AppException(Exception e) {
		super(e);
		this.message = e.getMessage();
	}

	public AppException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AppException(Throwable arg0) {
		super(arg0);
	}
	
	public String toString() {
		return super.getClass().getName() + ":" + this.message;
	}
}
