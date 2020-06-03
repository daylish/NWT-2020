package etf.nwt.authorizationserver.repository;

import etf.nwt.authorizationserver.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;

@EntityScan("etf.nwt.authorizationserver.model")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
