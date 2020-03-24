package etf.nwt.datamicroservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
	
	List<Review> findAll();
	List<Review> findByMovie(@Param("item") long id);
	List<Review> findByContent(@Param("content") long id);
}
