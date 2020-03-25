package etf.nwt.listmicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import etf.nwt.listmicroservice.model.ListItem;
import etf.nwt.listmicroservice.model.Lista;
import etf.nwt.listmicroservice.service.ListaServis;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class Controller {

    @Autowired
    private ListaServis listaServis;

    @GetMapping("/lists")
    public List<Lista> getAllLists() {
        return listaServis.getAllLists();
    }

    @GetMapping("/lists/{userId}")
    public List<Lista> getLists(@PathVariable("userId") Long userId) {
        return listaServis.getLists(userId);
    }

    @PostMapping("/lists/new")
    public void createList(@RequestBody Lista lista) {
        listaServis.createList(lista);
    }
    
    @GetMapping("/listItems/{listId}")
    public List<ListItem> getListsItems(@PathVariable("listId") Long listId) {
        return listaServis.getListsItems(listId);
    }

    @PostMapping("lists/{listId}/new")
    public void addListItemToList(@PathVariable("listId") Long listId, @RequestBody ListItem li) {
        listaServis.addListItemToList(listId, li);
    }

    @DeleteMapping("lists/delete/{listId}")
    public void deleteList(@PathVariable("listId") Long listId) {
        listaServis.deleteList(listId);
    }

    @DeleteMapping("lists/{listId}/delete/{listItemId}")
    public void deleteListItemFromList(@PathVariable("listId") Long listId, @PathVariable("listItemId") Long listItemId) {
        listaServis.deleteListItemFromList(listId, listItemId);
    }
}