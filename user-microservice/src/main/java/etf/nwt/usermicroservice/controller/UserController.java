package etf.nwt.usermicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import etf.nwt.usermicroservice.exception.InvalidParametersException;
import etf.nwt.usermicroservice.exception.UserNotFoundException;
import etf.nwt.usermicroservice.model.User;
import etf.nwt.usermicroservice.repository.UserRepository;

@EnableJpaRepositories("etf.nwt.usermicroservice.repository")
@EntityScan("etf.nwt.usermicroservice.model")
@RestController
public class UserController {
	
	private UserRepository userRepository;
	
	UserController(UserRepository repository) {
		this.userRepository = repository;
	}
	
	// fetch all users
	@GetMapping("/users")
	@ResponseBody
	List<User> allUsers() {
		return userRepository.findAll();
	}
	
	// fetch user by id
	@GetMapping("/users/{id}")
	@ResponseBody
	User userById(@PathVariable Long id) throws UserNotFoundException {

	  return userRepository.findById(id)
			  .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, id));
	}
	
	// fetch users by location
	@GetMapping("/users/location")
	@ResponseBody
	List<User> usersByLocation(@RequestParam(name = "location", required = true) String location) {

		return userRepository.findByLocation(location);
	}
	
	// create new user
	// for some reason needs userLocation instead of location??? - nvm i figured it out
	@PostMapping("/users/new")
	@ResponseBody
	User newUser(@RequestBody User newUser) throws InvalidParametersException {
		try {
			if (newUser.getUserAboutMe() == null)
				newUser.setUserAboutMe("");
			userRepository.save(newUser);
		}
		catch (Exception e) {
			throw new InvalidParametersException("creating new user", HttpStatus.PRECONDITION_FAILED);
		}
		
		return newUser;
	}
	
	// edit existing user
	// this works too btw
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
	@ResponseBody
	User editUser(@RequestParam(name = "location", required = false, defaultValue = "") String location,
		@RequestParam(name = "desc", required = false, defaultValue = "") String userAboutMe,
		@PathVariable Long id) throws UserNotFoundException {

		return userRepository.findById(id)
			    .map(user -> {
			    	// location can't be empty 
			    	if (!location.equals(""))
			    		user.setUserLocation(location);
			    	user.setUserAboutMe(userAboutMe);
			        return userRepository.save(user);
			    }).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, id));
	}
	
	// delete existing user
	@DeleteMapping("/users/delete/{id}")
	void deleteUser(@PathVariable Long id) throws UserNotFoundException {
		try {
			userRepository.deleteById(id);
		}
		catch (Exception e) {
			throw new UserNotFoundException(HttpStatus.NOT_FOUND, id);
		}
	}
}