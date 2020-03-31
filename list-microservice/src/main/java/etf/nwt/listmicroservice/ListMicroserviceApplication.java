package etf.nwt.listmicroservice;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import etf.nwt.listmicroservice.model.ListItem;
import etf.nwt.listmicroservice.model.Lista;
import etf.nwt.listmicroservice.repositories.ListItemRepository;
import etf.nwt.listmicroservice.repositories.ListaRepository;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("etf.nwt.listmicroservice")
public class ListMicroserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ListMicroserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ListaRepository listaRepository, ListItemRepository listItemRepository) {
	    return (args) -> {
			Lista lista = new Lista((long) 3, "Lista horora", new Date());
			ListItem li = new ListItem("Status", new Date(), (long) 13);
			lista.addListItem(li);
			li = new ListItem("Status novi", new Date(), (long) 15);
			lista.addListItem(li);
			listaRepository.save(lista);

			lista = new Lista((long) 11, "Lista komedija", new Date());
			li = new ListItem("Mr. Bean", new Date(), (long) 99);
			lista.addListItem(li);
			li = new ListItem("Funny movie", new Date(), (long) 88);
			lista.addListItem(li);
			listaRepository.save(lista);
	    };
	}
}
