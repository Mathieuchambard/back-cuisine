package blockchain.entrepreneur.cuisine.service;


import java.io.*;
import java.nio.file.Files;
import java.text.Normalizer;
import java.util.*;



import blockchain.entrepreneur.cuisine.model.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
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

	public Recipe addRecipe(Recipe recipe){
		ObjectMapper objectMapper = new ObjectMapper();
		recipe.updateDate();

		HeatBalance heatBalance = new HeatBalance();
		heatBalance.updateHeatBalance(recipe.getIngredients());

		recipe.setHeatBalance(heatBalance);
		recipe.setNutriscore(heatBalance.nutriScore());



		File dossier = new File("src/main/resources/recipe/json/");
		List<String> listNameId = new ArrayList<>();

		File[] files = dossier.listFiles();
		for (File file : files) {
			listNameId.add(file.getName());
		}



		String nameNormalise = Normalizer.normalize(recipe.getName(), Normalizer.Form.NFD);
		nameNormalise = nameNormalise.replaceAll("[^\\p{ASCII}]", "");
		nameNormalise = nameNormalise.replaceAll("[^a-zA-Z0-9]", "");
		nameNormalise = nameNormalise.replaceAll("\\s+", "");

		Random random = new Random();

		String nameId = nameNormalise + random.nextInt(100000);

		while (listNameId.contains(nameId+".json")){
			nameId = nameNormalise + random.nextInt(100000);
		}

		recipe.setNameId(nameId);


		try (FileWriter fileWriter = new FileWriter("src/main/resources/recipe/json/" + nameId + ".json")) {
			objectMapper.writeValue(fileWriter, recipe);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return recipe;
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
	public List<RecipeDTO> getAllRecipes() {
		List<RecipeDTO> recipeNames = new ArrayList<>();
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
							JSONObject timeRecipeObject = (JSONObject) jsonObject.get("timeRecipe");
							TimeRecipe timeRecipe = new TimeRecipe(((Long) timeRecipeObject.get("cooking")).intValue(),
									((Long) timeRecipeObject.get("preparation")).intValue(),
									((Long) timeRecipeObject.get("rest")).intValue());



							String nutriscoreString = (String) jsonObject.get("nutriscore");


							String difficultyString = (String) jsonObject.get("difficulty");

							if (name != null) {
								RecipeDTO recipeDTO = new RecipeDTO(nameId,name,timeRecipe,NutriScore.fromValue(nutriscoreString),Difficulty.fromValue(difficultyString));
								recipeNames.add(recipeDTO);
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

	public List<RecipeDTO> getAllRecipesCollection(String name) {
		File directory = new File("src/main/resources/collection/" + name + ".json");
		CollectionRecipe collection = null;

		try (FileReader reader = new FileReader(directory)) {
			BufferedReader fileReader = new BufferedReader(reader);
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			collection = mapper.readValue(fileReader, CollectionRecipe.class);
		}catch (Exception e) {
			e.printStackTrace();
		}


		List<RecipeDTO> recipeNames = new ArrayList<>();
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


								JSONObject timeRecipeObject = (JSONObject) jsonObject.get("timeRecipe");
								TimeRecipe timeRecipe = new TimeRecipe(((Long) timeRecipeObject.get("cooking")).intValue(),
										((Long) timeRecipeObject.get("preparation")).intValue(),
										((Long) timeRecipeObject.get("rest")).intValue());



								String nutriscoreString = (String) jsonObject.get("nutriscore");


								String difficultyString = (String) jsonObject.get("difficulty");

								RecipeDTO recipeDTO = new RecipeDTO(nameIdRecipe,nameRecipe,timeRecipe,NutriScore.fromValue(nutriscoreString),Difficulty.fromValue(difficultyString));
								recipeNames.add(recipeDTO);
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

