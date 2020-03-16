package etf.nwt.datamicroservice;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataMicroserviceApplication {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DataMicroserviceApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(MovieRepository repository) {
	    return (args) -> {
	      // save a few movies
	      repository.save(new Movie("Very Scary Movie", "People die, when they are killed", "Horror", 1982));
	      repository.save(new Movie("Very Scary Movie 2", "People die, when they are killed... again", "Horror", 1984));
	      repository.save(new Movie("Very Funny Movie", "People die (of laughter)", "Comedy", 1982));

	      // fetch all movies
	      log.info("Movies found with findAll():");
	      log.info("-------------------------------");
	      for (Movie movie : repository.findAll()) {
	        log.info(movie.toString());
	      }
	      log.info("");
	      
	      // fetch all users from...
	      log.info("Movies found with findByGenre():");
	      log.info("-------------------------------");
	      String genre = "Horror";
	      for (Movie movie : repository.findByGenre(genre)) {
	        log.info(movie.toString());
	      }
	      log.info("");
	    };
	  }
}
