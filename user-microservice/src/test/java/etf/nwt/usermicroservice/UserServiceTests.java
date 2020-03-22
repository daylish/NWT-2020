package etf.nwt.usermicroservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.slf4j.LoggerFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UserServiceTests extends AbstractTest {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserMicroserviceApplicationTests.class);

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getUsersTest() throws Exception {
		String uri = "/users";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Content is: " + content);
	    User[] userList = super.mapFromJson(content, User[].class);
	    assertTrue(userList.length >= 3);
	}
	
	@Test
	public void addUserTest() throws Exception {
	    String uri = "/users/new";
	    User user = new User("dgickons4", "tfatte6@cmu.edu", "Canada");
	    String inputJson = super.mapToJson(user);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	       .contentType(MediaType.APPLICATION_JSON_VALUE)
	       .content(inputJson)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Content is: " + content);
	    // assertEquals(content, "");
	}
}
