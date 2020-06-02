package etf.nwt.datamicroservice;

import static org.assertj.core.api.Assertions.assertThat;

import etf.nwt.datamicroservice.util.DataMicroserviceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import etf.nwt.datamicroservice.controller.MovieController;

@SpringBootTest(classes = DataMicroserviceApplication.class)
class DataMicroserviceApplicationTests {
	
	@Autowired
	private MovieController movieController;

	@Test
	void contextLoads() {
		assertThat(movieController).isNotNull();
	}

}
