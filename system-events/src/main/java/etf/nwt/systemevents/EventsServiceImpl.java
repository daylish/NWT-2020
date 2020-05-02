package etf.nwt.systemevents;

import etf.nwt.systemevents.db.EventRequestRepository;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;


@Service
public class EventsServiceImpl extends EventsServiceGrpc.EventsServiceImplBase {
    private Logger logger = LogManager.getLogger(getClass());

    private final EventRequestRepository eventRequestRepository;

    public EventsServiceImpl(
            EventRequestRepository eventRequestRepository,
            @Value("${grpc.port}") Integer port
    ) throws IOException {
        this.eventRequestRepository = eventRequestRepository;

        Server server = ServerBuilder.forPort(port)
                .addService(this)
                .build();

        logger.info("Starting server...");
        server.start();
        logger.info("Server started!");
    }


    @Override
    public void hello(
            etf.nwt.systemevents.EventRequest request, StreamObserver<EventResponse> responseObserver
    ) {
        logger.info(String.format(
                "New event received: %s %s %s %s %s %s",
                request.getActionTimestamp(),
                request.getServiceName(),
                request.getUserId(),
                request.getActionType(),
                request.getResourceName(),
                request.getResponseType()
        ));

        EventResponse response = EventResponse.newBuilder()
                .setEventResponseText("OkiDoki") // todo nigdje nije definisan odgovor RPCa?
                .build();

        etf.nwt.systemevents.db.entity.EventRequest dbEntity = new etf.nwt.systemevents.db.entity.EventRequest(
                null,
                Timestamp.from(Instant.ofEpochSecond(request.getActionTimestamp().getSeconds())),
                request.getServiceName(),
                request.getUserId(),
                Optional.ofNullable(request.getActionType()).map(Enum::name).orElse(null),
                request.getResourceName(),
                Optional.ofNullable(request.getResponseType()).map(Enum::name).orElse(null)
        );

        eventRequestRepository.save(dbEntity);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
