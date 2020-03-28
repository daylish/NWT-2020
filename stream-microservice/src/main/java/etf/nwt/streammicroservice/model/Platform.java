package etf.nwt.streammicroservice.model;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Platform name cannot be null")
    @NotBlank(message = "Platform name cannot be blank")
    @Size(max = 50, message = "Platform name cannot be longer than 50 characters")
    private String name;

    @NotNull(message = "Platform must have a link")
    @NotBlank(message = "Platform link cannot be blank")
    @Size(min = 5, max = 50, message = "Platform link must be between 5 and 50 characters long")
    private String platformLink;

    @NotNull(message = "Platform price cannot be null")
    @Min(value = 0, message = "Price cannot be lower than 0")
    private double price;

    @JsonBackReference
    @OneToMany(mappedBy = "platforma", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Stream> streams = new ArrayList<Stream>();

    public void addStream(Stream stream) {
        this.streams.add(stream);
        stream.setPlatforma(this);
    }

    public void removeStream(Stream stream) {
        this.streams.remove(stream);
        stream.setPlatforma(null);
    }

    public List<Stream> getStreams() {
        return this.streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = new ArrayList<Stream>(streams);
    }

    public Platform() {
    }

    public Platform(String name, String platformLink, double price) {
        this.name = name;
        this.platformLink = platformLink;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatformLink() {
        return this.platformLink;
    }

    public void setPlatformLink(String platformLink) {
        this.platformLink = platformLink;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Platform)) {
            return false;
        }
        Platform platform = (Platform) o;
        return Objects.equals(id, platform.id) && Objects.equals(name, platform.name) && Objects.equals(platformLink, platform.platformLink) && price == platform.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", platformLink='" + getPlatformLink() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }

}