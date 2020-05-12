package etf.nwt.datamicroservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import etf.nwt.datamicroservice.util.QueueConsumer;

public class RabbitConfiguration {
	
	// default listener method
	private static final String LISTENER_METHOD = "receiveMessage";
	 
	// same variables as in user rabbit config because this service will also be a producer
	// these values are defined in application.properties
	@Value("${fanout.exchange}")
	private String fanoutExchange;
	
	@Value("${queue.name}")
	private String queueName;
	
	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}
	
	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange(fanoutExchange);
	}
	
	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}
	
	// makes the service act as a consumer/listener
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
		MessageListenerAdapter listenerAdapter) {
		
		  SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		  container.setConnectionFactory(connectionFactory);
		  container.setQueueNames(queueName);
		  container.setMessageListener(listenerAdapter);
		  return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(QueueConsumer consumer) {

		return new MessageListenerAdapter(consumer, LISTENER_METHOD);
	}
}
