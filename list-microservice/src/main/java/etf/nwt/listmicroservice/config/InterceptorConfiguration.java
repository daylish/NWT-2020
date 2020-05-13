package etf.nwt.listmicroservice.config;

import etf.nwt.listmicroservice.interceptor.EventInterceptor;
import etf.nwt.systemevents.EventsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    EventInterceptor eventInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(eventInterceptor);
    }

    @Bean
    EventsServiceGrpc.EventsServiceBlockingStub eventServiceGrpc(
            @Value("${grpc.host}") String grpcHost,
            @Value("${grpc.port}") Integer grpcPort
    ) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();

        return EventsServiceGrpc.newBlockingStub(channel);
    }
}
