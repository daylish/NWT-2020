package etf.nwt.streammicroservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import etf.nwt.streammicroservice.controller.Controller;

@SpringBootTest
class StreamMicroserviceApplicationTests {

	@Autowired
	private Controller controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
}
