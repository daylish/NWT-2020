package etf.nwt.listmicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import etf.nwt.listmicroservice.model.Lista;

public interface ListaRepository extends JpaRepository<Lista, Long> {
    List<Lista> findByUserID(long userID);
}