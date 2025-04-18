package blockchain.entrepreneur.cuisine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import blockchain.entrepreneur.cuisine.model.Ingredient;
import blockchain.entrepreneur.cuisine.model.Recipe;
import blockchain.entrepreneur.cuisine.service.IngredientService;

@RestController
@CrossOrigin
@RequestMapping("/ingredients")
public class IngredientController {

	private IngredientService ingredientService;
	
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
		
	
	@GetMapping
	public ResponseEntity<List<String>> getAllIngredients() {
		return new ResponseEntity<List<String>>(ingredientService.getAllIngredients(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<Ingredient>(ingredientService.addIngredient(ingredient), HttpStatus.OK);
	}

	
	

}
 