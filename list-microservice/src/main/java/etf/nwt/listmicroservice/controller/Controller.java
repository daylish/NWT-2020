package etf.nwt.listmicroservice.controller;

import etf.nwt.listmicroservice.model.ListItem;
import etf.nwt.listmicroservice.model.Lista;
import etf.nwt.listmicroservice.service.ListaServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class Controller {

    @Autowired
    private ListaServis listaServis;

    @GetMapping("/lists")
    public ResponseEntity<?> getAllLists() {
        return ResponseEntity.status(HttpStatus.OK).body(listaServis.getAllLists());
    }

    @GetMapping("/lists/{userId}")
    public ResponseEntity<?> getLists(@PathVariable("userId") Long userId) {
        
        if(userId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "userId mora biti cjelobrojni broj veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaServis.getLists(userId));
    }

    @PostMapping("/lists/new")
    public ResponseEntity<?> createList(@RequestBody Lista lista) {
        listaServis.createList(lista);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/listItems/{listId}")
    public ResponseEntity<?> getListsItems(@PathVariable("listId") Long listId) {

        if(listId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "listId mora biti cjelobrojni broj veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaServis.getListsItems(listId));
    }

    @PostMapping("lists/{listId}/new")
    public ResponseEntity<?> addListItemToList(@PathVariable("listId") Long listId, @RequestBody ListItem li) {

        if(listId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "listId mora biti cjelobrojni broj veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }

        listaServis.addListItemToList(listId, li);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("lists/delete/{listId}")
    public ResponseEntity<?> deleteList(@PathVariable("listId") Long listId) {

        if(listId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "listId mora biti cjelobrojni broj veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }

        listaServis.deleteList(listId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("lists/{listId}/delete/{listItemId}")
    public ResponseEntity<?> deleteListItemFromList(@PathVariable("listId") Long listId, @PathVariable("listItemId") Long listItemId) {
        
        if(listId < 1 || listItemId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "listId i listItemId moraju biti cjelobrojni brojevi veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }

        listaServis.deleteListItemFromList(listId, listItemId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("lists/edit/{listId}")
    public ResponseEntity<?> updateList(@PathVariable("listId") Long listId, @RequestBody Lista l) {
        return listaServis.updateList(listId, l);
    }

    @PutMapping("listItems/edit/{listItemId}")
    public ResponseEntity<?> updateListItem(@PathVariable("listItemId") Long id, @RequestBody ListItem li) {
        return listaServis.updateListItem(id, li);
    }

    @GetMapping("list/{listId}")
    public ResponseEntity<?> getListById(@PathVariable("listId") Long id) {
        return listaServis.getListById(id);
    }

    @GetMapping("listItem/{listItemId}")
    public ResponseEntity<?> getListItemById(@PathVariable("listItemId") Long id) {
        return listaServis.getListItemById(id);
    }
}
