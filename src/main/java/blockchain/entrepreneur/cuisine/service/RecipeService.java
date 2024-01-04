package blockchain.entrepreneur.cuisine.service;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import blockchain.entrepreneur.cuisine.model.Date;
import blockchain.entrepreneur.cuisine.model.EcologicalBalance;
import blockchain.entrepreneur.cuisine.model.HeatBalance;
import blockchain.entrepreneur.cuisine.model.Ingredient;
import blockchain.entrepreneur.cuisine.model.IngredientDTO;
import blockchain.entrepreneur.cuisine.model.Recipe;
import blockchain.entrepreneur.cuisine.model.RestrictionRecipeIngredient;
import blockchain.entrepreneur.cuisine.repository.IngredientRepository;
import blockchain.entrepreneur.cuisine.repository.RecipeRepository;

@Service
public class RecipeService {
	
	private RecipeRepository recipeRepository;
	private IngredientRepository ingredientRepository;

	public RecipeService(RecipeRepository recipeRepository,IngredientRepository ingredientRepository) {
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
	}

	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}
	
	public Recipe getRandomRecipe() {
		List<Recipe> lRecipe = recipeRepository.findAll();
		int i = (int)(Math.random()*(lRecipe.size()));  
		return lRecipe.get(i);
	}

	
	public HashMap<String,List<Recipe>> getAllRecipesByAllCombinationIngredients(RestrictionRecipeIngredient restriction, SortType sort) {
		List<Recipe> allRecipe = recipeRepository.findAll();
		HashMap<String,List<Recipe>> result = new HashMap<String,List<Recipe>>();

		for (Recipe recipe : allRecipe) {
			List<Ingredient> l = recipe.ingredientInRecipe(restriction.getIngredients());
			if (l.size() != 0 && restriction.acceptRecipe(recipe, ingredientRepository)) {
				String key = returnKeyFromListIngredient(l);
				if (result.containsKey(key)) {
					result.get(key).add(recipe);
				}
				else {
					List<Recipe> lRecipe = new ArrayList<Recipe>();
					lRecipe.add(recipe);
					result.put(key,lRecipe);
				}
			}
			
		}
		
		for (String key : result.keySet()) {
			List<Recipe> l = result.get(key);
			l= sort.sortList(l);
	        result.put(key, l.subList(0,5)); //Renvoie seulement 5 recettes
	    }
		
		
		return result;
	}
	
	public List<Recipe> getAllRecipesByListIngredients(RestrictionRecipeIngredient restriction, SortType sort) {
		List<Recipe> allRecipe = recipeRepository.findAll();
		List<Recipe> recipesWithIngredients = new ArrayList<Recipe>();

		for (Recipe recipe : allRecipe) {
			if (recipe.ingredientInRecipe(restriction.getIngredients()).size()  == restriction.getIngredients().size()) recipesWithIngredients.add(recipe);
		}
		
		return sort.sortList(recipesWithIngredients);
	}
	
	
	public Optional<Recipe> getRecipeByName(String name) {
		return recipeRepository.findByName(name);
	}
	
	public Recipe addRecipe(Recipe recipe) {
		updateEcologicalBalance(recipe);
		updateHeatBalance(recipe);
		updateNameId(recipe);
		recipe.setUrlImage("https://www.produits-laitiers.com/app/uploads/2019/04/4176_boursin-836x470.jpg");
		recipe.setDate(new Date());
		
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json;
		try {
			json = ow.writeValueAsString(recipe);
			try (FileWriter file = new FileWriter("src/main/resources/recipe/json/" + recipe.getName() + ".json")) {
	            //We can write any JSONArray or JSONObject instance to the file
	            file.write(json); 
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		
		
		
		return recipeRepository.insert(recipe);
	}

	public void deleteRecipeByName(String name) {
		recipeRepository.deleteByName(name);
	}

	
	protected void updateEcologicalBalance(Recipe recipe) {
		EcologicalBalance ecologicalBalance = new EcologicalBalance();
		ecologicalBalance.ecologicalBalanceRecipe();
		float ponderation;
		float totQuantityPond = 0;
		boolean valide = true;
		for (IngredientDTO ingr : recipe.getIngredients()) {
			
			Optional<Ingredient> optIngredient = ingredientRepository.findByName(ingr.getName());
	    	if (optIngredient.isPresent()) {
	    		ponderation = ingr.getQuantityGramme(optIngredient.get());
				totQuantityPond += ponderation;
	    	}
	    	else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
			
		}
		
		float totQuantity = 0;
	    for (IngredientDTO ingr : recipe.getIngredients()) {
	    	Optional<Ingredient> optIngredient = ingredientRepository.findByName(ingr.getName());
	    	if (optIngredient.isPresent()) {
	    		ponderation = ingr.getQuantityGramme(optIngredient.get());
	    		EcologicalBalance ingrEcologicalBalance = optIngredient.get().getEcologicalBalance();
	    		if (ingrEcologicalBalance == null) {
	    			if (100*ponderation/totQuantityPond > 10) {
	    				valide = false;
	    			}
	    		}
	    		else {
	    			totQuantity += ponderation;
		    		ecologicalBalance.sum(ponderation, ingrEcologicalBalance);
	    		}
	    	}
	    	else {
	    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    	}
	  
	      }
	    ecologicalBalance.ponderation(totQuantity);
		recipe.setEcologicalBalance(ecologicalBalance);
		if (valide) {
			recipe.setEcoScore(ecologicalBalance.getEcoScore());
		}
		else {
			recipe.setEcoScore(-1);
			recipe.setEcologicalBalance(null);
		}
	}
	
	protected void updateNameId(Recipe recipe) {
		recipe.setNameId(recipe.getName().replace(' ', '-').replace('é', 'e').replace('è', 'e').toLowerCase());
	}
	
	protected void updateHeatBalance(Recipe recipe) {
		HeatBalance heatBalance = new HeatBalance();
		float ponderation;
		float totQuantity = 0;
	    for (IngredientDTO ingr : recipe.getIngredients()) {
	    	Optional<Ingredient> optIngredient = ingredientRepository.findByName(ingr.getName());
	    	if (optIngredient.isPresent()) {
	    		ponderation = ingr.getQuantityGramme(optIngredient.get());
		    	totQuantity += ponderation;
		    	HeatBalance ingrHeatBalance = optIngredient.get().getHeatBalance();
		    	heatBalance.sum(ponderation, ingrHeatBalance);
	    	}
	    	else {
	    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    	}
	  
	      }
	    heatBalance.ponderation(totQuantity);
	    recipe.setHeatBalance(heatBalance);
		recipe.setNutriScore(heatBalance.nutriScore());
	}






	public static String returnKeyFromListIngredient(List<Ingredient> lingredient) {
		Collections.sort(lingredient,Ingredient.ComparatorNom);
		String s = "";
		for (Ingredient ingr : lingredient) {
			s += ingr.getName();
			s += "/";
		}
		return s.substring(0, s.length()-1);
		
	}
}


