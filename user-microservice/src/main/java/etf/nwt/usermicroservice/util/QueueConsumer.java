package etf.nwt.usermicroservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import etf.nwt.usermicroservice.repository.UserRepository;

@Component
@RabbitListener(queues="${queue.name}")
public class QueueConsumer implements MessageListener {

	@Autowired
	UserRepository userRepositoryImpl;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void receiveMessage(String message) {

		logger.info("Received (String) " + message);
		processMessage(message);
	}
	
    public void onMessage(Message message) {
        try {
            System.out.println("Consuming Message = " + message.getBody());
        } catch (Exception e) {

        }
    }

	public void receiveMessage(byte[] message) {
		
		String strMessage = new String(message);
		logger.info("Received (No String) " + strMessage);
		processMessage(strMessage);
	}

	private void processMessage(String message) {
		try {
			logger.info("Reached message processing.");
			logger.info("Received object: whatever");

		/*
		} catch (JsonParseException e) {

			logger.warn("Bad JSON in message: " + message);
		} catch (JsonMappingException e) {

			logger.warn("cannot map JSON to Object: " + message);
		*/
		} catch (Exception e) {

			logger.error(e.getMessage());
		}
	}
}

