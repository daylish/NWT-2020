package etf.nwt.datamicroservice;

public class ShowNotFoundException extends RuntimeException {
	
	ShowNotFoundException(Long id) {
		super("Could not find show with id " + id);
	}
}


