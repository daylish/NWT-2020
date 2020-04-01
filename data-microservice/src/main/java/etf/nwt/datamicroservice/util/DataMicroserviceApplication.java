package etf.nwt.datamicroservice.util;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import etf.nwt.datamicroservice.model.Movie;
import etf.nwt.datamicroservice.model.Review;
import etf.nwt.datamicroservice.model.Show;
import etf.nwt.datamicroservice.model.ShowEpisode;
import etf.nwt.datamicroservice.repository.MovieRepository;
import etf.nwt.datamicroservice.repository.ReviewRepository;
import etf.nwt.datamicroservice.repository.ShowEpisodeRepository;
import etf.nwt.datamicroservice.repository.ShowRepository;

@EnableEurekaClient 
@SpringBootApplication
@ComponentScan("etf.nwt.datamicroservice.*")
public class DataMicroserviceApplication {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DataMicroserviceApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(MovieRepository repository, ReviewRepository reviewRepository, 
			  ShowRepository showRepository, ShowEpisodeRepository showEpisodeRepository) {
	    return (args) -> {
	      // save a few movies
	      Movie m1 = repository.save(new Movie("Very Scary Movie", "People die, when they are killed", "Horror", 1982));
	      Movie m2 = repository.save(new Movie("Very Scary Movie 2", "People die, when they are killed... again", "Horror", 1984));
	      Movie m3 = repository.save(new Movie("Very Funny Movie", "People die (of laughter)", "Comedy", 1982));
	      
	      // save a few reviews
	      reviewRepository.save(new Review(m1, "Pretty good but could use more blood"));
	      reviewRepository.save(new Review(m1, "People die, when they are killed... and I wish I was dead after watching this crap."));
	      reviewRepository.save(new Review(m3, "People die (of laughter) is worse than Martin Scorsese movies."));

	      // save a few shows
	      Show s1 = showRepository.save(new Show("Brooklyn Nine-Nine", "Jake Peralta does dumb stuff. Holt exists and is awesome. Rosa is my wife.", "Comedy", 2013));
	      Show s2 = showRepository.save(new Show("Dark", "Time is a literal mystery and German sounds really cool.", "Horror", 2017));

	      // save a few episodes
	      showEpisodeRepository.save(new ShowEpisode(s1, "Pilot", " The 99th precinct of the New York Police Department on Brooklyn receives a new Commanding Officer, Raymond Holt."));
	      showEpisodeRepository.save(new ShowEpisode(s1, "The Tagger", " Jake shows up late for roll call, so Holt assigns him to a graffiti case that Jake thinks is below him."));
	      
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
