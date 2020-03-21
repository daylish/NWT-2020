package etf.nwt.listmicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import etf.nwt.listmicroservice.Lista;

public interface ListaRepository extends CrudRepository<Lista, Long> {
    Lista findById(long id);
}