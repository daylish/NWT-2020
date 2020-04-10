package etf.nwt.streammicroservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import etf.nwt.streammicroservice.model.Platform;
import etf.nwt.streammicroservice.model.Stream;
import etf.nwt.streammicroservice.repositories.PlatformRepository;
import etf.nwt.streammicroservice.repositories.StreamRepository;

@Service
public class StreamService {

    @Autowired
    private StreamRepository streamRepository;

    @Autowired
    private PlatformRepository platformRepository;

    public List<Stream> getAllStreams() {
        return streamRepository.findAll();
    }

    public List<Stream> getStreamsByItemId(Long itemId) {
        return streamRepository.findByItemId(itemId);
    }

    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    public void addPlatform(Platform platform) {
        platformRepository.save(platform);
    }

    public void addStream(Long platformId, Stream stream) {
        Optional<Platform> tmp = platformRepository.findById(platformId);
        if(tmp.isPresent()) {
            Platform platform = tmp.get();
            platform.addStream(stream);
            platformRepository.save(platform);
        }
    }

    public void deletePlatform(Long platformId) {
        platformRepository.deleteById(platformId);
    }

    public void deleteStream(Long platformId, Long streamId) {
        Optional<Platform> platform = platformRepository.findById(platformId);
        Optional<Stream> stream = streamRepository.findById(streamId);
        if(platform.isPresent() && stream.isPresent()) {
            platform.get().removeStream(stream.get());
            platformRepository.save(platform.get());
        }
    }

    public ResponseEntity<?> updateStream(Long id, Stream s) {
        Optional<Stream> oldStream = streamRepository.findById(id);
        if(oldStream.isPresent()) {
            Stream temp = oldStream.get();
            temp.setItemId(s.getItemId());
            temp.setLink(s.getLink());
            //nema smisla mijenjati ID i platformu
            streamRepository.save(temp);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Ne postoji stream sa tim ID-jem");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    public ResponseEntity<?> updatePlatform(Long id, Platform p) {
        Optional<Platform> oldPlatform = platformRepository.findById(id);
        if(oldPlatform.isPresent()) {
            Platform temp = oldPlatform.get();
            temp.setName(p.getName());
            temp.setPlatformLink(p.getPlatformLink());
            temp.setPrice(p.getPrice());
            //nema smisla mijenjati ID
            platformRepository.save(temp);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Ne postoji platforma sa tim ID-jem");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    public ResponseEntity<?> getStreamById(Long id) {
        Optional<Stream> s = streamRepository.findById(id);
        if(s.isPresent()) {
            Stream temp = s.get();
            return ResponseEntity.status(HttpStatus.OK).body(temp);
        } else {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Ne postoji stream sa tim ID-jem");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    public ResponseEntity<?> getPlatformById(Long id) {
        Optional<Platform> p = platformRepository.findById(id);
        if(p.isPresent()) {
            Platform temp = p.get();
            return ResponseEntity.status(HttpStatus.OK).body(temp);
        } else {
            Map<String, String> body = new HashMap<String, String>();
            body.put("message", "Ne postoji platforma sa tim ID-jem");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}