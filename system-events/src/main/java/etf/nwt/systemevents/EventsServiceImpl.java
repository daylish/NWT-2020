package etf.nwt.systemevents;

import etf.nwt.systemevents.db.EventRequest;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

public class EventsServiceImpl extends EventsServiceGrpc.EventsServiceImplBase {

    @Override
    public void hello(
            etf.nwt.systemevents.EventRequest request, StreamObserver<EventResponse> responseObserver
    ) {
        String eventResponseText = new StringBuilder()
                .append("Object type: ")
                .append(request.getObjectType())
                .append(", Request type: ")
                .append(request.getRequestType())
                .append(", Response type: ")
                .append(request.getResponseType())
                .append(", Timestamp: ")
                .append(request.getRequestTimestamp())
                .toString();

        EventResponse response = EventResponse.newBuilder()
                .setEventResponseText(eventResponseText)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
