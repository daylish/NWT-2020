package etf.nwt.listmicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import etf.nwt.listmicroservice.model.ListItem;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    ListItem findById(long id);
    List<ListItem> findAll();
}