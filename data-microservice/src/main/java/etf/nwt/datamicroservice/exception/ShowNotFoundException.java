package etf.nwt.datamicroservice.exception;

public class ShowNotFoundException extends Exception {

	protected Long id;
	
	public ShowNotFoundException(Long id) {
		// super("Could not find show with id " + id);
		this.id = id;
	}
	
	@Override
	public String getLocalizedMessage() {
		return "Show with id " + id.toString() + " not found.";
	}
}


