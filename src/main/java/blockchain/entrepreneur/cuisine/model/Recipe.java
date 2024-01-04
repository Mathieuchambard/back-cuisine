package blockchain.entrepreneur.cuisine.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Recipe {
	
	@Id
	private String id;
	private String nameId;
	private String name;
	private List<IngredientDTO> ingredients;
	private List<String> instructions;
	private Difficulty difficulty;
	private HeatBalance heatBalance;
	private EcologicalBalance ecologicalBalance;
	private double ecoScore;
	private NutriScore nutriScore;
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
	

	public NutriScore getNutriScore() {
		return nutriScore;
	}

	public void setNutriScore(NutriScore nutriScore) {
		this.nutriScore = nutriScore;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getTitle() {
		return name;
	}
	
	public void setTitle(String title) {
		this.name = title;
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
	
	public HeatBalance getHeatBalance() {
		return heatBalance;
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
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
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

	public double getEcoScore() {
		return ecoScore;
	}

	public void setEcoScore(double ecoScore) {
		this.ecoScore = ecoScore;
	}

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public EcologicalBalance getEcologicalBalance() {
		return ecologicalBalance;
	}

	public void setEcologicalBalance(EcologicalBalance ecologicalBalance) {
		this.ecologicalBalance = ecologicalBalance;
	}

	public void setHeatBalance(HeatBalance heatBalance) {
		this.heatBalance = heatBalance;
	}

	public TimeRecipe getTimeRecipe() {
		return timeRecipe;
	}

	public void setTimeRecipe(TimeRecipe timeRecipe) {
		this.timeRecipe = timeRecipe;
	}


	


}
