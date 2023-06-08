package com.blit.pirates.tacos;

import lombok.Data;

@Data
public class Ingredient {

	private final String id;
	private final String name;
	private final Type type;
	
	// creating a datatype called Type that
	// can only hold the values Wrap...
	// enum datatypes are controlled specified values
	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
