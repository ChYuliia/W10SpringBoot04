package com.blit.pirates.tacos.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.blit.pirates.tacos.data.IngredientRepository;

import jakarta.validation.Valid;

@Controller 					// identifies as a Controller class for Spring MVC
@RequestMapping("/design")		// identifies path in the HTTP request (domain/design
@SessionAttributes("tacoOrder") // identifies model should be maintained in session 
								// to be used for the entire order and entire session
public class DesignTacoController {
	

	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {		
		List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
				
		Type[] types = Ingredient.Type.values();		
		
		for(Type type : types) {			
			model.addAttribute(type.toString().toLowerCase(), 
					filterByType(ingredients, type));
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
		
		return "redirect:/orders/current";
	}
	
	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		String strType = type.toString();
		return ingredients.stream()
						.filter(x -> x.getType().equals(strType))
						.collect(Collectors.toList());
	}
}
