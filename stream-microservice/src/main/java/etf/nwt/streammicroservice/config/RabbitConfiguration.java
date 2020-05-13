package etf.nwt.streammicroservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    
    @Value("${queue.name}")
    private String name;

    @Value("${queue.exchange}")
    private String queueExchange;

    @Bean
    Queue queue() {
        return new Queue(name, true);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(queueExchange);
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}