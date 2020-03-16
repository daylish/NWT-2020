package etf.nwt.datamicroservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long movieID;
	
	@NotBlank
	@NotNull(message = "Title cannot be null.")
	private String title;
	
	//@Size(min = 100, max = 500, message 
	//	      = "Description must be between 100 and 500 characters")
	private String description;
	
	@NotBlank
	@NotNull(message = "Genre cannot be null.")
	private String genre;
	
	@NotNull(message = "Year cannot be null.")
	private int year;
	
	// not used anywhere
	protected Movie() {}

	public Movie(String title, String description, String genre, int year) {
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.year = year;
	}
	
	@Override
	public String toString() {
	    return String.format(
	        "Movie[id=%d, title='%s', description='%s', genre='%s', year=%d]",
	        movieID, title, description, genre, year);
	}
	
	public Long getMovieID() {
	    return movieID;
	}
	
	public String getDescription() {
	    return description;
	}
	
	public String getGenre() {
	    return genre;
	}
	
	public int getYear() {
	    return year;
	}
}
