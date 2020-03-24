package etf.nwt.datamicroservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.slf4j.LoggerFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class DataServiceTests extends AbstractTest {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataMicroserviceApplicationTests.class);

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getMoviesTest() throws Exception {
		String uri = "/movies";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Content is: " + content);
	    //Movie[] movieList = super.mapFromJson(content, Movie[].class);
	    //assertTrue(movieList.length > 0);
	}
}
