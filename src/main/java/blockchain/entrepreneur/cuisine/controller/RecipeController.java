package blockchain.entrepreneur.cuisine.controller;

import java.util.List;

import blockchain.entrepreneur.cuisine.model.RecipeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import blockchain.entrepreneur.cuisine.model.Recipe;
import blockchain.entrepreneur.cuisine.service.RecipeService;


@RestController
@CrossOrigin
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@PostMapping("/recipe")
	public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
		return new ResponseEntity<Recipe>(recipeService.addRecipe(recipe), HttpStatus.OK);
	}
	@GetMapping("/recipes")
	public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
		return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
	}

	@GetMapping("/recipes/{nameCollection}")
	public ResponseEntity<List<RecipeDTO>> getAllRecipesCollection(@PathVariable String nameCollection) {
		return new ResponseEntity<>(recipeService.getAllRecipesCollection(nameCollection), HttpStatus.OK);
	}

	@GetMapping("/recipe/{name}")
	public ResponseEntity<Recipe> getRecipe(@PathVariable String name) {
		return new ResponseEntity<>(recipeService.getRecipe(name), HttpStatus.OK);
	}

}
