package etf.nwt.usermicroservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.slf4j.LoggerFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import etf.nwt.usermicroservice.model.User;
@TestPropertySource(locations="classpath:communication.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTests extends AbstractTest {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserMicroserviceApplicationTests.class);
	
	@Autowired
	private EurekaClient eurekaClient;
	
    @Value("${service.data}")
    private String dataServiceID;

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	// for failures content should be the custom handler message in json format
	
	// adding a new user
	@Test
	public void testA_addUserTest() throws Exception {
	    String uri = "/users/new";
	    User user = new User("dgickons4", "tfatte6@cmu.edu", "Canada");
	    String inputJson = super.mapToJson(user);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	       .contentType(MediaType.APPLICATION_JSON_VALUE)
	       .content(inputJson)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Add new user status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Add new user content is: " + content);
	    // assertEquals(content, "");
	}
	
	// fetching all users
	@Test
	public void testB_getUsersTest() throws Exception {
		String uri = "/users";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch all users status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch all users content is: " + content);
	    User[] userList = super.mapFromJson(content, User[].class);
	    assertTrue(userList.length == 5);
	}
	
	// fetching user by specific id
	@Test
	public void testC_getUserByIdTest() throws Exception {
		String uri = "/users/1";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch user by id status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch user by id content is: " + content);
	}
	
	// fetching user by non-existent id
	@Test
	public void testD_getUserByIdTest_fails() throws Exception {
		String uri = "/users/10";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch user by non-existent id status is: " + status);
	    assertEquals(404, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch user by non-existent id content is: " + content);
	}
	
	// fetching users by location
	@Test
	public void testE_getUsersByLocationTest() throws Exception {
	    String uri = "/users/location?location=USA";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	 	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Fetch users by location status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Fetch users by location content is: " + content);
	    User[] userList = super.mapFromJson(content, User[].class);
	    // there's 2 to return so...
	    assertTrue(userList.length == 2);
	}
	
	// adding a new user but it fails
	@Test
	public void testF_addUserTest_fails() throws Exception {
	    String uri = "/users/new";
	    User user = new User("dgickons5", "notanemail", "Mexico");
	    String inputJson = super.mapToJson(user);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	       .contentType(MediaType.APPLICATION_JSON_VALUE)
	       .content(inputJson)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Add new user (fail) status is: " + status);
	    assertEquals(412, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Add new user (fail) content is: " + content);
	    // assertEquals(content, "");
	}
	
	// deleting existing user
	@Test
	public void testG_deleteUserTest() throws Exception {
	    String uri = "/users/delete/1";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
		 	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	    
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Delete user status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Delete user content is: " + content);
	}
	
	// deleting user by non-existent id
	// deleting existing user
	@Test
	public void testH_deleteUserTest_fails() throws Exception {
	    String uri = "/users/delete/10";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
		 	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	    
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Delete non-existent user status is: " + status);
	    assertEquals(404, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Delete non-existent user content is: " + content);
	}
	
	// editing non-existent user
	@Test
	public void testI_editUserTest_fails() throws Exception {
	    String uri = "/users/edit/10";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
		 	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	    
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Edit non-existent user status is: " + status);
	    assertEquals(404, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Edit non-existent user content is: " + content);
	}
	
	// editing existing user
	@Test
	public void testJ_editUserTest() throws Exception {
	    String uri = "/users/edit/2?desc=TEST DESCRIPTION";
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
		 	       .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	    
	    int status = mvcResult.getResponse().getStatus();
	    log.info("Edit user status is: " + status);
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    log.info("Edit user content is: " + content);
	}
	
	// same test for adding shows basically
	@Test
	public void testK_addMovieByUserTest() throws Exception {
		Application application = eurekaClient.getApplication(dataServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "movies/newp";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("title", "The Rock and a Hard Place");
        map.add("description", "The Rock is cool and also funny");
        map.add("genre", "Action");
        map.add("year", "2009");
        map.add("creatorID", "1");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<Object> response = testRestTemplate.postForEntity(url, request, Object.class);
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    //log.info("Add new movie by user content is: " + response.getBody());
	}
	
	@Test
	public void testL_getMoviesByUserTest() throws Exception {
		Application application = eurekaClient.getApplication(dataServiceID);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "movies/creator/1";
        
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<Object> response = testRestTemplate.getForEntity(url, Object.class);
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    log.info("Get movies for user content is: " + response.getBody());
	}
}
