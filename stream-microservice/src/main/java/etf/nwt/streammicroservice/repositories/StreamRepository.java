package etf.nwt.streammicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import etf.nwt.streammicroservice.model.Stream;

public interface StreamRepository extends JpaRepository<Stream, Long> {
    List<Stream> findByItemId(Long itemId);
}