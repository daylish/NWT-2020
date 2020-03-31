package etf.nwt.listmicroservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import etf.nwt.listmicroservice.model.ListItem;
import etf.nwt.listmicroservice.model.Lista;
import etf.nwt.listmicroservice.repositories.ListItemRepository;
import etf.nwt.listmicroservice.repositories.ListaRepository;
import etf.nwt.listmicroservice.service.ListaServis;

@SpringBootTest
public class ListaServisTests {

    @Mock 
    private ListaRepository listaRepository;

    @Mock
    private ListItemRepository listItemRepository;

    @InjectMocks
    private ListaServis listaServis;

    @Test
    public void getAllListsTest() {
        Lista l1 = new Lista(1L, "test", new Date());
        Lista l2 = new Lista(1L, "test", new Date());

        List<Lista> expectedResult = new ArrayList<>();
        expectedResult.add(l1);
        expectedResult.add(l2);

        doReturn(expectedResult).when(listaRepository).findAll();

        List<Lista> actualResult = listaServis.getAllLists();

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(listaRepository, times(1)).findAll();
    }

    @Test
    public void getAllListItemsTest() {
        ListItem li1 = new ListItem("test", new Date(), 1L);
        ListItem li2 = new ListItem("test", new Date(), 1L);

        List<ListItem> expectedResult = new ArrayList<>();
        expectedResult.add(li1);
        expectedResult.add(li2);

        Lista l = new Lista(1L, "test", new Date());

        l.setItemsList(expectedResult);
        l.setListID(1L);

        doReturn(Optional.of(l)).when(listaRepository).findById(1L);

        List<ListItem> actualResult = listaServis.getListsItems(1L);

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(listaRepository, times(1)).findById(1L);
    }

    @Test
    public void getListsTest() {
        Lista l1 = new Lista(1L, "test", new Date());
        Lista l2 = new Lista(1L, "test", new Date());

        List<Lista> expectedResult = new ArrayList<>();
        expectedResult.add(l1);
        expectedResult.add(l2);

        doReturn(expectedResult).when(listaRepository).findByUserID(1L);

        List<Lista> actualResult = listaServis.getLists(1L);

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(listaRepository, times(1)).findByUserID(1L);
    }

    @Test
    public void createListTest() {
        Lista l = new Lista(1L, "test", new Date());

        listaServis.createList(l);
        listaServis.createList(l);

        verify(listaRepository, times(2)).save(l);
    }

    @Test
    public void addListItemToListTest() {
        ListItem li1 = new ListItem("test", new Date(), 1L);
        ListItem li2 = new ListItem("test", new Date(), 1L);

        Lista l = new Lista(1L, "test", new Date());
        l.setListID(1L);

        listaServis.createList(l);
        listaServis.addListItemToList(1L, li1);
        listaServis.addListItemToList(1L, li2);

        verify(listaRepository, times(1)).save(l);
        l.addListItem(li1);
        verify(listaRepository, times(1)).save(l);
        l.addListItem(li2);
        verify(listaRepository, times(1)).save(l);
    }

    @Test
    public void deleteListTest() {
        listaServis.deleteList(1L);
        listaServis.deleteList(2L);

        verify(listaRepository, times(1)).deleteById(1L);
        verify(listaRepository, times(1)).deleteById(2L);
    }

    @Test
    public void deleteListItemFromListTest() {
        ListItem li1 = new ListItem("test", new Date(), 1L);
        ListItem li2 = new ListItem("test", new Date(), 1L);
        li1.setListItemID(2L);
        li2.setListItemID(3L);

        Lista l = new Lista(1L, "test", new Date());
        l.setListID(1L);
        
        l.addListItem(li1);
        l.addListItem(li2);

        doReturn(Optional.of(l)).when(listaRepository).findById(1L);
        doReturn(Optional.of(li1)).when(listItemRepository).findById(2L);
        doReturn(Optional.of(li2)).when(listItemRepository).findById(3L);

        assertThat(l.getItemsList().size()).isEqualTo(2);
        listaServis.deleteListItemFromList(1L, 2L);
        assertThat(l.getItemsList().size()).isEqualTo(1);
        listaServis.deleteListItemFromList(1L, 3L);
        assertThat(l.getItemsList().size()).isEqualTo(0);
        verify(listaRepository, times(2)).save(l);
    }
}