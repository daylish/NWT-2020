package etf.nwt.datamicroservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// used to render an HTTP 404
@ControllerAdvice
public class StatusCodeAdvice {
	
	@ResponseBody
	@ExceptionHandler(MovieNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String movieNotFoundHandler(MovieNotFoundException ex) {
		return ex.getMessage();
	}
	
	// i'm stupid for making these two separate exceptions but i'm also too lazy to fix it so :)
	@ResponseBody
	@ExceptionHandler(ShowNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String movieNotFoundHandler(ShowNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(InvalidParametersException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	String invalidParametersHandler(InvalidParametersException ex) {
		return ex.getMessage();
	}
}