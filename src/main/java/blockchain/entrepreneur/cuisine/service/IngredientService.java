package blockchain.entrepreneur.cuisine.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import blockchain.entrepreneur.cuisine.model.CollectionRecipe;
import blockchain.entrepreneur.cuisine.model.Recipe;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import blockchain.entrepreneur.cuisine.model.Ingredient;
import blockchain.entrepreneur.cuisine.repository.IngredientRepository;

@Service
public class IngredientService {
	
	private IngredientRepository ingredientRepository;

	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public List<String> getAllIngredients() {
		List<String> ingredients = new ArrayList<>();
		File directory = new File("src/main/resources/jsonIngredient/");
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();

			if (files != null) {

				for (File file : files) {
					if (file.isFile() && file.getName().endsWith(".json")) {

							ingredients.add(file.getName().replace(".json", ""));

					}
				}
			}
		}
		return ingredients;

	}


}
