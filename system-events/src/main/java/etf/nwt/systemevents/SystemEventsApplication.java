package etf.nwt.systemevents;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SystemEventsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SystemEventsApplication.class, args);
	}
}
