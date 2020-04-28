package etf.nwt.systemevents;

import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

public class EventsServiceImpl implements BindableService {
	
	// the method that gets all the relevant information 
	public void hello(
      EventRequest request, StreamObserver<EventResponse> responseObserver) {
 
        String eventResponseText = new StringBuilder()
          .append("Object type: ")
          .append(request.getObjectType())
          .append(", Request type: ")
          .append(request.getRequestType())
          .append(", Response type: ")
          .append(request.getResponseType())
          .append(", Timestamp: ")
          .append(request.getEventTimestamp())
          .toString();
 
        EventResponse response = new EventResponse(eventResponseText);
 
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

	@Override
	public ServerServiceDefinition bindService() {
		// TODO Auto-generated method stub
		return null;
	}}
