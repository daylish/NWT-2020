package etf.nwt.usermicroservice;

public class InvalidParametersException extends RuntimeException {
	
	InvalidParametersException(String message) {
		super("Invalid request parameters for " + message);
	}
}
