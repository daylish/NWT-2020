package etf.nwt.usermicroservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
	
	// these values are defined in application.properties
	@Value("${fanout.exchange}")
	private String fanoutExchange;
	
	@Value("${queue.name}")
	private String queueName;
	
	// queue will survive server restart
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
	
    @Bean
    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
