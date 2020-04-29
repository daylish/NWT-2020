package etf.nwt.usermicroservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import etf.nwt.systemevents.EventsServiceGrpc;
import etf.nwt.usermicroservice.exception.InvalidParametersException;
import etf.nwt.usermicroservice.exception.UserNotFoundException;
import etf.nwt.usermicroservice.model.User;
import etf.nwt.usermicroservice.repository.UserRepository;
import net.minidev.json.JSONObject;

@EnableJpaRepositories("etf.nwt.usermicroservice.repository")
@EntityScan("etf.nwt.usermicroservice.model")
@PropertySource(value = "classpath:communication.properties", ignoreResourceNotFound = true)
@EnableAutoConfiguration
@RestController
public class UserController {
	// gRPC stuff
    private EventsServiceGrpc.EventsServiceBlockingStub eventsService;

	private UserRepository userRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
    
    @Value("${service.data}")
	private String dataServiceID;
	
	private String listServiceId = "LIST-MICROSERVICE";
	/*
	@Value("${service.list}")
	private String listServiceID;
	*/
	
	UserController(UserRepository repository) {
		this.userRepository = repository;
	}
	
	// fetch all users
	@GetMapping("/users")
	@ResponseBody
	List<User> allUsers() {
		return userRepository.findAll();
	}
	
	// fetch user by id
	@GetMapping("/users/{id}")
	@ResponseBody
	User userById(@PathVariable Long id) throws UserNotFoundException {

	  return userRepository.findById(id)
			  .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, id));
	}
	
	// fetch users by location
	@GetMapping("/users/location")
	@ResponseBody
	List<User> usersByLocation(@RequestParam(name = "location", required = true) String location) {

		return userRepository.findByLocation(location);
	}
	
	// create new user
	// for some reason needs userLocation instead of location??? - nvm i figured it out
	@PostMapping("/users/new")
	@ResponseBody
	User newUser(@RequestBody User newUser) throws InvalidParametersException {
		try {
			if (newUser.getUserAboutMe() == null)
				newUser.setUserAboutMe("");
			userRepository.save(newUser);
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new user", HttpStatus.PRECONDITION_FAILED);
		}
		
		return newUser;
	}
	
	// edit existing user
	// this works too btw
	/*
	@PutMapping("/users/edit/{id}")
	User editFullUser(@RequestBody User newUser, @PathVariable Long id) {

	  return userRepository.findById(id)
	    .map(user -> {
	    	user.setUsername(newUser.getUsername());
	    	user.setPassword(newUser.getPassword());
	    	user.setEmail(newUser.getEmail());
	    	user.setUserLocation(newUser.getUserLocation());
	    	user.setUserAboutMe(newUser.getUserAboutMe());
	        return userRepository.save(user);
	    })
	    .orElseGet(() -> {
	        newUser.setUserId(id);
	        return userRepository.save(newUser);
	    });
	}
	*/
	
	// edit only what makes sense
	@PutMapping("/users/edit/{id}")
	@ResponseBody
	User editUser(@RequestParam(name = "location", required = false, defaultValue = "") String location,
		@RequestParam(name = "desc", required = false, defaultValue = "") String userAboutMe,
		@PathVariable Long id) throws UserNotFoundException {

		return userRepository.findById(id)
			    .map(user -> {
			    	// location can't be empty 
			    	if (!location.equals(""))
			    		user.setUserLocation(location);
			    	user.setUserAboutMe(userAboutMe);
			        return userRepository.save(user);
			    }).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, id));
	}
	
	// delete existing user
	@DeleteMapping("/users/delete/{id}")
	void deleteUser(@PathVariable Long id) throws UserNotFoundException {
		try {
			userRepository.deleteById(id);
		}
		catch (Exception e) {
			throw new UserNotFoundException(HttpStatus.NOT_FOUND, id);
		}
	}
	
	/* COMMUNICATION */
	
	// for user-added movies
	// id doesn't need to get checked because the endpoint won't be accessible if it's not correct
	// needs title, desc, genre, year
	@PostMapping("/users/{id}/movies/add")
	@ResponseBody
	Object addMovieByUser(@PathVariable Long id, @RequestParam(name="title", required = true) String title,
					@RequestParam(name = "description", required = true) String description,
					@RequestParam(name = "genre", required = true) String genre,
					@RequestParam(name = "year", required = true) int year) throws InvalidParametersException {
		
		// fetching relevant data from eureka
		Application application = eurekaClient.getApplication(dataServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "movies/newpc";
        //System.out.println("URL: " + url);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("title", title);
        map.add("description", description);
        map.add("genre", genre);
        map.add("year", Integer.toString(year));
        map.add("creatorID", id.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            Object movie = restTemplate.postForObject(url, request, Object.class);
            return movie;
        }
        catch (Exception e) {
        	throw new InvalidParametersException("adding new movie", HttpStatus.PRECONDITION_FAILED);
        }
	}
	
	// same as for new movie
	@PostMapping("/users/{id}/shows/add")
	@ResponseBody
	Object addShowByUser(@PathVariable Long id, @RequestParam(name="title", required = true) String title,
					@RequestParam(name = "description", required = true) String description,
					@RequestParam(name = "genre", required = true) String genre,
					@RequestParam(name = "year", required = true) int year) throws InvalidParametersException {
		
		// fetching relevant data from eureka
		Application application = eurekaClient.getApplication(dataServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "shows/newp";
        //System.out.println("URL: " + url);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("title", title);
        map.add("description", description);
        map.add("genre", genre);
        map.add("year", Integer.toString(year));
        map.add("creatorID", id.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            Object show = restTemplate.postForObject(url, request, Object.class);
            return show;
        }
        catch (Exception e) {
        	throw new InvalidParametersException("adding new show", HttpStatus.PRECONDITION_FAILED);
        }
	}
	
	// get user's created items
	@GetMapping("/users/{id}/movies")
	@ResponseBody
	Object[] getMoviesCreatedByUser(@PathVariable Long id) {

		Application application = eurekaClient.getApplication(dataServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "movies/creator/" + id.toString();
        RestTemplate restTemplate = new RestTemplate();
        Object[] movies = restTemplate.getForObject(url, Object[].class);
        return movies;
	}
	
	// get user's created shows
	@GetMapping("/users/{id}/shows")
	@ResponseBody
	Object[] getShowsCreatedByUser(@PathVariable Long id) {

		Application application = eurekaClient.getApplication(dataServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "shows/creator/" + id.toString();
        RestTemplate restTemplate = new RestTemplate();
        Object[] shows = restTemplate.getForObject(url, Object[].class);
        return shows;
	}
	
	// get user's reviews
	@GetMapping("/users/{id}/reviews")
	@ResponseBody
	Object[] getReviewsCreatedByUser(@PathVariable Long id) {

		Application application = eurekaClient.getApplication(dataServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "reviews/creator/" + id.toString();
        RestTemplate restTemplate = new RestTemplate();
        Object[] reviews = restTemplate.getForObject(url, Object[].class);
        return reviews;
	}
	
	// add new review
	@PostMapping("users/{id}/movies/{movieID}/reviews/new") 
	@ResponseBody
	Object addReviewByUser(@PathVariable Long id, @PathVariable Long movieID, 
			@RequestParam(name = "text", required = true) String text) throws InvalidParametersException {
		Application application = eurekaClient.getApplication(dataServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "reviews/movie/" + movieID.toString() + "/new";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("text", text);
        map.add("creatorID", id.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            Object review = restTemplate.postForObject(url, request, Object.class);
            return review;
        }
        catch (Exception e) {
        	// if (e instanceof InvalidParametersException)
        	throw new InvalidParametersException("adding new review", HttpStatus.PRECONDITION_FAILED);
        }
	}

	//SINHRONA KOMUNIKACIJA SA LIST SERVISOM
	@PostMapping(value="/users/{userId}/lists/new")
	public ResponseEntity<?> createListByUser(@PathVariable("userId") Long userId, @RequestBody JSONObject lista) {
		
		Application app = eurekaClient.getApplication("list-microservice");
		InstanceInfo instanceInfo = app.getInstances().get(0);
		String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/lists/new";
		
		lista.put("userID", userId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> req = new HttpEntity<String>(lista.toString(), headers);
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<?> res = rt.postForEntity(url, req, Object.class);

		return res;
	}
}
