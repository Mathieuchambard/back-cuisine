package blockchain.entrepreneur.cuisine.model.menus;

import blockchain.entrepreneur.cuisine.model.Recipe;

public class Menus {
	private Recipe entreeRecipe;
	private boolean entree;
	private Recipe platRecipe;
	private boolean plat;
	private Recipe dessertRecipe;
	private boolean dessert;
	
	
	
	
	public Menus(boolean entree, boolean plat, boolean dessert) {
		this.entree = entree;
		this.plat = plat;
		this.dessert = dessert;
	}
	
	public Recipe getEntreeRecipe() {
		return entreeRecipe;
	}
	public void setEntreeRecipe(Recipe entreeRecipe) {
		this.entreeRecipe = entreeRecipe;
	}
	public boolean isEntree() {
		return entree;
	}
	public void setEntree(boolean entree) {
		this.entree = entree;
	}
	public Recipe getPlatRecipe() {
		return platRecipe;
	}
	public void setPlatRecipe(Recipe platRecipe) {
		this.platRecipe = platRecipe;
	}
	public boolean isPlat() {
		return plat;
	}
	public void setPlat(boolean plat) {
		this.plat = plat;
	}
	public Recipe getDessertRecipe() {
		return dessertRecipe;
	}
	public void setDessertRecipe(Recipe dessertRecipe) {
		this.dessertRecipe = dessertRecipe;
	}
	public boolean isDessert() {
		return dessert;
	}
	public void setDessert(boolean dessert) {
		this.dessert = dessert;
	}
	
	
	
}
