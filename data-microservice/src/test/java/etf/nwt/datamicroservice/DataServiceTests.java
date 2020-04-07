package etf.nwt.datamicroservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.slf4j.LoggerFactory;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.netflix.discovery.EurekaClient;

import etf.nwt.datamicroservice.model.Movie;
import etf.nwt.datamicroservice.model.Show;

// it means the tests get called in alphabetical order 
@TestPropertySource(locations="classpath:communication.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataServiceTests extends AbstractTest {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(DataMicroserviceApplicationTests.class);
	
	@Autowired
	private EurekaClient eurekaClient;
	
    @Value("${service.user}")
    private String userServiceID;

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	// just the regular fetch alls
	@Test
	public void testA_getMoviesTest() throws Exception {
		String uri = "/movies";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch all movies status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch all movies content is: " + content);
	    //Movie[] movieList = super.mapFromJson(content, Movie[].class);
	    //assertTrue(movieList.length == 3);
	}
	
	@Test
	public void testB_getShowsTest() throws Exception {
		String uri = "/shows";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch all shows status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch all shows content is: " + content);
	    //Show[] showList = super.mapFromJson(content, Show[].class);
	    //assertTrue(showList.length == 2);
	}
	
	// fetching by id and failures
	@Test
	public void testC_getMovieByIdTest() throws Exception {
		String uri = "/movies/1";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch movie by id status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch movie by id content is: " + content);
	}
	
	@Test
	public void testD_getShowByIdTest() throws Exception {
		String uri = "/shows/7";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch show by id status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch show by id content is: " + content);
	}
	
	@Test
	public void testE_getMovieByIdTest_fails() throws Exception {
		String uri = "/movies/18";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch non-existent movie by id status is: " + status);
	    assertEquals(404, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch non-existent movie by id content is: " + content);
	}
	
	@Test
	public void testF_getShowByIdTest_fails() throws Exception {
		String uri = "/shows/17";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch non-existent show by id status is: " + status);
	    assertEquals(404, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch non-existent show by id content is: " + content);
	}
	
	// adding new movie/show and failures
	@Test
	public void testG_addMovieTest() throws Exception {
		String uri = "/movies/new";
		Movie movie = new Movie("Very Scary Movie 3", "People die, when they are killed, no matter what", "Horror", 1988);
	    String inputJson = super.mapToJson(movie);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	 	       .contentType(MediaType.APPLICATION_JSON_VALUE)
	 	       .content(inputJson)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Add new movie status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Add new movie content is: " + content);
	}
	
	@Test
	public void testH_addShowTest() throws Exception {
		String uri = "/shows/new";
		Show show = new Show("BoJack Horseman", "A humanoid horse, BoJack Horseman -- lost in a sea of self-loathing and booze -- decides it's time for a comeback.", "Comedy", 2014);
	    String inputJson = super.mapToJson(show);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	 	       .contentType(MediaType.APPLICATION_JSON_VALUE)
	 	       .content(inputJson)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Add new show status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Add new show content is: " + content);
	}
	
	@Test
	public void testI_addMovieTest_fails() throws Exception {
		String uri = "/movies/new";
		Movie movie = new Movie("Very Scary Movie 3", "People die, when they are killed, no matter what", "Horror", 1800);
	    String inputJson = super.mapToJson(movie);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	 	       .contentType(MediaType.APPLICATION_JSON_VALUE)
	 	       .content(inputJson)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Add new movie (fail) status is: " + status);
	    assertEquals(412, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Add new movie (fail) content is: " + content);
	}
	
	@Test
	public void testJ_addShowTest_fails() throws Exception {
		String uri = "/shows/new";
		Show show = new Show("BoJack Horseman", "A humanoid horse, BoJack Horseman -- lost in a sea of self-loathing and booze -- decides it's time for a comeback.", "Comedy", 1800);
	    String inputJson = super.mapToJson(show);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	 	       .contentType(MediaType.APPLICATION_JSON_VALUE)
	 	       .content(inputJson)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Add new show (fail) status is: " + status);
	    assertEquals(412, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Add new show (fail) content is: " + content);
	}
	
	// deleting and deleting with failures; no content here
	@Test
	public void testK_deleteMovieTest() throws Exception {
		String uri = "/movies/delete/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Delete movie status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Delete movie content is: " + content);
	}
	
	@Test
	public void testL_deleteShowTest() throws Exception {
		String uri = "/shows/delete/8";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Delete show status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Delete show content is: " + content);
	}
	
	@Test
	public void testM_deleteMovieTest_fails() throws Exception {
		String uri = "/movies/delete/16";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Delete non-existent movie status is: " + status);
	    assertEquals(404, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Delete non-existent movie content is: " + content);
	}
	
	@Test
	public void testN_deleteShowTest_fails() throws Exception {
		String uri = "/shows/delete/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
			       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Delete non-existent show status is: " + status);
	    assertEquals(404, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Delete non-existent show content is: " + content);
	}
	
	// next two don't have a failstate
	// get reviews for movie
	@Test
	public void testO_getReviewsForMovieTest() throws Exception {
		String uri = "/reviews/movie/3";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch all reviews for movie status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch all reviews for movie content is: " + content);
	}
	
	// get episodes for show
	@Test
	public void testP_getEpisodesForShowTest() throws Exception {
		String uri = "/shows/7/episodes";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch all episodes for show status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch all episodes for show content is: " + content);
	}
	
	// editing review text
	@Test
	public void testQ_editReviewForMovieTest() throws Exception {
		String uri = "/reviews/edit/6?text=Hahaha what a story Mark.";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Edit review for movie status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Edit review for movie content is: " + content);
	}
}
