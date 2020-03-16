package etf.nwt.listmicroservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

public class MovieListItem {
	
	private Long listMovieID;
	private String listMovieStatus;
}
