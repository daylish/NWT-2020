package etf.nwt.datamicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import org.springframework.web.client.RestTemplate;

import etf.nwt.datamicroservice.exception.InvalidParametersException;
import etf.nwt.datamicroservice.exception.MovieNotFoundException;
import etf.nwt.datamicroservice.model.Movie;
import etf.nwt.datamicroservice.repository.MovieRepository;
import etf.nwt.datamicroservice.util.QueueConsumer;

@EnableJpaRepositories("etf.nwt.datamicroservice.repository")
@EntityScan("etf.nwt.datamicroservice.model")
@PropertySource(value = "communication.properties", ignoreResourceNotFound = true)
@RestController
public class MovieController {
	
	@Autowired
	QueueConsumer queueConsumer;
	
	private MovieRepository movieRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
    
    @Value("${service.user}")
    private String userServiceID;
	
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
	
	// new movie by params
	// the path is dumb but w/e
	@PostMapping("/movies/newp")
	@ResponseBody
	Movie newMovieParameters(@RequestParam(name="title", required = true) String title,
			@RequestParam(name = "description", required = true) String description,
			@RequestParam(name = "genre", required = true) String genre,
			@RequestParam(name = "year", required = true) int year,
			@RequestParam(name = "creatorID", required = false) Long creatorID) throws InvalidParametersException {
		try {
			Movie newMovie = new Movie(title, description, genre, year);
			newMovie.setCreatorId(creatorID);
			movieRepository.save(newMovie);
			return newMovie;
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new movie");
		}
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
	
	// fetch movies created by certain user
	@GetMapping("/movies/creator/{id}")
	@ResponseBody
	List<Movie> getMoviesByCreatorId(@PathVariable Long id) {
		return movieRepository.findByCreatorID(id);
	}
	
	/* COMMUNICATION */
	// terrible path but :)))
	@PostMapping("/movies/newpc")
	@ResponseBody
	Movie newMovieParametersControl(@RequestParam(name="title", required = true) String title,
			@RequestParam(name = "description", required = true) String description,
			@RequestParam(name = "genre", required = true) String genre,
			@RequestParam(name = "year", required = true) int year,
			@RequestParam(name = "creatorID", required = false) Long creatorID) throws InvalidParametersException {
		try {
			// checking if said user exists
			Application application = eurekaClient.getApplication(userServiceID);
	        InstanceInfo instanceInfo = application.getInstances().get(0);
	        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "users/" + creatorID.toString();
	        
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
	        
	        if (response.getStatusCode().equals(HttpStatus.OK)) {
	        	// adding movie
				Movie newMovie = new Movie(title, description, genre, year);
				newMovie.setCreatorId(creatorID);
				movieRepository.save(newMovie);
				return newMovie;
	        }
	        else throw new InvalidParametersException("creating new movie");
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new movie");
		}
	}
	
	// i don't even know?? get all users that added a movie??
	// for now just trying to get all users via this i guess
	// this is useless and wild and just for testing for now :) 
	/*
    @GetMapping("/movies/users")
    @ResponseBody
    Object[] getMovieUsers() {
    	
    	Application application = eurekaClient.getApplication(userServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "users";
        //System.out.println("URL: " + url);
        RestTemplate restTemplate = new RestTemplate();
        Object[] user = restTemplate.getForObject(url, Object[].class);
        return user;
    }
	*/
}
