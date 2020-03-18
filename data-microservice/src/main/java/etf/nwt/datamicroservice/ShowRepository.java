package etf.nwt.datamicroservice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {
	Show findByTitle(String title);
	Show findById(long id);
	List<Show> findByGenre(String genre);
	
}
