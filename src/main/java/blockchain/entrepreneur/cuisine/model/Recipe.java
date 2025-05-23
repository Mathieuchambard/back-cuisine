package blockchain.entrepreneur.cuisine.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Recipe {
	
	private String nameId;
	private String name;
	private List<IngredientDTO> ingredients;
	private List<Instruction> instructions;
	private Difficulty difficulty;
	private UnitServes unitServes;

	private HeatBalance heatBalance;

	private NutriScore nutriscore; 

	private EcologicalBalance ecologicalBalance;

	private double ecoScore;

	private int serves;
	private int price;
	private TimeRecipe timeRecipe;
	private String date;

	private String[] encodeImage;

	private String[] tag;
	private String[] recipeWith;
	private String astuceChef;

	


	
	public Recipe() {}

	public UnitServes getUnitServes() {
		return unitServes;
	}

	public void setUnitServes(UnitServes unitServes) {
		this.unitServes = unitServes;
	}

	public EcologicalBalance getEcologicalBalance() {
		return ecologicalBalance;
	}

	public void setEcologicalBalance(EcologicalBalance ecologicalBalance) {
		this.ecologicalBalance = ecologicalBalance;
	}

	public NutriScore getNutriscore() {
		return nutriscore;
	}

	public void setNutriscore(NutriScore nutriscore) {
		this.nutriscore = nutriscore;
	}

	public double getEcoScore() {
		return ecoScore;
	}

	public void setEcoScore(double ecoScore) {
		this.ecoScore = ecoScore;
	}

	public HeatBalance getHeatBalance() {
		return heatBalance;
	}

	public void setHeatBalance(HeatBalance heatBalance) {
		this.heatBalance = heatBalance;
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
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	public void setInstructions(List<Instruction> instructions) {
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
	


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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


    public void updateDate() {
		LocalDate dateDuJour = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.date = dateDuJour.format(formatter);
    }

	public String[] getEncodeImage() {
		return encodeImage;
	}

	public void setEncodeImage(String[] encodeImage) {
		this.encodeImage = encodeImage;
	}

	public String[] getTag() {
		return tag;
	}

	public void setTag(String[] tag) {
		this.tag = tag;
	}

	public String[] getRecipeWith() {
		return recipeWith;
	}

	public void setRecipeWith(String[] recipeWith) {
		this.recipeWith = recipeWith;
	}

	public String getAstuceChef() {
		return astuceChef;
	}

	public void setAstuceChef(String astuceChef) {
		this.astuceChef = astuceChef;
	}
}
