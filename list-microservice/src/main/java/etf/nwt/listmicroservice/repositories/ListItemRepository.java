package etf.nwt.listmicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import etf.nwt.listmicroservice.model.ListItem;

public interface ListItemRepository extends CrudRepository<ListItem, Long> {
    ListItem findById(long id);
}