package blockchain.entrepreneur.cuisine.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import blockchain.entrepreneur.cuisine.model.CollectionRecipe;
import blockchain.entrepreneur.cuisine.model.EcologicalBalance;
import blockchain.entrepreneur.cuisine.model.HeatBalance;
import blockchain.entrepreneur.cuisine.model.Recipe;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import blockchain.entrepreneur.cuisine.model.Ingredient;
import blockchain.entrepreneur.cuisine.repository.IngredientRepository;

@Service
public class IngredientService {
	
	private IngredientRepository ingredientRepository;
	@Value("${chemin.ressource}")
	private String cheminRessource ;

	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public List<String> getAllIngredients() {
		List<String> ingredients = new ArrayList<>();
		File directory = new File(cheminRessource  + "jsonIngredient/");
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

	public Ingredient addIngredient(Ingredient ingredient){
		ObjectMapper objectMapper = new ObjectMapper();
		
		String nameNormalise = Normalizer.normalize(ingredient.getName(), Normalizer.Form.NFD);
		nameNormalise = nameNormalise.replaceAll("[^\\p{ASCII}]", "");
		nameNormalise = nameNormalise.replaceAll("[^a-zA-Z0-9]", "");
		nameNormalise = nameNormalise.replaceAll("\\s+", "");




		try (FileWriter fileWriter = new FileWriter(cheminRessource  + "jsonIngredient/" + nameNormalise + ".json")) {
			objectMapper.writeValue(fileWriter, ingredient);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return ingredient;
	}


}
