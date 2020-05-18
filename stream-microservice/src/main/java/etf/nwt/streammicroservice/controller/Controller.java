package etf.nwt.streammicroservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import etf.nwt.streammicroservice.config.QueueProducer;
import etf.nwt.streammicroservice.model.Platform;
import etf.nwt.streammicroservice.model.Stream;
import etf.nwt.streammicroservice.service.StreamService;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class Controller {

    @Autowired
    private StreamService streamService;

    @Autowired
    private QueueProducer qp;

    @GetMapping("/streams")
    public ResponseEntity<List<Stream>> getAllStreams() {
        return ResponseEntity.status(HttpStatus.OK).body(streamService.getAllStreams());
    }

    @GetMapping("/streams/{itemId}")
    public ResponseEntity<?> getStreamsByItemId(@PathVariable("itemId") Long itemId) {
        if(itemId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "itemId mora biti cjelobrojni broj veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(streamService.getStreamsByItemId(itemId));
    }

    @GetMapping("/platforms")
    public ResponseEntity<?> getAllPlatforms() {
        return ResponseEntity.status(HttpStatus.OK).body(streamService.getAllPlatforms());
    }

    @PostMapping("/platforms/new")
    public ResponseEntity<?> addPlatform(@RequestBody Platform platform) {
        streamService.addPlatform(platform);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/platforms/{platformId}/new")
    public ResponseEntity<?> addStream(@PathVariable("platformId") Long platformId, @RequestBody Stream stream) {
        
        if(platformId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "platformId mora biti cjelobrojni broj veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }

        streamService.addStream(platformId, stream);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/platforms/delete/{platformId}")
    public ResponseEntity<?> deletePlatform(@PathVariable("platformId") Long platformId) {
        
        if(platformId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "platformId mora biti cjelobrojni broj veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }

        streamService.deletePlatform(platformId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/platforms/{platformId}/delete/{streamId}")
    public ResponseEntity<?> deleteStream(@PathVariable("platformId") Long platformId, @PathVariable("streamId") Long streamId) {
        
        if(platformId < 1 ||streamId < 1) {
            Map<String, String> error = new HashMap<String, String>();
            error.put("message", "platformId i streamId moraju biti cjelobrojni brojevi veci od 1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(error);
        }
        
        streamService.deleteStream(platformId, streamId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("stream/edit/{id}")
    public ResponseEntity<?> updateStream(@PathVariable("id") Long id, @RequestBody Stream s) {
        return streamService.updateStream(id, s);
    }

    @PutMapping("platform/edit/{id}")
    public ResponseEntity<?> updatePlatform(@PathVariable("id") Long id, @RequestBody Platform p) {
        return streamService.updatePlatform(id, p);
    }

    @GetMapping("stream/{id}")
    public ResponseEntity<?> getStreamById(@PathVariable("id") Long id) {
        try {
            qp.produce(streamService.getStreamById(id).getBody());
        } catch(Exception e) {
            
        }
        return streamService.getStreamById(id);
    }

    @GetMapping("platform/{id}")
    public ResponseEntity<?> getPlatformById(@PathVariable("id") Long id) {
        return streamService.getPlatformById(id);
    }
}