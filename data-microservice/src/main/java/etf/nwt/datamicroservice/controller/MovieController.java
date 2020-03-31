package etf.nwt.datamicroservice.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import etf.nwt.datamicroservice.exception.InvalidParametersException;
import etf.nwt.datamicroservice.exception.MovieNotFoundException;
import etf.nwt.datamicroservice.model.Movie;
import etf.nwt.datamicroservice.repository.MovieRepository;

@EnableJpaRepositories("etf.nwt.datamicroservice.repository")
@EntityScan("etf.nwt.datamicroservice.model")
@RestController
public class MovieController {
	
	private MovieRepository movieRepository;
	
	MovieController(MovieRepository repository) {
		this.movieRepository = repository;
	}
	
	// fetch all movies
	@GetMapping("/movies")
	@ResponseBody
	List<Movie> allMovies() {
		return movieRepository.findAll();
	}
	
	// fetch movie by id
	@GetMapping("/movies/{id}")
	@ResponseBody
	Movie movieById(@PathVariable Long id) throws MovieNotFoundException {
		return movieRepository.findById(id)
				  .orElseThrow(() -> new MovieNotFoundException(id));
	}
	
	// create new movie
	@PostMapping("/movies/new")
	@ResponseBody
	Movie newMovie(@RequestBody Movie newMovie) throws InvalidParametersException {
		try {
			movieRepository.save(newMovie);
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new movie");
		}
		
		return newMovie;
	}
	
	// edit only what makes sense
	@PutMapping("/movies/edit/{id}")
	@ResponseBody
	Movie editMovie(@RequestParam(name = "genre", required = false, defaultValue = "") String genre,
		@RequestParam(name = "description", required = false, defaultValue = "") String description,
		@PathVariable Long id) throws MovieNotFoundException {

			return movieRepository.findById(id)
				   .map(movie -> {
					   if (!description.equals(""))
				    		movie.setDescription(description);
					   if (!genre.equals(""))
				    		movie.setGenre(genre);
				       return movieRepository.save(movie);
				    }).orElseThrow(() -> new MovieNotFoundException(id));
		}
	
	// delete existing movie
	@DeleteMapping("/movies/delete/{id}")
	void deleteMovie(@PathVariable Long id) throws MovieNotFoundException {
		try {
			movieRepository.deleteById(id);
		}
		catch (Exception e) {
			throw new MovieNotFoundException(id);
		}
	}
}
