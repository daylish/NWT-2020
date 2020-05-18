package etf.nwt.streammicroservice.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {
    
    @Value("${queue.exchange}")
    private String exchange;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(Object obj) throws Exception {
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.convertAndSend(obj.toString().getBytes());
        System.out.println("Poruka je poslana");
    }
}