package com.blit.pirates.tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.blit.pirates.tacos.TacoOrder;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	
	// 2 HTTP Request Methods:  (1) GET and (2) POST
	
	
	// rendering the page initially
	@GetMapping("/current")
	public String orderForm() {
		// returns the name of the html page
		return "orderForm";
	}
	
	// #1. parameters of the method --> @Valid in front of TacoOrder order
	//						AND add "Errors errors"		
	// #2. add an if Statement to the content of the method
	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
		
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		log.info("Order submitted: {}", order);
		sessionStatus.setComplete();
		
		return "redirect:/";
	}

}
