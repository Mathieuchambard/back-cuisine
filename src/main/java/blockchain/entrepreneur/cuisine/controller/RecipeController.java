package blockchain.entrepreneur.cuisine.controller;

import java.util.List;

import blockchain.entrepreneur.cuisine.model.RecipeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import blockchain.entrepreneur.cuisine.model.Recipe;
import blockchain.entrepreneur.cuisine.service.RecipeService;


@RestController
@CrossOrigin
@RequestMapping("/recipe")
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@PostMapping
	public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
		return new ResponseEntity<Recipe>(recipeService.addRecipe(recipe), HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
		return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
	}

	@GetMapping("/search/{nameSearch}")
	public ResponseEntity<List<RecipeDTO>> getRecipesSearch(@PathVariable String nameSearch) {
		return new ResponseEntity<>(recipeService.getRecipesSearch(nameSearch), HttpStatus.OK);
	}


	@GetMapping("/collection/{nameCollection}")
	public ResponseEntity<List<RecipeDTO>> getAllRecipesCollection(@PathVariable String nameCollection) {
		return new ResponseEntity<>(recipeService.getAllRecipesCollection(nameCollection), HttpStatus.OK);
	}

	@GetMapping("/{name}")
	public ResponseEntity<Recipe> getRecipe(@PathVariable String name) {
		return new ResponseEntity<>(recipeService.getRecipe(name), HttpStatus.OK);
	}

	@DeleteMapping("/{nameId}")
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable String nameId) {
		return new ResponseEntity<Recipe>(recipeService.deleteRecipe(nameId), HttpStatus.OK);
	}

	@PutMapping("/{nameId}")
	public ResponseEntity<Recipe> modifyRecipe(@RequestBody Recipe recipe,@PathVariable String nameId) {
		return new ResponseEntity<Recipe>(recipeService.modifyRecipe(nameId,recipe), HttpStatus.OK);
	}

}
