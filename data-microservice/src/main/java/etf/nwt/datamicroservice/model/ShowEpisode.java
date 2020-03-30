package etf.nwt.datamicroservice.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ShowEpisode {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long episodeID;
	
	/*
	@NotNull
	private Long showID;
	*/
	
	@ManyToOne
    @JoinColumn(name="item", nullable=false)
	private Show show;
	
	@NotBlank
	@NotNull(message = "Title cannot be null.")
	private String title;
	
	//@Size(min = 50, max = 255, message 
	//	      = "Description must be between 50 and 255 characters")
	// for testing
	private String description;
	
	@NotNull
	private Long content;
	
	public ShowEpisode() {
		
	}
	
	public ShowEpisode(Show show, String title, String description) {
		this.show = show;
		this.title = title;
		this.content = show.getShowId();
		this.description = description;
	}
	
	public Long getEpisodeId() {
		return episodeID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
