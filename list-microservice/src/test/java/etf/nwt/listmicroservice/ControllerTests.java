package etf.nwt.listmicroservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import etf.nwt.listmicroservice.controller.Controller;
import etf.nwt.listmicroservice.model.ListItem;
import etf.nwt.listmicroservice.model.Lista;
import etf.nwt.listmicroservice.repositories.ListItemRepository;
import etf.nwt.listmicroservice.repositories.ListaRepository;
import etf.nwt.listmicroservice.service.ListaServis;

@WebMvcTest(Controller.class)
class ControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ListaServis listaServis;

    @MockBean
    private ListaRepository listaRepository;

    @MockBean
    private ListItemRepository listItemRepository;

    @Test
    public void getAllListsTest() throws Exception {
        mvc.perform(get("/lists")).andExpect(status().isOk());

        verify(listaServis, times(1)).getAllLists();
    }

    @Test
    public void getListsTest() throws Exception {
        mvc.perform(get("/lists/1")).andExpect(status().isOk());
        verify(listaServis, times(1)).getLists(1L);

        mvc.perform(get("/lists/error")).andExpect(status().isBadRequest());
    }

    @Test
    public void getListItemsTest() throws Exception {
        mvc.perform(get("/listItems/1")).andExpect(status().isOk());
        verify(listaServis, times(1)).getListsItems(1L);

        mvc.perform(get("/listItems/error")).andExpect(status().isBadRequest());
    }

    @Test
    public void createListTest() throws Exception {
        Lista l = new Lista(1L, "test", new Date());
        l.setListID(1L);

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(l);

        mvc.perform(post("/lists/new").contentType(MediaType.APPLICATION_JSON).content(json)).
            andExpect(status().isCreated()); 
        verify(listaServis, times(1)).createList(l); 
    }

    @Test
    public void addListItemToListTest() throws Exception {
        ListItem li = new ListItem("test", new Date(), 1L);

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(li);

        mvc.perform(post("/lists/1/new").contentType(MediaType.APPLICATION_JSON).content(json)).
            andExpect(status().isCreated());
        verify(listaServis, times(1)).addListItemToList(1L, li); 

        mvc.perform(post("/lists/error/new").contentType(MediaType.APPLICATION_JSON).content(json)).
        andExpect(status().isBadRequest());
    }

    @Test
    public void deleteListTest() throws Exception {
        mvc.perform(delete("/lists/delete/1")).andExpect(status().isOk());
        verify(listaServis, times(1)).deleteList(1L);

        mvc.perform(delete("/lists/delete/error")).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteListItemFromListTest() throws Exception {
        mvc.perform(delete("/lists/1/delete/2")).andExpect(status().isOk());
        verify(listaServis, times(1)).deleteListItemFromList(1L, 2L);

        mvc.perform(delete("/lists/1/delete/error")).andExpect(status().isBadRequest());
    }
}