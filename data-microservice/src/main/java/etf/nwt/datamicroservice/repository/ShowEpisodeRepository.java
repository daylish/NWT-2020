package etf.nwt.datamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import etf.nwt.datamicroservice.model.ShowEpisode;

import java.util.List;

@RepositoryRestResource
public interface ShowEpisodeRepository extends JpaRepository<ShowEpisode, Long>  {
	
	List<ShowEpisode> findAll();
	ShowEpisode findById(@Param("id") long id);
	List<ShowEpisode> findByContent(@Param("content") long id);
}
