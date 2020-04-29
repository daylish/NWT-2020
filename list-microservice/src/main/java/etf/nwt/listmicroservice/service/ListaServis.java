package etf.nwt.listmicroservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import etf.nwt.systemevents.EventRequest;
import etf.nwt.systemevents.EventResponse;
import etf.nwt.systemevents.EventsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import etf.nwt.listmicroservice.model.ListItem;
import etf.nwt.listmicroservice.model.Lista;
import etf.nwt.listmicroservice.repositories.ListItemRepository;
import etf.nwt.listmicroservice.repositories.ListaRepository;

@Service
public class ListaServis {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ListItemRepository listItemRepository;

    private EventsServiceGrpc.EventsServiceBlockingStub eventsService;

    public List<Lista> getAllLists() {
        return listaRepository.findAll();
    }

    public List<Lista> getLists(Long userId) {
        return listaRepository.findByUserID(userId);
    }

    public void createList(Lista lista) {
        listaRepository.save(lista);
    }

    public List<ListItem> getListsItems(Long listId) {
        Optional<Lista> l = listaRepository.findById(listId);
        if(l.isPresent())
            return l.get().getItemsList();
        return new ArrayList<ListItem>();
    }

    public void addListItemToList(Long listId, ListItem li) {
        Optional<Lista> tmp = listaRepository.findById(listId);
        if(tmp.isPresent()) {
            Lista l = tmp.get();
            l.addListItem(li);
            listaRepository.save(l);
        }
    }

    public void deleteList(Long listId) {
        listaRepository.deleteById(listId);
    }

    public void deleteListItemFromList(Long listId, Long listItemId) {
        Optional<Lista> l = listaRepository.findById(listId);
        Optional<ListItem> li = listItemRepository.findById(listItemId);
        if(l.isPresent() && li.isPresent()) {
            l.get().removeListItem(li.get());
            listaRepository.save(l.get());
        }
    }

    public ResponseEntity<?> updateList(Long listId, Lista l) {
        Optional<Lista> oldLista = listaRepository.findById(listId);
        if(oldLista.isPresent()) {
            Lista temp = oldLista.get();
            temp.setDate(l.getDate());
            temp.setTitle(l.getTitle());
            //nema smisla mijenjati ID i vlasnika liste
            listaRepository.save(temp);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Ne postoji lista sa tim ID-jem");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    public ResponseEntity<?> updateListItem(Long id, ListItem li) {
        Optional<ListItem> oldli = listItemRepository.findById(id);
        if(oldli.isPresent()) {
            ListItem temp = oldli.get();
            temp.setDateCreated(li.getDateCreated());
            temp.setListItemStatus(li.getListItemStatus());
            temp.setItemId(li.getItemId());
            //nema smisla mijenjati ID i listu kojoj pripada
            listItemRepository.save(temp);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Ne postoji list item sa tim ID-jem");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    public ResponseEntity<?> getListById(Long id) {
        Optional<Lista> l = listaRepository.findById(id);
        if(l.isPresent()) {
            Lista temp = l.get();
            return ResponseEntity.status(HttpStatus.OK).body(temp);
        } else {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Ne postoji lista sa tim ID-jem");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    public ResponseEntity<?> getListItemById(Long id) {
        Optional<ListItem> li = listItemRepository.findById(id);
        if(li.isPresent()) {
            ListItem temp = li.get();
            return ResponseEntity.status(HttpStatus.OK).body(temp);
        } else {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Ne postoji list item sa tim ID-jem");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}