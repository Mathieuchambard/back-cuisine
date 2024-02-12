package blockchain.entrepreneur.cuisine.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class IngredientDTO {

	private String name;
	private float quantity;
	private Unit unit;
	
	public IngredientDTO() {}
	
	public float getQuantityGramme(Ingredient ingr) {
		float result = 0;
		switch (this.unit) {
	        case G:
	        	result = this.quantity;
	            break;
	        case CL:
	        	result = this.quantity;
	            break;
	        case CAS:
	        	result = 0;
	            break;
	        case CAC:
	        	result = 0;
	            break;
	        case NUMBER:
	        	result = this.quantity * ingr.getUnitTogramme();
	            break;
	        case NONE:
	        	result = 0;
	            break;
	            
		}
		return result;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity(float quantity) {
		this.quantity += quantity;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}


	public IngredientDTO(String name, float quantity, Unit unit) {
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
	}


	public IngredientDTO copy() {
		return new IngredientDTO(this.name, this.quantity, this.unit);
	}

	public Ingredient dtoToIngredient() {
		File directory = new File("src/main/resources/jsonIngredient/" + this.getName() + ".json");
		Ingredient ingredient = null;

		try (FileReader reader = new FileReader(directory)) {
			BufferedReader fileReader = new BufferedReader(reader);
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ingredient = mapper.readValue(fileReader, Ingredient.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ingredient;
	}
	
	

}
