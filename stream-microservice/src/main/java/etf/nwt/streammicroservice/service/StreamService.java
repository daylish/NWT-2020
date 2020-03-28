package etf.nwt.streammicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
}