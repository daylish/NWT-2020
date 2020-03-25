package etf.nwt.datamicroservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
	
	private ReviewRepository reviewRepository;
	
	ReviewController(ReviewRepository repository) {
		this.reviewRepository = repository;
	}
	
	// fetch all reviews
	@GetMapping("/reviews")
	@ResponseBody
	List<Review> allReviews() {
		return reviewRepository.findAll();
	}
	
	@GetMapping("/reviews/movie/{id}")
	@ResponseBody
	List<Review> allReviewsForMovie(@PathVariable Long id) {
		try {
			return reviewRepository.findByContent(id);
		}
		catch (Exception e) {
			return new ArrayList<Review>();
		}
	}
	
	// edit and delete review also needed
	// editing review text works
	@PutMapping("/reviews/edit/{id}")
	@ResponseBody
	Review editReview(@RequestParam(name = "text", required = false, defaultValue = "") String reviewText,
		@PathVariable Long id) {
		
			return reviewRepository.findById(id)
				   .map(review -> {
					   if (!reviewText.equals(""))
				    		review.setReviewText(reviewText);
				       return reviewRepository.save(review);
				    }).orElseThrow(() -> new InvalidParametersException("editing review"));
		}
	
	// delete works
	@DeleteMapping("/reviews/delete/{id}")
	void deleteReview(@PathVariable Long id) {
		reviewRepository.deleteById(id);
	}
}	
