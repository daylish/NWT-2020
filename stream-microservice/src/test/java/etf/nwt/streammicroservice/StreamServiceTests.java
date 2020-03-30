package etf.nwt.streammicroservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import etf.nwt.streammicroservice.model.Platform;
import etf.nwt.streammicroservice.model.Stream;
import etf.nwt.streammicroservice.repositories.PlatformRepository;
import etf.nwt.streammicroservice.repositories.StreamRepository;
import etf.nwt.streammicroservice.service.StreamService;

@SpringBootTest
public class StreamServiceTests {

    @Mock
    private PlatformRepository platformRepository;

    @Mock
    private StreamRepository streamRepository;

    @InjectMocks
    private StreamService streamService;

    @Test
    public void getAllPlatformsTest() {
        Platform p1 = new Platform("TestPlatform1", "TestLink1", 0);
        Platform p2 = new Platform("TestPlatform2", "TestLink2", 9.99);
        List<Platform> expectedPlatforms = new ArrayList<Platform>();
        expectedPlatforms.add(p1);
        expectedPlatforms.add(p2);

        doReturn(expectedPlatforms).when(platformRepository).findAll();
        
        List<Platform> actualPlatforms = streamService.getAllPlatforms();

        assertThat(actualPlatforms).isEqualTo(expectedPlatforms);
        verify(platformRepository, times(1)).findAll();
    }

    @Test
    public void getAllStreamsTest() {
        Stream s1 = new Stream("Link1", 12L);
        Stream s2 = new Stream("Link2", 2L);
        List<Stream> expectedStreams = new ArrayList<Stream>();
        expectedStreams.add(s1);
        expectedStreams.add(s2);

        doReturn(expectedStreams).when(streamRepository).findAll();

        List<Stream> actualStreams = streamService.getAllStreams();

        assertThat(actualStreams).isEqualTo(expectedStreams);
        verify(streamRepository, times(1)).findAll();
    }

    @Test
    public void getStreamsByItemIdTest() {
        Stream s1 = new Stream("Link1", 2L);
        Stream s2 = new Stream("Link2", 2L);
        List<Stream> expectedStreams = new ArrayList<>();
        expectedStreams.add(s1);
        expectedStreams.add(s2);

        doReturn(expectedStreams).when(streamRepository).findByItemId(2L);

        List<Stream> actualStreams = streamService.getStreamsByItemId(2L);

        assertThat(actualStreams).isEqualTo(expectedStreams);
        verify(streamRepository, times(1)).findByItemId(2L);
    }

    @Test
    public void addPlatformTest() {
        Platform p = new Platform("TestName", "TestLInk", 1);

        streamService.addPlatform(p);
        streamService.addPlatform(p);

        verify(platformRepository, times(2)).save(p);
    }

    @Test
    public void addStreamTest() {
        Stream s1 = new Stream("TestLink", 1L);
        Stream s2 = new Stream("TestLink2", 2L);
        Platform p = new Platform("TestIme", "TestLinkPlatforme", 0);
        p.setId(1L);

        streamService.addPlatform(p);
        streamService.addStream(1L, s1);
        streamService.addStream(1L, s2);

        verify(platformRepository, times(1)).save(p);
        p.addStream(s1);
        verify(platformRepository, times(1)).save(p);
        p.addStream(s2);
        verify(platformRepository, times(1)).save(p);
    }

    @Test
    public void deletePlatformTest() {
        
        streamService.deletePlatform(1L);

        verify(platformRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteStreamTest() {
        
        Platform p = new Platform("Testt", "Testt", 1);
        Stream s1 = new Stream("Testt", 12L);
        Stream s2 = new Stream("Testt", 12L);
        p.setId(1L);
        p.addStream(s1);
        p.addStream(s2);

        doReturn(Optional.of(p)).when(platformRepository).findById(1L);
        doReturn(Optional.of(s1)).when(streamRepository).findById(2L);
        doReturn(Optional.of(s2)).when(streamRepository).findById(3L);
        
        assertThat(p.getStreams().size()).isEqualTo(2);
        streamService.deleteStream(1L, 2L);
        assertThat(p.getStreams().size()).isEqualTo(1);
        streamService.deleteStream(1L, 3L);

        verify(platformRepository, times(2)).save(p);
        assertThat(p.getStreams().size()).isEqualTo(0);
    }
}