package etf.nwt.listmicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import etf.nwt.listmicroservice.model.Lista;

public interface ListaRepository extends CrudRepository<Lista, Long> {
    Lista findById(long id);
    List<Lista> findByUserID(long userID);
}