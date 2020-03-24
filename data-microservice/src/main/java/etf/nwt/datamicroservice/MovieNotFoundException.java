package etf.nwt.datamicroservice;

public class MovieNotFoundException extends RuntimeException {
	
	MovieNotFoundException(Long id) {
		super("Could not find movie with id " + id);
	}
}
