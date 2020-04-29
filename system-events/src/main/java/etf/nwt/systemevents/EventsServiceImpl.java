package etf.nwt.systemevents;

import etf.nwt.systemevents.db.EventRequest;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventsServiceImpl extends EventsServiceGrpc.EventsServiceImplBase {
    private Logger logger = LogManager.getLogger(getClass());

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

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
