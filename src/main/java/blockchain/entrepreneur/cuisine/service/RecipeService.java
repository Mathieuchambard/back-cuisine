package blockchain.entrepreneur.cuisine.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


import blockchain.entrepreneur.cuisine.model.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import blockchain.entrepreneur.cuisine.repository.IngredientRepository;
import blockchain.entrepreneur.cuisine.repository.RecipeRepository;

@Service
public class RecipeService {

	private RecipeRepository recipeRepository;
	private IngredientRepository ingredientRepository;

	public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
	}

	public Recipe getRecipe(String name){
		File directory = new File("src/main/resources/recipe/json/" + name + ".json");
		Recipe recipe = null;

		try (FileReader reader = new FileReader(directory)) {
			BufferedReader fileReader = new BufferedReader(reader);
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			recipe = mapper.readValue(fileReader, Recipe.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return recipe;
	}
	public List<Pair> getAllRecipes() {
		List<Pair> recipeNames = new ArrayList<>();
		File directory = new File("src/main/resources/recipe/json/");
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();

			if (files != null) {
				JSONParser parser = new JSONParser();

				for (File file : files) {
					if (file.isFile() && file.getName().endsWith(".json")) {
						try (FileReader reader = new FileReader(file)) {
							Object obj = parser.parse(reader);
							JSONObject jsonObject = (JSONObject) obj;

							// Assuming the JSON structure contains a "name" attribute
							String name = (String) jsonObject.get("name");
							String nameId = (String) jsonObject.get("nameId");

							if (name != null) {
								Pair pair = new Pair(nameId,name);
								recipeNames.add(pair);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return recipeNames;
	}

	public List<Pair> getAllRecipesCollection(String name) {
		File directory = new File("src/main/resources/collection/" + name + ".json");
		CollectionRecipe collection = null;

		try (FileReader reader = new FileReader(directory)) {
			BufferedReader fileReader = new BufferedReader(reader);
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			collection = mapper.readValue(fileReader, CollectionRecipe.class);
		}catch (Exception e) {
			e.printStackTrace();
		}


		List<Pair> recipeNames = new ArrayList<>();
		File directoryRecipe = new File("src/main/resources/recipe/json/");
		if (directoryRecipe.exists() && directoryRecipe.isDirectory()) {
			File[] files = directoryRecipe.listFiles();

			if (files != null) {
				JSONParser parser = new JSONParser();

				for (File file : files) {
					if (file.isFile() && file.getName().endsWith(".json")) {
						try (FileReader reader = new FileReader(file)) {
							Object obj = parser.parse(reader);
							JSONObject jsonObject = (JSONObject) obj;

							// Assuming the JSON structure contains a "name" attribute
							String nameIdRecipe = (String) jsonObject.get("nameId");


							if (name != null && collection.inCollection(nameIdRecipe)) {
								String nameRecipe =(String) jsonObject.get("name");
								Pair pair = new Pair(nameIdRecipe,nameRecipe);
								recipeNames.add(pair);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return recipeNames;
	}
}

