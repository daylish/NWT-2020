package etf.nwt.usermicroservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidParametersException extends Exception {
	
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private String message;
	
	public InvalidParametersException(String message, HttpStatus httpStatus) {
		// super("Invalid request parameters for " + message);
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    @Override
    public String getLocalizedMessage() {
    	return "Invalid parameters";
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
    
    @Override
    public String getMessage() {
    	return message;
    }
}
