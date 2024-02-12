package blockchain.entrepreneur.cuisine.model;

import java.util.List;

public class HeatBalance {

	private float energieKcal;
	private float energieKj;
	
	private float eau;
	private float proteines;
	private float glucides;
	private float sucres;
	private float lipides;
	private float agSature;
	private float fibres;
	private float sel;
	private float sodium;
	private float calcium;
	private float fer;
	private float vitamineD;
	private float vitamineC;

	
	
	
	

	
	public HeatBalance() {
		this.energieKcal = 0;
		this.energieKj = 0;
		this.eau = 0;
		this.proteines = 0;
		this.glucides = 0;
		this.sucres = 0;
		this.lipides = 0;
		this.agSature = 0;
		this.fibres = 0;
		this.sel = 0;
		this.sodium = 0 ;
		this.calcium = 0;
		this.fer = 0;
		this.vitamineD = 0;
		this.vitamineC = 0;
	}
	
	public void sum(float ponderation, HeatBalance heatbalance) {
		this.energieKcal += ponderation * heatbalance.energieKcal;
		this.energieKj += ponderation * heatbalance.energieKj;
		this.eau += ponderation * heatbalance.eau;
		this.proteines += ponderation * heatbalance.proteines;
		this.glucides += ponderation * heatbalance.glucides;
		this.sucres += ponderation * heatbalance.sucres;
		this.lipides += ponderation * heatbalance.lipides;
		this.agSature += ponderation * heatbalance.agSature;
		this.fibres += ponderation * heatbalance.fibres;
		this.sel += ponderation * heatbalance.sel;
		this.sodium += ponderation * heatbalance.sodium;
		this.calcium += ponderation * heatbalance.calcium;
		this.fer += ponderation * heatbalance.fer;
		this.vitamineD += ponderation * heatbalance.vitamineD;
		this.vitamineC += ponderation * heatbalance.vitamineC;
	}
	
	public void ponderation(float ponderation) {
		this.energieKcal /= ponderation;
		this.energieKj /= ponderation ;
		this.eau /= ponderation ;
		this.proteines /= ponderation ;
		this.glucides /= ponderation ;
		this.sucres /= ponderation ;
		this.lipides /= ponderation ;
		this.agSature /= ponderation ;
		this.fibres /= ponderation ;
		this.sel /= ponderation ;
		this.sodium /= ponderation ;
		this.calcium /= ponderation ;
		this.fer /= ponderation;
		this.vitamineD /= ponderation ;
		this.vitamineC /= ponderation ;
	}
	
	public NutriScore nutriScore() {
		int ptsKj,ptsGlus,ptsAgs,ptsNa,ptsProt,ptsFib,ptsA,score ;
		NutriScore nutriScore;
		
		ptsKj = (int) (this.energieKj / 335);
		if (ptsKj > 10) ptsKj = 10;
		
		ptsGlus = (int) (this.sucres / 4.5);
		if (ptsGlus > 10) ptsGlus = 10;

		ptsAgs = (int) (this.agSature );
		if (ptsAgs > 10) ptsAgs = 10;
		
		ptsNa = (int) (this.sodium / 90);
		if (ptsNa > 10) ptsNa = 10;
		
		ptsProt = (int) (this.proteines / 1.6);
		if (ptsProt > 5) ptsProt = 5;
		
		ptsFib = (int) (this.fibres / 0.9);
		if (ptsFib > 5) ptsFib = 5;
		
		ptsA = ptsKj + ptsGlus + ptsAgs + ptsNa;
		
		score = ptsA - ptsFib - ptsProt;
		
		if (score < 0) nutriScore = NutriScore.A;
		else {
			if (score < 3) nutriScore = NutriScore.B;
			else {
				if (score < 11) nutriScore = NutriScore.C;
				else {
					if (score < 19) nutriScore = NutriScore.D;
					else nutriScore = NutriScore.E;
				}
			}
		}

		return nutriScore;
	}

	public float getEnergieKcal() {
		return energieKcal;
	}

	public void setEnergieKcal(float energieKcal) {
		this.energieKcal = energieKcal;
	}

	public float getEnergieKj() {
		return energieKj;
	}

	public void setEnergieKj(float energieKj) {
		this.energieKj = energieKj;
	}

	public float getEau() {
		return eau;
	}

	public void setEau(float eau) {
		this.eau = eau;
	}

	public float getProteines() {
		return proteines;
	}

	public void setProteines(float proteines) {
		this.proteines = proteines;
	}

	public float getGlucides() {
		return glucides;
	}

	public void setGlucides(float glucides) {
		this.glucides = glucides;
	}

	public float getSucres() {
		return sucres;
	}

	public void setSucres(float sucres) {
		this.sucres = sucres;
	}

	public float getLipides() {
		return lipides;
	}

	public void setLipides(float lipides) {
		this.lipides = lipides;
	}

	public float getAgSature() {
		return agSature;
	}

	public void setAgSature(float agSature) {
		this.agSature = agSature;
	}

	public float getFibres() {
		return fibres;
	}

	public void setFibres(float fibres) {
		this.fibres = fibres;
	}

	public float getSel() {
		return sel;
	}

	public void setSel(float sel) {
		this.sel = sel;
	}

	public float getSodium() {
		return sodium;
	}

	public void setSodium(float sodium) {
		this.sodium = sodium;
	}

	public float getCalcium() {
		return calcium;
	}

	public void setCalcium(float calcium) {
		this.calcium = calcium;
	}

	public float getFer() {
		return fer;
	}

	public void setFer(float fer) {
		this.fer = fer;
	}

	public float getVitamineD() {
		return vitamineD;
	}

	public void setVitamineD(float vitamineD) {
		this.vitamineD = vitamineD;
	}

	public float getVitamineC() {
		return vitamineC;
	}

	public void setVitamineC(float vitamineC) {
		this.vitamineC = vitamineC;
	}


	public void updateHeatBalance(List<IngredientDTO> ingredients) {
		for (IngredientDTO ingredient : ingredients) {
			Ingredient ingr = ingredient.dtoToIngredient();
			this.sum(ingredient.getQuantityGramme(ingr)/100,ingr.getHeatBalance());

		}
	}
}
