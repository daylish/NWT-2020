package etf.nwt.datamicroservice;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
