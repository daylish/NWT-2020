package etf.nwt.usermicroservice;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {
	
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	
	UserNotFoundException(HttpStatus httpStatus, Long id) {
		super("Could not find user with id " + id);
        this.httpStatus = httpStatus;
	}
	
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
