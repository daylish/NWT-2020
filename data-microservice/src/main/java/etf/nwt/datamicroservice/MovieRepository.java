package etf.nwt.datamicroservice;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
	
	List<Movie> findAll();
	Movie findByTitle(@Param("title") String title);
	Movie findById(@Param("id") long id);
	List<Movie> findByGenre(@Param("genre") String genre);
}
