package blockchain.entrepreneur.cuisine.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import blockchain.entrepreneur.cuisine.model.Recipe;
import blockchain.entrepreneur.cuisine.model.RestrictionRecipeIngredient;
import blockchain.entrepreneur.cuisine.service.RecipeService;


@RestController
@CrossOrigin
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipes")
	public ResponseEntity<List<String>> getAllRecipes() {
		return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
	}
	
	@GetMapping("/recipe/random")
	public ResponseEntity<Recipe> getRandomRecipe() {
		return new ResponseEntity<>(recipeService.getRandomRecipe(), HttpStatus.OK);
	}
	
	@GetMapping("/recipe/ingredient/{sort}")
	public ResponseEntity<HashMap<String,List<Recipe>>> getAllRecipesByAllCombinationIngredients(@PathVariable SortType sort,@RequestBody RestrictionRecipeIngredient restriction) {
		return new ResponseEntity<>(recipeService.getAllRecipesByAllCombinationIngredients(restriction,sort), HttpStatus.OK);
	}
	@GetMapping("/recipe/ingredient/additional/{sort}")
	public ResponseEntity<List<Recipe>> getAllRecipesByListIngredients(@PathVariable SortType sort,@RequestBody RestrictionRecipeIngredient restriction) {
		return new ResponseEntity<>(recipeService.getAllRecipesByListIngredients(restriction,sort), HttpStatus.OK);
	}


	@PostMapping("/recipe")
	public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
		return new ResponseEntity<>(recipeService.addRecipe(recipe), HttpStatus.OK);
	}
	

}
