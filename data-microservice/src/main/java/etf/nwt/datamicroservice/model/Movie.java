package etf.nwt.datamicroservice.model;

import java.util.List;

import javax.persistence.Column;
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
public class Movie {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long movieID;
	
	@NotBlank
	@NotNull(message = "Title cannot be null.")
	private String title;
	
	//@Size(min = 50, max = 300, message 
	//	      = "Description must be between 50 and 300 characters")
	// commented out for testing purposes
	private String description;
	
	@NotBlank
	@NotNull(message = "Genre cannot be null.")
	private String genre;
	
	@NotNull(message = "Year cannot be null.")
	// just to prevent absolute nonsense 
    @Min(1900)
    @Max(2020)
	private int year;
	
	@OneToMany(targetEntity = Review.class, mappedBy = "movie")
	private List<Review> reviews;
	
	// not used anywhere
	protected Movie() {}

	public Movie(String title, String description, String genre, int year) {
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.year = year;
		// reviews list is empty on creation
	}
	
	@Override
	public String toString() {
	    return String.format(
	        "Movie[id=%d, title='%s', description='%s', genre='%s', year=%d]",
	        movieID, title, description, genre, year);
	}
	
	public Long getMovieId() {
	    return movieID;
	}
	
	public String getTitle() {
	    return title;
	}
	
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
	
	public List<Review> getReviews() {
		return reviews;
	}
}
