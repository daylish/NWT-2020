package etf.nwt.datamicroservice.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// many part of ManyToOne for movies/shows/episodes
@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long reviewID;
	
	// id of the movie/show/episode the review is for
	// turns out this isn't a good approach
	//@NotNull
	//@NotBlank
	//private Long itemID;
	
	@ManyToOne
    @JoinColumn(name="item", nullable=false)
	private Movie movie;
	
	@NotNull
	private Long content;
	
	// add relation to user later
	
	@NotNull
	@NotBlank
	//@Size(min = 50, max = 500, message 
	//		  = "Review must be between 50 and 500 characters")
	// commented out for testing
	private String reviewText;
	
	// will be the datetime of commit converted to string
	@NotNull
	@NotBlank
	private String reviewDate;
	
	// not used anywhere
	public Review() {
		
	}
	
	public Review(Movie movie, String reviewText) {
		this.movie = movie;
		this.content = movie.getMovieId();
		this.reviewText = reviewText;
		
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		this.reviewDate = date; 
	}
	// possibly create separate constructor for show reviews?
	
	public Long getReviewId() {
		return reviewID;
	}
	
	public String getReviewText() {
		return reviewText;
	}
	
	public void setReviewText(String text) {
		this.reviewText = text;
	}
	
	public String getReviewDate() {
		return reviewDate;
	}
}
