package blockchain.entrepreneur.cuisine.model;

public class TimeRecipe {
	
	private int cooking;
	private int preparation;
	private int rest;
	
	public TimeRecipe() {}
	
	public TimeRecipe(int cooking, int preparation, int rest) {
		this.cooking = cooking;
		this.preparation = preparation;
		this.rest = rest;
	}
	
	public int getCooking() {
		return cooking;
	}
	
	public void setCooking(int cooking) {
		this.cooking = cooking;
	}
	
	public int getPreparation() {
		return preparation;
	}
	
	public void setPreparation(int preparation) {
		this.preparation = preparation;
	}
	
	public int getRest() {
		return rest;
	}
	
	public void setRest(int rest) {
		this.rest = rest;
	}
	
	public int totalTime() {
		return this.cooking + this.preparation + this.rest;
	}
}
