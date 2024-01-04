package blockchain.entrepreneur.cuisine.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import blockchain.entrepreneur.cuisine.model.Ingredient;
import blockchain.entrepreneur.cuisine.repository.IngredientRepository;

@Service
public class IngredientService {
	
	private IngredientRepository ingredientRepository;

	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public List<Ingredient> getAllIngredients() {
		List<Ingredient> l = ingredientRepository.findAll();
		Collections.sort(l,Ingredient.ComparatorNom);
		return l;
	}
	
	public Optional<Ingredient> getIngredientByName(String name) {
		return ingredientRepository.findByName(name);
	}
	
	public Ingredient addIngredient(Ingredient ingredient) {
		return ingredientRepository.insert(ingredient);
	}

	public void deleteIngredientByName(String name) {
		ingredientRepository.deleteByName(name);
	}

}
