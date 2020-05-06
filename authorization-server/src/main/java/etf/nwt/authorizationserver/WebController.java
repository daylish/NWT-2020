package etf.nwt.authorizationserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	 @RequestMapping({"/","index"})
	 public String indexStuff() {
	    return "index stuff";
	 }

	 @RequestMapping("/webprivate")
	 public String privateStuff() {
	    return "private stuff";
	 }
	 
	 @RequestMapping("/webpublic")
	 public String loginpub() {
	    return "public";
	 }
	 
	 @RequestMapping("/webadmin")
	 public String admin() {
	    return "admin";
	 }
	 
	 @RequestMapping("/login")
	 public String login() {
	    return "login";
	 }
}
