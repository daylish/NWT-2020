package etf.nwt.systemevents;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Events") 
public class EventRequest {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long eventID;

	@NotBlank
	@NotNull(message = "Object type cannot be null.")
	private String objectType;
	
	@NotBlank
	@NotNull(message = "Request type cannot be null.")
	private String requestType;
	
	@NotBlank
	@NotNull(message = "Response type cannot be null.")
	private String responseType;
	
	@NotBlank
	@NotNull(message = "Timestamp cannot be null.")
	private String eventTimestamp;
	
	protected EventRequest() {}

	public EventRequest(String objectType, String requestType, String responseType, String eventTimestamp) {
	    this.objectType = objectType;
	    this.requestType = requestType;
	    this.responseType = responseType;
	    this.eventTimestamp = eventTimestamp;
	}
	
	public String getObjectType() {
		return objectType;
	}
	
	public String getRequestType() {
		return requestType;
	}
	
	public String getResponseType() {
		return responseType;
	}
	
	public String getEventTimestamp() {
		return eventTimestamp;
	}
}
