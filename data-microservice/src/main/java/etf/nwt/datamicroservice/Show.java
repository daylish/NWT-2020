package etf.nwt.datamicroservice;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Show {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long showID;
	
	@NotBlank
	@NotNull(message = "Title cannot be null.")
	private String title;
	
	private int episodeCount;
	
	//@Size(min = 100, max = 500, message 
	//	      = "Description must be between 100 and 500 characters")
	private String description;
	
	@NotBlank
	@NotNull(message = "Genre cannot be null.")
	private String genre;
	
	@NotNull(message = "Year cannot be null.")
	private int year;
	
	@ElementCollection
	private List<ShowEpisode> items;
}
