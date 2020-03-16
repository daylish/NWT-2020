package etf.nwt.usermicroservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.*;

@Entity
// no table annotation cause the table has the same name as the class
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userID;
	
	@NotBlank
	@NotNull(message = "Username cannot be null.")
	private String username;
	
	//@NotBlank
	//@NotNull(message = "Password cannot be null.")
	// commented out only for testing purposes :)
	private String password;
	
	@NotBlank
	@NotNull (message = "Email cannot be null.")
	@Email(message = "Email should be valid.")
    private String email;
	
	@NotBlank
	@NotNull(message = "Location cannot be null.")
	private String location;
	
	@Size(min = 0, max = 200, message 
		      = "About Me must be under 200 characters")
	private String userAboutMe;
	
	// not used anywhere
	protected User() {}

	public User(String username, String email, String location) {
	    this.username = username;
	    this.email = email;
	    this.location = location;
	}
	
	@Override
	public String toString() {
	    return String.format(
	        "User[id=%d, username='%s', email='%s', location='%s']",
	        userID, username, email, location);
	}
	
	public Long getUserID() {
	    return userID;
	}
	
	public String getEmail() {
	    return email;
	}
	
	public String getUserLocation() {
	    return location;
	}
	
	public String getUserAboutMe() {
	    return userAboutMe;
	}
}
