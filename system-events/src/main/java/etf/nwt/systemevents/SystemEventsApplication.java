package etf.nwt.systemevents;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories
public class SystemEventsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SystemEventsApplication.class, args);
	}
}
