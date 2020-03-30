package etf.nwt.datamicroservice.controller;

import java.util.List;

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
import etf.nwt.datamicroservice.exception.ShowNotFoundException;
import etf.nwt.datamicroservice.model.Show;
import etf.nwt.datamicroservice.repository.ShowRepository;

@RestController
public class ShowController {
	
	private ShowRepository showRepository;
	
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
	Show showById(@PathVariable Long id) {
		return showRepository.findById(id)
			.orElseThrow(() -> new ShowNotFoundException(id));
	}
	
	// create new show
	@PostMapping("/show/new")
	@ResponseBody
	Show newShow(@RequestBody Show newShow) {
		try {
			showRepository.save(newShow);
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new show");
		}
			
		return newShow;
	}
	
	// edit only what makes sense
	@PutMapping("/shows/edit/{id}")
	@ResponseBody
	Show editShow(@RequestParam(name = "genre", required = false, defaultValue = "") String genre,
		@RequestParam(name = "description", required = false, defaultValue = "") String description,
		@PathVariable Long id) {

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
	void deleteShow(@PathVariable Long id) {
		try {
			showRepository.deleteById(id);
		}
		catch (Exception e) {
			throw new ShowNotFoundException(id);
		}
	}
}
