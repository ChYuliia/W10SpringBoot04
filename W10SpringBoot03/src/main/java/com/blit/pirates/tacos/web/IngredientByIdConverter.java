package com.blit.pirates.tacos.web;

import java.util.Map;
import java.util.HashMap;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blit.pirates.tacos.Ingredient;
import com.blit.pirates.tacos.Ingredient.Type;

	@Component
	public class IngredientByIdConverter implements Converter<String, Ingredient> {
		
		/*
		 * Taco Ingredients return from the website with only the KEY
		 * We have to convert that KEY into the OBJECT (which is the value of the map)
		 */
		
		// data member:  Collection
		private Map<String, Ingredient> ingredientMap = new HashMap<>();
		
		// default Constructor
		public IngredientByIdConverter() {
			// PUT ingredients into Map
			// Maps are Collections with KEY / VALUE pairs 
			// Here, we defined Map as <String, Ingredient>, SOOOOO
			// the KEY is the Ingredient ID String
			// the VALUE is the entire Ingredient object (ID,Name, Type)
			ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
			ingredientMap.put("JACK", new Ingredient("JACK", "Monterey Jack", Type.CHEESE));
			ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Type.SAUCE));
			ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		}

		@Override
		public Ingredient convert(String id) {
			// this will find the KEY in the Map 
			// and return the VALUE (which is Ingredient Object)
			return ingredientMap.get(id);
		}
}
