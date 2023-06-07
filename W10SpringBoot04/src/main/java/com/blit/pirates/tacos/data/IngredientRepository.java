package com.blit.pirates.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.blit.pirates.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}
