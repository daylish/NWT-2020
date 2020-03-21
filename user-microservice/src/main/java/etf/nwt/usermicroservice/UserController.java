package etf.nwt.usermicroservice;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private UserRepository userRepository;
	
	UserController(UserRepository repository) {
		this.userRepository = repository;
	}
	
	// fetch all users
	@GetMapping("/users")
	List<User> allUsers() {
		return userRepository.findAll();
	}
	
	// fetch user by id
	@GetMapping("/users/{id}")
	User userById(@PathVariable Long id) {

	  return userRepository.findById(id)
			  .orElseThrow(() -> new UserNotFoundException(id));
	}
	
	// create new user
	// for some reason needs userLocation instead of location???
	@PostMapping("/users/new")
	User newUser(@RequestBody User newUser) {
		try {
			userRepository.save(newUser);
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new user");
		}
		
		return newUser;
	}
	
	// edit existing user
	/*
	@PutMapping("/users/edit/{id}")
	User editFullUser(@RequestBody User newUser, @PathVariable Long id) {

	  return userRepository.findById(id)
	    .map(user -> {
	    	user.setUsername(newUser.getUsername());
	    	user.setPassword(newUser.getPassword());
	    	user.setEmail(newUser.getEmail());
	    	user.setUserLocation(newUser.getUserLocation());
	    	user.setUserAboutMe(newUser.getUserAboutMe());
	        return userRepository.save(user);
	    })
	    .orElseGet(() -> {
	        newUser.setUserId(id);
	        return userRepository.save(newUser);
	    });
	}
	*/
	
	// edit only what makes sense
	@PutMapping("/users/edit/{id}")
	User editUser(@RequestParam(name = "location", required = false, defaultValue = "") String location,
		@RequestParam(name = "desc", required = false, defaultValue = "") String userAboutMe,
		@PathVariable Long id) {

		return userRepository.findById(id)
			    .map(user -> {
			    	// location can't be empty 
			    	if (!location.equals(""))
			    		user.setUserLocation(location);
			    	user.setUserAboutMe(userAboutMe);
			        return userRepository.save(user);
			    }).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	// delete existing user
	@DeleteMapping("/users/delete/{id}")
	void deleteEmployee(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
		}
		catch (Exception e) {
			throw new UserNotFoundException(id);
		}
	}
}
