package etf.nwt.datamicroservice.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// used to render an HTTP 404
@ControllerAdvice
public class StatusCodeAdvice extends ResponseEntityExceptionHandler {
	
	/*
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
	*/
	
	// passing id of non-existent movie handler
	@ExceptionHandler(MovieNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleMovieNotFound(MovieNotFoundException ex) {

		String error = "No movie with that id found.";

		ApiError apiError = 
				 	new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	// passing id of non-existent show handler
	@ExceptionHandler(ShowNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleShowNotFound(ShowNotFoundException ex) {

		String error = "No show with that id found.";

		ApiError apiError = 
				 	new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	// wrong parameters when creating movie/show
	@ExceptionHandler(InvalidParametersException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	protected ResponseEntity<Object> handleInvalidParameters(InvalidParametersException ex) {

		String error = "Invalid parameters for creating new movie/show.";

		ApiError apiError = 
				     new ApiError(HttpStatus.PRECONDITION_FAILED, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	     
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	    return handleExceptionInternal(
	      ex, apiError, headers, apiError.getStatus(), request);
	}
	
	// this exception is thrown when request missing parameter
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
		 MissingServletRequestParameterException ex, HttpHeaders headers, 
		 HttpStatus status, WebRequest request) {
		   String error = ex.getParameterName() + " parameter is missing";
		     
		   ApiError apiError = 
		      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		   return new ResponseEntity<Object>(
		      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	// when you use the wrong request type
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
		  HttpRequestMethodNotSupportedException ex, 
		  HttpHeaders headers, 
		  HttpStatus status, 
		  WebRequest request) {
		    StringBuilder builder = new StringBuilder();
		    builder.append(ex.getMethod());
		    builder.append(
		      " method is not supported for this request. Supported methods are ");
		    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		 
		    ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, 
		      ex.getLocalizedMessage(), builder.toString());
		    return new ResponseEntity<Object>(
		      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	// fall-back handler
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(
		      HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
		return new ResponseEntity<Object>(
		      apiError, new HttpHeaders(), apiError.getStatus());
	}
}