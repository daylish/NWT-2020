package etf.nwt.usermicroservice;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long>  {
	 
	List<User> findAll();
	User findByUsername(@Param("username") String username);
	User findById(@Param("id") long id);
	List<User> findByLocation(@Param("location") String location);
}
