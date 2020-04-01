package etf.nwt.streammicroservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import etf.nwt.streammicroservice.model.Platform;
import etf.nwt.streammicroservice.model.Stream;
import etf.nwt.streammicroservice.repositories.PlatformRepository;
import etf.nwt.streammicroservice.repositories.StreamRepository;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("etf.nwt.streammicroservice")
public class StreamingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingMicroserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StreamRepository streamRepository, PlatformRepository platformRepository) {
	    return (args) -> {
			Stream stream1 = new Stream("https://link1.com", 12L);
			Stream stream2 = new Stream("https://link2.com", 12L);
			Stream stream3 = new Stream("https://link3.com", 333L);
			Stream stream4 = new Stream("https://link4.com", 444L);

			Platform platform1 = new Platform("XYZ Streams", "https://xyz.streams.com", 9.99);
			Platform platform2 = new Platform("1movies", "https://1movies.to", 0);

			platform1.addStream(stream1);
			platform1.addStream(stream3);
			platform2.addStream(stream2);
			platform2.addStream(stream4);
			platformRepository.save(platform1);
			platformRepository.save(platform2);
	    };
	}
}
