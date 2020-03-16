package etf.nwt.usermicroservice;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserMicroserviceApplication {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(UserRepository repository) {
	    return (args) -> {
	      // save a few users
	      repository.save(new User("cxmgoblin69", "thiccthighs@gmail.com", "USA"));
	      repository.save(new User("larrybtw", "notafurry@gmail.com", "Finland"));
	      repository.save(new User("iamconfusion", "whyisitkansasbutnotarkansas@gmail.com", "USA"));
	      
	      // fetch all users
	      log.info("Users found with findAll():");
	      log.info("-------------------------------");
	      for (User user : repository.findAll()) {
	        log.info(user.toString());
	      }
	      log.info("");
	      
	      // fetch all users from...
	      log.info("Users found with findByLocation():");
	      log.info("-------------------------------");
	      String location = "USA";
	      for (User user : repository.findByLocation(location)) {
	        log.info(user.toString());
	      }
	      log.info("");
	    };
	  }
}
