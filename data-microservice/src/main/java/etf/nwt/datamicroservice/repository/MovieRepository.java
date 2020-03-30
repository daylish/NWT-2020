package etf.nwt.datamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import etf.nwt.datamicroservice.model.Movie;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findAll();
	Movie findByTitle(@Param("title") String title);
	Movie findById(@Param("id") long id);
	List<Movie> findByGenre(@Param("genre") String genre);
}
