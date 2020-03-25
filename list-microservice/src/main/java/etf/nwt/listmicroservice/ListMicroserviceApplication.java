package etf.nwt.listmicroservice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import etf.nwt.listmicroservice.model.ListItem;
import etf.nwt.listmicroservice.model.Lista;
import etf.nwt.listmicroservice.repositories.ListItemRepository;
import etf.nwt.listmicroservice.repositories.ListaRepository;

@SpringBootApplication
public class ListMicroserviceApplication {

	private static final Logger log = LoggerFactory.getLogger(ListMicroserviceApplication.class);
	
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
			lista = listaRepository.findById(1L);
			log.info(listaRepository.findAll().get(0).toString());
	    };
	}
}
