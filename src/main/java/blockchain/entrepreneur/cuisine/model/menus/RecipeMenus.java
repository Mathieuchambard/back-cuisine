package blockchain.entrepreneur.cuisine.model.menus;

import java.util.Comparator;

import blockchain.entrepreneur.cuisine.model.Recipe;

public class RecipeMenus {
	private Recipe recipe;
	private int score;
	

	
	public RecipeMenus(Recipe recipe, int f) {
		this.recipe = recipe;
		this.score = f;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public float getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public static Comparator<RecipeMenus> ComparatorScore = new Comparator<RecipeMenus>() {
	      
        @Override
        public int compare(RecipeMenus e1, RecipeMenus e2) {
            return e2.score - e1.score;
        }
    };
}
