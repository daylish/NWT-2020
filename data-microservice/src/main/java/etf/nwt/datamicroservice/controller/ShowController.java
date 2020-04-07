package etf.nwt.datamicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import etf.nwt.datamicroservice.exception.InvalidParametersException;
import etf.nwt.datamicroservice.exception.ShowNotFoundException;
import etf.nwt.datamicroservice.model.Movie;
import etf.nwt.datamicroservice.model.Show;
import etf.nwt.datamicroservice.repository.ShowRepository;

@RestController
public class ShowController {
	
	private ShowRepository showRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
    
    @Value("${service.user}")
    private String userServiceID;
	
	public ShowController(ShowRepository repository) {
		this.showRepository = repository;
	}
	
	// fetch all shows
	@GetMapping("/shows")
	@ResponseBody
	List<Show> allShows() {
		return showRepository.findAll();
	}
	
	// fetch show by id
	@GetMapping("/shows/{id}")
	@ResponseBody
	Show showById(@PathVariable Long id) throws ShowNotFoundException {
		return showRepository.findById(id)
			.orElseThrow(() -> new ShowNotFoundException(id));
	}
	
	// create new show
	@PostMapping("/shows/new")
	@ResponseBody
	Show newShow(@RequestBody Show newShow) throws InvalidParametersException {
		try {
			showRepository.save(newShow);
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new show");
		}
			
		return newShow;
	}
	
	// new movie by params
	// the path is dumb but w/e
	@PostMapping("/shows/newp")
	@ResponseBody
	Show newShowP(@RequestParam(name="title", required = true) String title,
			@RequestParam(name = "description", required = true) String description,
			@RequestParam(name = "genre", required = true) String genre,
			@RequestParam(name = "year", required = true) int year,
			@RequestParam(name = "creatorID", required = false) Long creatorID) throws InvalidParametersException {
		try {
			Show newShow = new Show(title, description, genre, year);
			newShow.setCreatorId(creatorID);
			showRepository.save(newShow);
			return newShow;
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new show");
		}
	}
	
	// edit only what makes sense
	@PutMapping("/shows/edit/{id}")
	@ResponseBody
	Show editShow(@RequestParam(name = "genre", required = false, defaultValue = "") String genre,
		@RequestParam(name = "description", required = false, defaultValue = "") String description,
		@PathVariable Long id) throws ShowNotFoundException {

			return showRepository.findById(id)
					  .map(show -> {
						  if (!description.equals(""))
					    	show.setDescription(description);
						  if (!genre.equals(""))
					    		show.setGenre(genre);
					       return showRepository.save(show);
					    }).orElseThrow(() -> new ShowNotFoundException(id));
		}
	
	// delete existing show
	@DeleteMapping("/shows/delete/{id}")
	void deleteShow(@PathVariable Long id) throws ShowNotFoundException {
		try {
			showRepository.deleteById(id);
		}
		catch (Exception e) {
			throw new ShowNotFoundException(id);
		}
	}
	
	// fetch shows created by certain user
	@GetMapping("/shows/creator/{id}")
	@ResponseBody
	List<Show> getShowsByCreatorId(@PathVariable Long id) {
		return showRepository.findByCreatorID(id);
	}
	
	/* COMMUNICATION */
	// terrible path but :)))
	@PostMapping("/shows/newpc")
	@ResponseBody
	Show newShowParametersControl(@RequestParam(name="title", required = true) String title,
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
				Show newShow = new Show(title, description, genre, year);
				newShow.setCreatorId(creatorID);
				showRepository.save(newShow);
				return newShow;
	        }
	        else throw new InvalidParametersException("creating new show");
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new show");
		}
	}
}
