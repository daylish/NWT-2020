package etf.nwt.streammicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import etf.nwt.streammicroservice.model.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    
}