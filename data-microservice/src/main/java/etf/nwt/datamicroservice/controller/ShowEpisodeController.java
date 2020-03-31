package etf.nwt.datamicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import etf.nwt.datamicroservice.exception.ShowNotFoundException;
import etf.nwt.datamicroservice.model.ShowEpisode;
import etf.nwt.datamicroservice.repository.ShowEpisodeRepository;
import etf.nwt.datamicroservice.repository.ShowRepository;

@RestController
public class ShowEpisodeController {
	
	private ShowEpisodeRepository showEpisodeRepository;
	private ShowRepository showRepository;

	public ShowEpisodeController(ShowEpisodeRepository repository) {
		this.showEpisodeRepository = repository;
	}
	
	// fetch all episodes makes no sense so it's not implemented smile
	
	// fetch episodes by show id
	@GetMapping("/shows/{id}/episodes")
	@ResponseBody
	List<ShowEpisode> episodesForShow(@PathVariable Long id) {
		try {
			showRepository.findById(id);
			return showEpisodeRepository.findByContent(id);
		}
		catch (Exception e) {
			throw new ShowNotFoundException(id);
		}
	}
	
	// should editing request episode id? idk lad
	
	@DeleteMapping("/episodes/delete/{id}")
	void deleteEpisode(@PathVariable Long id) {
		showEpisodeRepository.deleteById(id);
	}
}
