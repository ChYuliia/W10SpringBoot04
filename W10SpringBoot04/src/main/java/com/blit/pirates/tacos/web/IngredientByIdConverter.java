package com.blit.pirates.tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.blit.pirates.tacos.Ingredient;
import com.blit.pirates.tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
	
		/*
		 * Taco Ingredients return from the website with only the KEY
		 * We have to convert that KEY into the OBJECT (which is the value of the map)
		 */
			
		private IngredientRepository ingredientRepo;
		
		@Autowired
		public IngredientByIdConverter(IngredientRepository ingredientRepo) {			
			this.ingredientRepo = ingredientRepo;
		}

		@Override
		public Ingredient convert(String id) {			
			return ingredientRepo.findById(id).orElse(null);
		}
}
