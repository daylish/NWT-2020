package etf.nwt.datamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import etf.nwt.datamicroservice.model.Show;

@RepositoryRestResource
public interface ShowRepository extends JpaRepository<Show, Long> {
	
	List<Show> findAll();
	Show findByTitle(@Param("title") String title);
	Show findById(@Param("id") long id);
	List<Show> findByGenre(@Param("genre") String genre);
}
