package blockchain.entrepreneur.cuisine.model;

public class RecipeCart {
	private Recipe recipe;
	private int serves;
	
	public RecipeCart(Recipe recipe) {
		this.recipe = recipe;
		this.serves = recipe.getServes();
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public int getServes() {
		return serves;
	}
	public void setServes(int serves) {
		this.serves = serves;
	}
}
