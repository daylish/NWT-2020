package etf.nwt.usermicroservice;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

// second type is type of User ID
public interface UserRepository extends CrudRepository<User, Long>  {
	 
	User findByUsername(String username);
	User findById(long id);
	List<User> findByLocation(String location);
}
