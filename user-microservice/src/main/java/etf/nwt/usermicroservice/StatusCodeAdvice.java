package etf.nwt.usermicroservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// used to render an HTTP 404
@ControllerAdvice
public class StatusCodeAdvice {
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(InvalidParametersException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	String invalidParametersHandler(InvalidParametersException ex) {
		return ex.getMessage();
	}
}
