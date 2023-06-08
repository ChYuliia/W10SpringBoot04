package com.blit.pirates.tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.blit.pirates.tacos.Ingredient;
import com.blit.pirates.tacos.Ingredient.Type;
import com.blit.pirates.tacos.Taco;
import com.blit.pirates.tacos.TacoOrder;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j							// lombok Slf4j --> logger object for Log4j
@Controller 					// identifies as a Controller class for Spring MVC
@RequestMapping("/design")		// identifies path in the HTTP request (domain/design
@SessionAttributes("tacoOrder") // identifies model should be maintained in session 
								// to be used for the entire order and entire session
public class DesignTacoController {

	/*
	 * 3 ModelAttribute methods:
	 * 		(1) adds Ingredient collections
	 * 		(2) adds Taco object "taco"
	 * 		(3) adds TacoOrder object "tacoOrder"
	 * 2 HTTP Request methods:
	 * 		(1) GET
	 * 		(2) POST
	 * 1 method to Filter Ingredients by Type
	 */
	
	/*
	 * MVC = MODEL view controller
	 * 
	 * FrontController passes an empty model to the DesignTacoController
	 * The DesignTacoController must fill the model with data
	 * 
	 * so @ModelAttribute annotations defines a method 
	 * that fill the model data
	 * 
	 * The ModelAttribute method receives the model from the FrontController
	 * the method will call a method model.addAttribute() to put information 
	 * into the model
	 * 
	 */
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		// #1. Create a collection of Ingredients Objects
		List<Ingredient> ingredients = Arrays.asList(
				// anonymous Ingredient objects
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				new Ingredient("TMTO", "Dices Tomatoes", Type.VEGGIES),
				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				new Ingredient("CHED", "Cheddar", Type.CHEESE),
		 		new Ingredient("JACK", "Monterey Jack", Type.CHEESE),
				new Ingredient("SLSA", "Salsa", Type.SAUCE),
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
				);
		
		// #2. Create an Array of unique Type values
		Type[] types = Ingredient.Type.values();
		
		// #3. Creating 5 mini Collections, 1 for each type
		for(Type type : types) {
			// for each type in the array, we will add the type
			// and the related ingredients of that type to the MODEL
			
			// filterByType() will make a smaller ArrayList based on Type
			// and then we will add it to the model
			// this means (because we are doing a for loop here),
			// we will give the model five small ArrayList of Ingredients
			// model.addAttribute(value)
			// model.addAttribute(name, value) <-- THIS IS THE ONE WE ARE USING
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}		
		
	}
	
	// put Taco Object named "taco" into the model
	@ModelAttribute(name="tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	// put TacoOrderObject named "tacoOrder" into the model
	@ModelAttribute(name="taco")
	public Taco taco() {
		return new Taco();
	}
	
	/*
	 * handles get request for "/ design" page
	 * returns String value for the name of the view
	 * and populates the model with an empty Taco
	 */
	
	/*
	 * HTTP is the request mechanism
	 * HTTP defines different kinds of requests
	 * Based on how I have built the page, I know
	 * there will be two kinds of HTTP requests: GET & POST
	 * SOOOO, I need to define two mapping methods, one for each
	 * 
	 */
	
	// method called when page first displays
	// that is why it has @GetMapping annotation
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	// #1. parameters of the method --> @Valid in front of Taco taco
	//						AND add "Errors errors"		
	// #2. add an if Statement to the content of the methid
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		
		if(errors.hasErrors()) {
			return "design";
		}
		
		tacoOrder.addTaco(taco);
		log.info("Processing taco: {}", taco); // log4j
		
		return "redirect:/orders/current";
	}
	
	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
						.filter(x -> x.getType().equals(type))
						.collect(Collectors.toList());
	}
}
