package etf.nwt.authorizationserver;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// this is just a test resource server to see if it works
@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	  @RequestMapping("/public")
	  public String publicStuff() {
	    return "Public stuff";
	  }
	  
	  @RequestMapping("/private")
	  public String privateStuff() {
	    return "Private stuff";
	  }
	  
	  @RequestMapping("/admin")
	  public String adminStuff() {
	    return "Admin stuff";
	  }
	  
	  @Override
		public void configure(HttpSecurity http) throws Exception {
		  
		  	// public allowed for anyone; role doesn't matter
			http.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/public").permitAll();
			// .anyRequest().authenticated();
			
			http.requestMatchers().antMatchers("/private")
			.and().authorizeRequests()
			.antMatchers("/private").access("hasRole('USER')")
			.and().requestMatchers().antMatchers("/admin")
			.and().authorizeRequests()
			.antMatchers("/admin").access("hasRole('ADMIN')");
		}   
}
