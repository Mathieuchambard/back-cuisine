package blockchain.entrepreneur.cuisine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import blockchain.entrepreneur.cuisine.model.Ingredient;
import blockchain.entrepreneur.cuisine.service.IngredientService;

@RestController
@CrossOrigin
public class IngredientController {

	private IngredientService ingredientService;
	
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
		
	
	@GetMapping("/ingredients")
	public ResponseEntity<List<Ingredient>> getAllIngredients() {
		return new ResponseEntity<List<Ingredient>>(ingredientService.getAllIngredients(), HttpStatus.OK);
	}
	
	@PostMapping("/ingredient")
	public ResponseEntity<HttpStatus> addIngredient(@RequestBody Ingredient ingr) {
		ingredientService.addIngredient(ingr);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
 