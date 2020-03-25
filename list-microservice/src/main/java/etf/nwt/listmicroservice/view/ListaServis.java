package etf.nwt.listmicroservice.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
}