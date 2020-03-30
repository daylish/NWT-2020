package etf.nwt.usermicroservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends Exception {
	
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private Long id;
	
	public UserNotFoundException(HttpStatus httpStatus, Long id) {
		// super("Could not find user with id " + id);
		this.id = id;
        this.httpStatus = httpStatus;
	}
	
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    @Override
    public String getLocalizedMessage() {
    	return "User with id " + id.toString() + " not found.";
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
