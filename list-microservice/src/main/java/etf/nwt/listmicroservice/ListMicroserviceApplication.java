package etf.nwt.listmicroservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import etf.nwt.listmicroservice.repositories.ListaRepository;

@SpringBootApplication
public class ListMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListMicroserviceApplication.class, args);
	}
}
