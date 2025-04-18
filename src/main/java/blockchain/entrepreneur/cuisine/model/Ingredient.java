package blockchain.entrepreneur.cuisine.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Comparator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.annotation.Id;


public class Ingredient {

	@Id
	private String id;
	private String name;
	private HeatBalance heatBalance;
	private EcologicalBalance ecologicalBalance;
	private boolean glutenFree;
	private boolean pregnantPermission;
	private boolean vegan;

	private int ciqual;
	private float unitTogramme;
	private String image;

	public Ingredient() {

	}

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public boolean isGlutenFree() {
		return glutenFree;
	}


	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}


	public boolean isPregnantPermission() {
		return pregnantPermission;
	}


	public void setPregnantPermission(boolean pregnantPermission) {
		this.pregnantPermission = pregnantPermission;
	}


	public boolean isVegan() {
		return vegan;
	}


	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public HeatBalance getHeatBalance() {
		return heatBalance;
	}


	public void setHeatBalance(HeatBalance heatBalance) {
		this.heatBalance = heatBalance;
	}


	public EcologicalBalance getEcologicalBalance() {
		return ecologicalBalance;
	}


	public void setEcologicalBalance(EcologicalBalance ecologicalBalance) {
		this.ecologicalBalance = ecologicalBalance;
	}

	public int getCiqual() {
		return ciqual;
	}

	public void setCiqual(int ciqual) {
		this.ciqual = ciqual;
	}

	public float getUnitTogramme() {
		return unitTogramme;
	}

	public void setUnitTogramme(float unitTogramme) {
		this.unitTogramme = unitTogramme;
	}

	public static Comparator<Ingredient> ComparatorNom = new Comparator<Ingredient>() {

		@Override
		public int compare(Ingredient e1, Ingredient e2) {
			return e1.name.compareTo(e2.name);
		}
	};


}