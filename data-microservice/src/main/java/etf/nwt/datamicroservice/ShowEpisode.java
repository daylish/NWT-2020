package etf.nwt.datamicroservice;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ShowEpisode {
	
	@NotBlank
	@NotNull(message = "Title cannot be null.")
	private String title;
	
	//@Size(min = 100, max = 500, message 
	//	      = "Description must be between 100 and 500 characters")
	private String description;
}
