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
public class ShowEpisodeController {
	
	private ShowEpisodeRepository showEpisodeRepository;
	
	public ShowEpisodeController(ShowEpisodeRepository repository) {
		this.showEpisodeRepository = repository;
	}
	
	// fetch all episodes makes no sense so it's not implemented smile
	
	// fetch episodes by show id
	@GetMapping("/shows/{id}/episodes")
	@ResponseBody
	List<ShowEpisode> episodesForShow(@PathVariable Long id) {
		try {
			return showEpisodeRepository.findByContent(id);
		}
		catch (Exception e) {
			return new ArrayList<ShowEpisode>();
		}
	}
	
	@DeleteMapping("/episodes/delete/{id}")
	void deleteEpisode(@PathVariable Long id) {
		showEpisodeRepository.deleteById(id);
	}
}
