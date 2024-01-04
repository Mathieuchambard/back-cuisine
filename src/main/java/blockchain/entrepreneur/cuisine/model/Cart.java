package blockchain.entrepreneur.cuisine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	List<RecipeCart> listeRecipe ;
	
	public Cart() {
		this.listeRecipe= new ArrayList<RecipeCart>() ;
	}
	
	public void addRecipeToCart(Recipe recipe) {
		this.listeRecipe.add(new RecipeCart(recipe));
	}
	
	public void changeServes(Recipe recipe,int serves) {
		for (RecipeCart recipeCart : this.listeRecipe) {
			if (recipeCart.getRecipe() == recipe) recipeCart.setServes(serves);
		}
	}
	
	
	public List<IngredientDTO> getAllIngredient(){
		Map<String,List<IngredientDTO>> mapIngredient = new HashMap<>();
		
		for (RecipeCart recipeCart : this.listeRecipe) {
			int serve = recipeCart.getServes();
			Recipe recipe = recipeCart.getRecipe();
			int serveRecipe = recipe.getServes();
			for (IngredientDTO ingr : recipe.getIngredients()) {
				IngredientDTO ingredient = ingr.copy();
				ingredient.setQuantity(ingr.getQuantity() * serve/((float) serveRecipe));
				if (mapIngredient.containsKey(ingredient.getName())) {
					List<IngredientDTO> l = mapIngredient.get(ingredient.getName());
					l.add(ingredient);
					mapIngredient.put(ingredient.getName(),l);
				}
				else {
					List<IngredientDTO> l = new ArrayList<IngredientDTO>() ;
					l.add(ingredient);
					mapIngredient.put(ingredient.getName(),l);
				}
			}
		}
		
		List<IngredientDTO> listIngredient = new ArrayList<IngredientDTO>();
		mapIngredient.forEach((key,value)->{
			listIngredient.add(addQuantity(value));
		});
		
		
		return listIngredient;
		
		
		
	}
	
	private IngredientDTO addQuantity(List<IngredientDTO> listeIngredient) {
		IngredientDTO ingrFinal = listeIngredient.get(0).copy();
		ingrFinal.setQuantity(0);
		ingrFinal.setUnit(Unit.NONE);
		for (IngredientDTO ingr : listeIngredient) {
			switch(ingr.getUnit()) {
				case G: 
					ingrFinal.setUnit(Unit.G);
					ingrFinal.addQuantity(ingr.getQuantity());
					break;
			   
			   case CL:
					ingrFinal.setUnit(Unit.CL);
					ingrFinal.addQuantity(ingr.getQuantity());
			       break;
			   			       
			   default:
				   break; 
			}
		}
		return ingrFinal;
		
	}
	
	
}


