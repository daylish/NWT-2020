package etf.nwt.listmicroservice.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {
    @Bean
    JsonMapper mapper() {
        return JsonMapper.builder().build();
    }
}
