package blockchain.entrepreneur.cuisine.model.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import blockchain.entrepreneur.cuisine.model.Diet;
import blockchain.entrepreneur.cuisine.model.Ingredient;
import blockchain.entrepreneur.cuisine.model.IngredientDTO;
import blockchain.entrepreneur.cuisine.model.Recipe;
import blockchain.entrepreneur.cuisine.repository.IngredientRepository;

public class ParametersMenu {
	
	private boolean noTime;
	private List<Diet> listDiet;
	private List<Ingredient> forbiddenIngredient;
	private int ecoParameters; // De 0 à 1, 100-Ecoscore de 1 à 100 pour 1 à 100
	private int nutriParameters; // De 0 à 20, 6-Nutriscore de 1 à 5 pour 1 à 100
	private int timeParameters;  // De 0 à 1
	private RestrictionStructureMenus restrictionStructure ;
	
	
	
	
	public WeekMenus generateMenus(List<Recipe> allRecipe,IngredientRepository ingredientRepository) {
		WeekMenus weekMenus = new WeekMenus(restrictionStructure);
		List<Recipe> entree = subListByCategories(allRecipe,CategoriesRecipe.ENTREE);
		List<Recipe> plat = subListByCategories(allRecipe,CategoriesRecipe.PLAT);
		List<Recipe> dessert = subListByCategories(allRecipe,CategoriesRecipe.DESSERT);
		
		List<RecipeMenus> entreeRecipe = new ArrayList<RecipeMenus>();
		List<RecipeMenus> platRecipe = new ArrayList<RecipeMenus>();
		List<RecipeMenus> dessertRecipe = new ArrayList<RecipeMenus>();
		
		for (Recipe recipe : entree) entreeRecipe.add(new RecipeMenus(recipe,this.rate(recipe, ingredientRepository)));
		for (Recipe recipe : plat) platRecipe.add(new RecipeMenus(recipe,this.rate(recipe, ingredientRepository)));
		for (Recipe recipe : dessert) dessertRecipe.add(new RecipeMenus(recipe,this.rate(recipe, ingredientRepository)));
		
		Collections.sort(entreeRecipe,RecipeMenus.ComparatorScore);
		Collections.sort(platRecipe,RecipeMenus.ComparatorScore);
		Collections.sort(dessertRecipe,RecipeMenus.ComparatorScore);
	
		weekMenus.fillEntree(entreeRecipe);
		weekMenus.fillPlat(platRecipe,this.noTime);
		weekMenus.fillDessert(dessertRecipe);
		
		
		return weekMenus;
	}

	
	
	public int rate(Recipe recipe,IngredientRepository ingredientRepository) {

		for (IngredientDTO ingr : recipe.getIngredients()) {
			if (this.inForbiddenIngredient(ingr)) return 0;
			for (Diet diet : this.listDiet) {
				Optional<Ingredient> ingrOpt = ingredientRepository.findByName(ingr.getName());
				if (ingrOpt.isPresent()) {
					if (! diet.acceptIngredient(ingrOpt.get())) return 0;
					
		    	}
		    	else {
		    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		    	}

			}
		}
		int score = 0;
		score += this.ecoParameters * (100-recipe.getEcoScore());
		score += this.nutriParameters *  (6-recipe.getNutriScore().getValue());
		int timeScore = 110- recipe.getTimeRecipe().totalTime();
		if (timeScore >100) timeScore = 100;
		if (timeScore < 0) timeScore = 0;
		score += this.timeParameters * timeScore;
		score += (int) (Math.random() * 50);
		score += (int)(10*  recipe.getScore());
		
		return score;
	}
	
	public boolean inForbiddenIngredient(IngredientDTO ingr) {
		for (Ingredient ingrForbidden : this.forbiddenIngredient) {
			if (ingrForbidden.getName() == ingr.getName()) return true;
 		}
		return false;
	}

	private static List<Recipe> subListByCategories(List<Recipe> list,CategoriesRecipe category){
		List<Recipe> result =  new ArrayList<Recipe>();
		for (Recipe recipe : list) {
			if (recipe.haveCategory(category)) result.add(recipe);
		}
		return result;
	}
}


