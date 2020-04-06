package etf.nwt.datamicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import etf.nwt.datamicroservice.model.Review;

@RepositoryRestResource
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	List<Review> findAll();
	List<Review> findByMovie(@Param("item") long id);
	List<Review> findByContent(@Param("content") long id);
	List<Review> findByCreatorID(@Param("creatorID") long id);
}
