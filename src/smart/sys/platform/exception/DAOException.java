package smart.sys.platform.exception;

public class DAOException extends Exception {
	private String message = "";

	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
		this.message = message;
	}

	public DAOException(Exception e) {
		super(e);
		this.message = e.getMessage();
	}
	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DAOException(Throwable arg0) {
		super(arg0);
	}

	public String toString() {
		return super.getClass().getName() + ":" + this.message;
	}
}
