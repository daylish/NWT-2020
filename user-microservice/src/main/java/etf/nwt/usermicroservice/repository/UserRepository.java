package etf.nwt.usermicroservice.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import etf.nwt.usermicroservice.model.User;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@EntityScan("etf.nwt.usermicroservice.model")
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>  {
	 
	List<User> findAll();
	User findByUsername(@Param("username") String username);
	User findById(@Param("id") long id);
	List<User> findByLocation(@Param("location") String location);
}
