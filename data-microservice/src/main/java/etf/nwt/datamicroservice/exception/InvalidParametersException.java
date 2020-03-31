package etf.nwt.datamicroservice.exception;

public class InvalidParametersException extends Exception {
	
	protected String message; 
	
	public InvalidParametersException(String message) {
		//super("Invalid request parameters for " + message);
		this.message = message;
	}
	
	@Override
	public String getLocalizedMessage() {
		return "Invalid parameters for " + message;
	}
}

