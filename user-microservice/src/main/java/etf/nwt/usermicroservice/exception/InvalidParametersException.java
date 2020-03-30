package etf.nwt.usermicroservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidParametersException extends Exception {
	
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public InvalidParametersException(String message, HttpStatus httpStatus) {
		// super("Invalid request parameters for " + message);
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
}
