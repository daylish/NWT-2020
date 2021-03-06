package etf.nwt.usermicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

// @Entity is a JPA annotation to make this object ready for storage in a JPA-based data store.
@Entity
// no table annotation cause the table has the same name as the class
// turns out it can't be
@Table(name="Users") 
public class User {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userID;
	
	@NotBlank
	@NotNull(message = "Username cannot be null.")
	// username should be unique
	@Column(unique = true)
	private String username;
	
	@NotBlank
	@NotNull(message = "Password cannot be null.")
	@Size(min = 6, max = 50, message 
    	= "Password must be between 6 and 50 characters")
	// commented out only for testing purposes :)
	private String password;
	
	@NotBlank
	@NotNull (message = "Email cannot be null.")
	@Email(message = "Email should be valid.")
	// email should be unique
	@Column(unique = true)
    private String email;

	@NotNull (message = "Authorities cannot be null.")
	@Column()
	private String roles = "user";
	
	@NotBlank
	@NotNull(message = "Location cannot be null.")
	private String location;
	
	@Size(min = 0, max = 200, message 
		      = "About Me must be under 200 characters")
	@NotNull
	private String userAboutMe;
	
	// not used anywhere
	protected User() {}

	public User(String username, String email, String location) {
		this.username = username;
		this.password = "passwordFillerBecausePasswordsAreComplicated";
		this.email = email;
		this.location = location;
		this.userAboutMe = "";
	}

	public User(String username, String email, String location, String roles) {
		this.username = username;
		this.password = "passwordFillerBecausePasswordsAreComplicated";
		this.email = email;
		this.location = location;
		this.userAboutMe = "";
		this.roles = roles;
	}
	
	@Override
	public String toString() {
	    return String.format(
	        "User[id=%d, username='%s', email='%s', location='%s', about='%s']",
	        userID, username, email, location, userAboutMe);
	}
	
	public Long getUserId() {
	    return userID;
	}
	
	public void setUserId(Long id) {
	    this.userID = id;
	}
	
	public String getUsername() {
	    return username;
	}
	
	public void setUsername(String username) {
	    this.username = username;
	}
	
	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}
	
	public String getEmail() {
	    return email;
	}
	
	public void setEmail(String email) {
	    this.email = email;
	}
	
	@JsonProperty("location")
	public String getUserLocation() {
	    return location;
	}
	
	public void setUserLocation(String location) {
	    this.location = location;
	}
	
	public String getUserAboutMe() {
	    return userAboutMe;
	}
	
	public void setUserAboutMe(String aboutMe) {
	    this.userAboutMe = aboutMe;
	}
}
