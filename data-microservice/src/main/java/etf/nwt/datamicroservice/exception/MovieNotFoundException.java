package etf.nwt.datamicroservice.exception;

public class MovieNotFoundException extends Exception {
	
	protected Long id;
	
	public MovieNotFoundException(Long id) {
		// super("Could not find movie with id " + id);
		this.id = id;
	}
	
	@Override
	public String getLocalizedMessage() {
		return "Movie with id " + id.toString() + " not found.";
	}
}
