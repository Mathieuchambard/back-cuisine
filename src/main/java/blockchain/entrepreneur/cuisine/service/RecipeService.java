package blockchain.entrepreneur.cuisine.service;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

import blockchain.entrepreneur.cuisine.model.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.apache.commons.text.similarity.LevenshteinDistance;


import blockchain.entrepreneur.cuisine.repository.IngredientRepository;
import blockchain.entrepreneur.cuisine.repository.RecipeRepository;


@Service
public class RecipeService {

	@Value("${chemin.ressource}")
	private String cheminRessource ;
	private RecipeRepository recipeRepository;
	private IngredientRepository ingredientRepository;

	public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
	}


	public Recipe deleteRecipe(String nameId){
		String cheminFichier = cheminRessource  + "recipe/json/"+ nameId+".json";
		File fichierASupprimer = new File(cheminFichier);
		fichierASupprimer.delete();
		return null;
	}

	public Recipe modifyRecipe(String nameId,Recipe recipe){
		ObjectMapper objectMapper = new ObjectMapper();
		recipe.updateDate();

		HeatBalance heatBalance = new HeatBalance();
		heatBalance.updateHeatBalance(recipe.getIngredients(), recipe.getServes());
		recipe.setHeatBalance(heatBalance);
		recipe.setNutriscore(heatBalance.nutriScore());

		EcologicalBalance ecologicalBalance = new EcologicalBalance();
		double ecoScore = ecologicalBalance.updateEcologicalBalance(recipe.getIngredients());
		recipe.setEcoScore(ecoScore);
		recipe.setEcologicalBalance(ecologicalBalance);

		recipe.setNameId(nameId);


		try (FileWriter fileWriter = new FileWriter(cheminRessource  + "recipe/json/" + nameId + ".json")) {
			objectMapper.writeValue(fileWriter, recipe);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return recipe;


	}

	public Recipe addRecipe(Recipe recipe){
		ObjectMapper objectMapper = new ObjectMapper();
		recipe.updateDate();

		HeatBalance heatBalance = new HeatBalance();
		heatBalance.updateHeatBalance(recipe.getIngredients(),recipe.getServes());
		recipe.setHeatBalance(heatBalance);
		recipe.setNutriscore(heatBalance.nutriScore());

		EcologicalBalance ecologicalBalance = new EcologicalBalance();
		double ecoScore = ecologicalBalance.updateEcologicalBalance(recipe.getIngredients());
		recipe.setEcoScore(ecoScore);
		recipe.setEcologicalBalance(ecologicalBalance);




		File dossier = new File(cheminRessource  + "recipe/json/");
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


		try (FileWriter fileWriter = new FileWriter(cheminRessource  + "recipe/json/" + nameId + ".json")) {
			objectMapper.writeValue(fileWriter, recipe);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return recipe;
	}

	public Recipe getRecipe(String name) {
		File directory = new File(cheminRessource  + "recipe/json/" + name + ".json");
		Recipe recipe = null;

		try (FileReader reader = new FileReader(directory)) {
			BufferedReader fileReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(directory), StandardCharsets.UTF_8));			
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			recipe = mapper.readValue(fileReader, Recipe.class);
			for (IngredientDTO ingr : recipe.getIngredients()) {

				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(new File(cheminRessource  + "jsonIngredient/" + ingr.getName() + ".json"));

				String imageString = jsonNode.get("image").asText();
				ingr.setEncodeImage(imageString);
			}



		}catch (Exception e) {
			e.printStackTrace();
		}
		return recipe;
	}

	public String removeAccents(String input) {
        if (input == null) {
            return null;
        }
        // Normalisation Unicode (NFD = Normalization Form Decomposed)
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Supprimer les caractères diacritiques (accents)
        return normalized.replaceAll("\\p{M}", "");
    }

	public double getLevenshteinDistance(String str1, String str2) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        int distance = levenshtein.apply(str1, str2);
        int maxLength = Math.max(str1.length(), str2.length());
        // Normaliser en fonction de la longueur maximale
        return 1.0 - (double) distance / maxLength;
    }

	public static int getLongestCommonSubstringLength(String str1, String str2) {
		
		int m = str1.length();
		int n = str2.length();
		int[][] dp = new int[m + 1][n + 1]; // Matrice pour stocker les longueurs
		int maxLength = 0;
	
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					maxLength = Math.max(maxLength, dp[i][j]);
				} else {
					dp[i][j] = 0; // Réinitialisation en cas de rupture de séquence
				}
			}
		}
		return maxLength;
	}
	
	
	

	public boolean isSimilar(String name, String nameSearch) {

		double threshold = 0.3;
		name = this.removeAccents(name).toLowerCase();
		nameSearch = this.removeAccents(nameSearch).toLowerCase();



		double longest = getLongestCommonSubstringLength(name, nameSearch);
		double levenshtein = getLevenshteinDistance(name, nameSearch);

		return ((longest >= 3) || (levenshtein > threshold));

	}
	public Double getSimilarity(String name, String nameSearch) {

		name = this.removeAccents(name).toLowerCase();
		nameSearch = this.removeAccents(nameSearch).toLowerCase();



		double longest = getLongestCommonSubstringLength(name, nameSearch);
		int maxLength = Math.max(name.length(), nameSearch.length());
		double levenshtein = getLevenshteinDistance(name, nameSearch);

		return longest/maxLength + levenshtein;

	}


	public List<RecipeDTO> getRecipesSearch(String nameSearch) {



		List<RecipeDTO> recipeNames = new ArrayList<>();
		List<Double> recipeScore = new ArrayList<>();
		File directory = new File(cheminRessource  + "recipe/json/");



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



							float ecoScore = ((Double) jsonObject.get("ecoScore")).floatValue();
							
							JSONArray encodeImageObject = (JSONArray) jsonObject.get("encodeImage");
							List<String> encodeImage = new ArrayList<>();

							JSONArray ingredientsArray = (JSONArray) jsonObject.get("ingredients");
							String ingredientNames = getIngredients(ingredientsArray);


							

							if ((name != null ) && (isSimilar(name,nameSearch))) {
								RecipeDTO recipeDTO = new RecipeDTO(nameId,name,timeRecipe,NutriScore.fromValue(nutriscoreString),Difficulty.fromValue(difficultyString),ecoScore,encodeImage,ingredientNames);
								recipeNames.add(recipeDTO);;
								recipeScore.add(this.getSimilarity(nameId, nameSearch));
							}
			
							

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return this.sortRecipesByScore(recipeNames, recipeScore);
	}


	public List<RecipeDTO> sortRecipesByScore(List<RecipeDTO> recipeNames, List<Double> recipeScore) {
        // Créer une liste d'index pour trier
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < recipeNames.size(); i++) {
            indices.add(i);
        }

        // Trier les index en fonction des scores
        indices.sort(Comparator.comparing(recipeScore::get).reversed()); // Trier en ordre décroissant

        // Réordonner les noms en fonction des index triés
        List<RecipeDTO> sortedNames = new ArrayList<>();
        for (int index : indices) {
            sortedNames.add(recipeNames.get(index));
        }

        return sortedNames;
    }


	public List<RecipeDTO> getAllRecipes() {



		List<RecipeDTO> recipeNames = new ArrayList<>();
		File directory = new File(cheminRessource  + "recipe/json/");



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



							float ecoScore = ((Double) jsonObject.get("ecoScore")).floatValue();



							
							JSONArray encodeImageObject = (JSONArray) jsonObject.get("encodeImage");
							List<String> encodeImage = new ArrayList<>();


                            for (Object o : encodeImageObject) {
                                encodeImage.add((String) o);
                            }

							JSONArray ingredientsArray = (JSONArray) jsonObject.get("ingredients");
							String ingredientNames = getIngredients(ingredientsArray);
		


							if (name != null) {
								RecipeDTO recipeDTO = new RecipeDTO(nameId,name,timeRecipe,NutriScore.fromValue(nutriscoreString),Difficulty.fromValue(difficultyString),ecoScore,encodeImage,ingredientNames);
								recipeNames.add(recipeDTO);;
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
	public String getIngredients(JSONArray ingredientsArray){

		// Concaténation des noms des ingrédients
		String ingredientNames = (String) ingredientsArray.stream()
			.map(ing -> {
				JSONObject ingredient = (JSONObject) ing;
				String n = (String) ingredient.get("name");
				String u = (String) ingredient.get("unit"); 
				int nu = ((Double) ingredient.get("quantity")).intValue(); 
				if (u.equals("NONE")) {
					return n ; // Concatène le nom et l'unité
				}else if (u.equals("NUMBER")) {
					return nu  + " " + n ; // Concatène le nom et l'unité
				}
				else{
					return nu + " " + u.toLowerCase() + " " + n ; // Concatène le nom et l'unité
				}
			
			})
			.collect(Collectors.joining(", "));
		
		return ingredientNames;
	}

	public List<RecipeDTO> getAllRecipesCollection(String name) {
		File directory = new File(cheminRessource  + "collection/" + name + ".json");
		CollectionRecipe collection = null;

		try (FileReader reader = new FileReader(directory)) {
			BufferedReader fileReader = new BufferedReader(reader);
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			collection = mapper.readValue(fileReader, CollectionRecipe.class);
		}catch (Exception e) {
			e.printStackTrace();
		}


		List<RecipeDTO> recipeNames = new ArrayList<>();
		File directoryRecipe = new File(cheminRessource  + "recipe/json/");
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
								float ecoScore = ((Double) jsonObject.get("ecoScore")).floatValue();


								String difficultyString = (String) jsonObject.get("difficulty");
								JSONArray encodeImageObject = (JSONArray) jsonObject.get("encodeImage");
								List<String> encodeImage = new ArrayList<>();

								for (Object o : encodeImageObject) {
									encodeImage.add((String) o);
								}

								JSONArray ingredientsArray = (JSONArray) jsonObject.get("ingredients");
								String ingredientNames = getIngredients(ingredientsArray);
								
							

								RecipeDTO recipeDTO = new RecipeDTO(nameIdRecipe,nameRecipe,timeRecipe,NutriScore.fromValue(nutriscoreString),Difficulty.fromValue(difficultyString),ecoScore,encodeImage,ingredientNames);
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

