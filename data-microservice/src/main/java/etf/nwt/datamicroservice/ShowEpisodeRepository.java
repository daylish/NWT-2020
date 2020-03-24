package etf.nwt.datamicroservice;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ShowEpisodeRepository extends PagingAndSortingRepository<ShowEpisode, Long>  {
	
	List<ShowEpisode> findAll();
	ShowEpisode findById(@Param("id") long id);
	List<ShowEpisode> findByContent(@Param("content") long id);
}
