package etf.nwt.datamicroservice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShowRepository extends PagingAndSortingRepository<Show, Long> {
	
	List<Show> findAll();
	Show findByTitle(@Param("title") String title);
	Show findById(@Param("id") long id);
	List<Show> findByGenre(@Param("genre") String genre);
}
