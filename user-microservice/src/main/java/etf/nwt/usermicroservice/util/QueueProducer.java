package etf.nwt.usermicroservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class QueueProducer {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	// again stored in application.properties
	@Value("${fanout.exchange}")
	private String fanoutExchange;

	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public QueueProducer(RabbitTemplate rabbitTemplate) {

		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	// this is the method we call somewhere in the module, when we want to store a message in queue
	public void produce(Object objectToSend) throws Exception {

		logger.info("Storing object...");
		try {
			rabbitTemplate.setExchange(fanoutExchange);
			rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(objectToSend));
		}
		catch (Exception e) {
			logger.info("Error: " + e.getMessage());
		}
		logger.info("Object " + objectToSend.toString() + " stored in queue sucessfully");
	}
}
