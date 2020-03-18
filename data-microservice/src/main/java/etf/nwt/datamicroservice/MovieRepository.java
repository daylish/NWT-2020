package etf.nwt.datamicroservice;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
	Movie findByTitle(String title);
	Movie findById(long id);
	List<Movie> findByGenre(String genre);
	
}
