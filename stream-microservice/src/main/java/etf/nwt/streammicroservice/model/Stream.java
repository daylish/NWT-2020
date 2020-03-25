package etf.nwt.streammicroservice.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Stream {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long streamId;

    @NotNull(message = "Streaming link cannot be null")
    @NotBlank(message = "Streaming link cannot be blank")
    @Size(min = 5, max = 100, message = "Streaming link must be between 5 and 100 characters long")
    private String link;

    @Min(value = 1, message = "Items ID cannot be smaller than 0")
    @NotNull(message = "Item ID cannot be null")
    private Long itemId;

    @JsonManagedReference
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "platformaId")
    private Platform platforma;
    

    public Long getStreamId() {
        return this.streamId;
    }

    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Platform getPlatforma() {
        return this.platforma;
    }

    public void setPlatforma(Platform platforma) {
        this.platforma = platforma;
    }

    public Stream() {}

    public Stream(Long streamId, String link, Long itemId) {
        this.streamId = streamId;
        this.link = link;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Stream)) {
            return false;
        }
        Stream stream = (Stream) o;
        return Objects.equals(streamId, stream.streamId) && Objects.equals(link, stream.link) && Objects.equals(itemId, stream.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamId);
    }

    @Override
    public String toString() {
        return "{" +
            " streamId='" + getStreamId() + "'" +
            ", link='" + getLink() + "'" +
            ", itemId='" + getItemId() + "'" +
            ", platforma='" + getPlatforma().getName() + "'" +
            "}";
    }

}