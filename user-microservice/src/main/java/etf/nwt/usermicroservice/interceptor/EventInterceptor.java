package etf.nwt.usermicroservice.interceptor;

import com.google.protobuf.Timestamp;
import etf.nwt.systemevents.EventRequest;
import etf.nwt.systemevents.EventResponse;
import etf.nwt.systemevents.EventsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EventInterceptor extends HandlerInterceptorAdapter {
    private final EventsServiceGrpc.EventsServiceBlockingStub eventsService;

    private final Logger logger = LogManager.getLogger(getClass());

    public EventInterceptor(
            @Value("${grpc.host}") String grpcHost,
            @Value("${grpc.port}") Integer grpcPort
    ) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();

        eventsService = EventsServiceGrpc.newBlockingStub(channel);
    }
    
    // before sending request to controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception exception) {

        HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod().toUpperCase());
        EventRequest.ActionType actionType;
        switch (httpMethod) {
            case GET:
                actionType = EventRequest.ActionType.GET;
                break;
            case HEAD:
                actionType = EventRequest.ActionType.HEAD;
                break;
            case POST:
                actionType = EventRequest.ActionType.POST;
                break;
            case PUT:
                actionType = EventRequest.ActionType.PUT;
                break;
            case PATCH:
                actionType = EventRequest.ActionType.PATCH;
                break;
            case DELETE:
                actionType = EventRequest.ActionType.DELETE;
                break;
            case OPTIONS:
                actionType = EventRequest.ActionType.OPTIONS;
                break;
            case TRACE:
                actionType = EventRequest.ActionType.TRACE;
                break;
            default:
                logger.error("Unknown http method! " + httpMethod);
                actionType = null;
        }

        int status = response.getStatus();
        EventRequest.ResponseType responseType = status >= 200 && status < 400
                ? EventRequest.ResponseType.Success
                : EventRequest.ResponseType.Failure;

        String resourceName = request.getRequestURI();

        EventResponse res = eventsService.hello(
                EventRequest.newBuilder()
                        .setServiceName("user")
                        .setActionTimestamp(Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build())
                        .setUserId(0) 
                        .setActionType(actionType)
                        .setResourceName(resourceName)
                        .setResponseType(responseType)
                        .build()
        );
        
        System.out.println(actionType.toString() + " " + resourceName.toString() + " " + responseType.toString());
    }
}
