package etf.nwt.datamicroservice.exception;

public class ShowNotFoundException extends RuntimeException {
	
	public ShowNotFoundException(Long id) {
		super("Could not find show with id " + id);
	}
}


