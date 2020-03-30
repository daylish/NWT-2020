package etf.nwt.streammicroservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import etf.nwt.streammicroservice.controller.Controller;
import etf.nwt.streammicroservice.model.Platform;
import etf.nwt.streammicroservice.model.Stream;
import etf.nwt.streammicroservice.repositories.PlatformRepository;
import etf.nwt.streammicroservice.repositories.StreamRepository;
import etf.nwt.streammicroservice.service.StreamService;

@WebMvcTest(Controller.class)
class ControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StreamService streamService;

    @MockBean
    private PlatformRepository platformRepository;

    @MockBean
    private StreamRepository streamRepository;

	@Test
	public void getAllStreamsTest() throws Exception {
        mvc.perform(get("/streams").accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
        verify(streamService, times(1)).getAllStreams();
    }
    
    @Test
    public void getAllPlatformsTest() throws Exception {
        mvc.perform(get("/platforms").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
        verify(streamService, times(1)).getAllPlatforms();
    }

    @Test
    public void getAllStreamsByItemIdTest() throws Exception {
        mvc.perform(get("/streams/12").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
        verify(streamService, times(1)).getStreamsByItemId(12L);

        mvc.perform(get("/streams/-1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
    }

    @Test
    public void addPlatformTest() throws Exception {

        Platform p = new Platform("test1", "test1", 0);
        
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(p);

        mvc.perform(post("/platforms/new").contentType(MediaType.APPLICATION_JSON).content(json)).
            andExpect(status().isCreated());
        verify(streamService, times(1)).addPlatform(p);
    }

    @Test
    public void addStreamTest() throws Exception {

        Stream s = new Stream("testt", 12L);

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(s);

        mvc.perform(post("/platforms/1/new").contentType(MediaType.APPLICATION_JSON).content(json)).
            andExpect(status().isCreated());
        verify(streamService, times(1)).addStream(1L, s);

        mvc.perform(post("/platforms/error/new").contentType(MediaType.APPLICATION_JSON).content(json)).
            andExpect(status().isBadRequest());
    }

    @Test
    public void deletePlatformTest() throws Exception {

        mvc.perform(delete("/platforms/delete/1")).andExpect(status().isOk());
        verify(streamService, times(1)).deletePlatform(1L);

        mvc.perform(delete("/platforms/delete/error")).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteStreamTest() throws Exception {

        mvc.perform(delete("/platforms/1/delete/2")).andExpect(status().isOk());
        verify(streamService, times(1)).deleteStream(1L, 2L);

        mvc.perform(delete("/platforms/error/delete/2")).andExpect(status().isBadRequest());
    }
}