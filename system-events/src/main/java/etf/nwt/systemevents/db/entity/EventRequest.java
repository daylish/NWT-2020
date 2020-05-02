package etf.nwt.systemevents.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="events")
public class EventRequest {

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long eventID;

	@NotNull(message = "actionTimestamp type cannot be null.")
	private Timestamp actionTimestamp;

	@NotBlank
	@NotNull(message = "serviceName type cannot be null.")
	private String serviceName;


	@NotNull(message = "userId type cannot be null.")
	private Long userId;

	@NotBlank
	@NotNull(message = "actionType type cannot be null.")
	private String actionType;

	@NotBlank
	@NotNull(message = "resourceName type cannot be null.")
	private String resourceName;

	@NotBlank
	@NotNull(message = "responseType type cannot be null.")
	private String responseType;


	protected EventRequest() {}

	public EventRequest(
			@NotNull Long eventID,
			@NotBlank @NotNull(message = "actionTimestamp type cannot be null.") Timestamp actionTimestamp,
			@NotBlank @NotNull(message = "serviceName type cannot be null.") String serviceName,
			@NotBlank @NotNull(message = "userId type cannot be null.") Long userId,
			@NotBlank @NotNull(message = "actionType type cannot be null.") String actionType,
			@NotBlank @NotNull(message = "resourceName type cannot be null.") String resourceName,
			@NotBlank @NotNull(message = "responseType type cannot be null.") String responseType
	) {
		this.eventID = eventID;
		this.actionTimestamp = actionTimestamp;
		this.serviceName = serviceName;
		this.userId = userId;
		this.actionType = actionType;
		this.resourceName = resourceName;
		this.responseType = responseType;
	}

	public Long getEventID() {
		return eventID;
	}

	public EventRequest setEventID(Long eventID) {
		this.eventID = eventID;
		return this;
	}

	public Timestamp getActionTimestamp() {
		return actionTimestamp;
	}

	public EventRequest setActionTimestamp(Timestamp actionTimestamp) {
		this.actionTimestamp = actionTimestamp;
		return this;
	}

	public String getServiceName() {
		return serviceName;
	}

	public EventRequest setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public EventRequest setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getActionType() {
		return actionType;
	}

	public EventRequest setActionType(String actionType) {
		this.actionType = actionType;
		return this;
	}

	public String getResourceName() {
		return resourceName;
	}

	public EventRequest setResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}

	public String getResponseType() {
		return responseType;
	}

	public EventRequest setResponseType(String responseType) {
		this.responseType = responseType;
		return this;
	}
}
