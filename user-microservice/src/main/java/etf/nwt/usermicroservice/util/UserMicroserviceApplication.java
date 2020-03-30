package etf.nwt.usermicroservice.util;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import etf.nwt.usermicroservice.model.User;
import etf.nwt.usermicroservice.repository.UserRepository;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("etf.nwt.usermicroservice.*")
public class UserMicroserviceApplication {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(UserRepository repository) {
	    return (args) -> {
	      // save a few users
	      repository.save(new User("nluebbert0", "vhakes0@smh.com.au", "USA"));
	      repository.save(new User("cguiness1", "mmccall1@who.int", "Finland"));
	      repository.save(new User("apelcheur2", "slonghorne2@wordpress.org", "Latvia"));
	      repository.save(new User("gdickons3", "ataffe3@cmu.edu", "USA"));

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
