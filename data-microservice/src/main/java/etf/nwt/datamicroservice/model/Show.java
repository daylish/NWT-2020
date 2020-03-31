package etf.nwt.datamicroservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	
	//@NotNull
	// thought about @Min(1) but it'd cause problems
	// doesn't need to exist cause it can just be the size of episodes list
	// private int episodeCount;
	
	//@Size(min = 50, max = 300, message 
	//	      = "Description must be between 50 and 300 characters")
	// commented out for testing
	private String description;
	
	@NotBlank
	@NotNull(message = "Genre cannot be null.")
	private String genre;
	
	@NotNull(message = "Year cannot be null.")
	// just to prevent absolute nonsense 
    @Min(1900)
    @Max(2020)
	private int year;
	
	@OneToMany(cascade = CascadeType.REMOVE, targetEntity = ShowEpisode.class, mappedBy = "show")
	private List<ShowEpisode> episodes;
	
	/*
	@ElementCollection
	private List<ShowEpisode> episodes;
	*/
	
	// constructors, setters, getters
	public Show() {
		
	}
	
	public Show(String title, String description, String genre, int year) {
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.year = year;
	}
	
	public Long getShowId() {
		return showID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	/*
	public int getEpisodeCount() {
		return episodeCount;
	}
	
	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}
	*/
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	// now they'll show up in get requests
	public List<ShowEpisode> getEpisodes() {
		return episodes;
	}
}
