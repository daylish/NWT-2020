package etf.nwt.listmicroservice.util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver {
    @Autowired
    private JsonMapper mapper;

    private <T> T fromBinary(byte[] binary, Class<T> clazz) {
        try {
            return mapper.readValue(binary, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void handleMessage(byte[] message) throws MessagingException {

    }
}