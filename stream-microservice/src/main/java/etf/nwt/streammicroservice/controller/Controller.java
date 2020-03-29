package etf.nwt.streammicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import etf.nwt.streammicroservice.model.Platform;
import etf.nwt.streammicroservice.model.Stream;
import etf.nwt.streammicroservice.service.StreamService;

@RestController
public class Controller {

    @Autowired
    private StreamService streamService;

    @GetMapping("/streams")
    public List<Stream> getAllStreams() {
        return streamService.getAllStreams();
    }

    @GetMapping("/streams/{itemId}")
    public List<Stream> getStreamsByItemId(@PathVariable("itemId") Long itemId) {
        return streamService.getStreamsByItemId(itemId);
    }

    @GetMapping("/platforms")
    public List<Platform> getAllPlatforms() {
        return streamService.getAllPlatforms();
    }

    @PostMapping("/platforms/new")
    public void addPlatform(@RequestBody Platform platform) {
        streamService.addPlatform(platform);
    }

    @PostMapping("/platforms/{platformId}/new")
    public void addStream(@PathVariable("platformId") Long platformId, @RequestBody Stream stream) {
        streamService.addStream(platformId, stream);
    }

    @DeleteMapping("/platforms/delete/{platformId}")
    public void deletePlatform(@PathVariable("platformId") Long platformId) {
        streamService.deletePlatform(platformId);
    }

    @DeleteMapping("/platforms/{platformId}/delete/{streamId}")
    public void deleteStream(@PathVariable("platformId") Long platformId, @PathVariable("streamId") Long streamId) {
        streamService.deleteStream(platformId, streamId);
    }
}