package etf.nwt.datamicroservice.exception;

public class InvalidParametersException extends RuntimeException {
	
	public InvalidParametersException(String message) {
		super("Invalid request parameters for " + message);
	}
}

