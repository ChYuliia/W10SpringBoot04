package com.blit.pirates.tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// Get Mapping is the annotation that pulls
	// information from the HTTP request and
	// helps our program navigate to the right page
	
	// This particular  @GetMapping tells me that this
	// HTTP request comes from the root domain
	// ("localhost:8080")
	@GetMapping("/")
	public String home() {
		return "home"; 
		// @Controller annotation is going to know
		// that this controller class method home()
		// will call the "home.html" view in templates
	}
}
