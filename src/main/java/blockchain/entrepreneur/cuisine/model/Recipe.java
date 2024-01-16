package blockchain.entrepreneur.cuisine.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Recipe {
	
	private String nameId;
	private String name;
	private List<IngredientDTO> ingredients;
	private List<String> instructions;
	private Difficulty difficulty;
	private int serves;
	private int price;
	private TimeRecipe timeRecipe;
	private Date date;
	private String urlImage;
	
	public Recipe() {}
	

	
	public List<Ingredient> ingredientInRecipe(List<Ingredient> lIngredient) {
		List<Ingredient> l = new ArrayList<Ingredient>();
		for (Ingredient ingr : lIngredient) {
			for (IngredientDTO ingrRecipe : this.ingredients) {
				if (ingr.getName() == ingrRecipe.getName()) l.add(ingr);
			}
		}
		return l ;
	}
	




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<IngredientDTO> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}
	
	public List<String> getInstructions() {
		return instructions;
	}
	
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public int getServes() {
		return serves;
	}
	
	public void setServes(int serves) {
		this.serves = serves;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}


	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}


	public TimeRecipe getTimeRecipe() {
		return timeRecipe;
	}

	public void setTimeRecipe(TimeRecipe timeRecipe) {
		this.timeRecipe = timeRecipe;
	}


	


}
