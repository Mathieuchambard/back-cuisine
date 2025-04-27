package blockchain.entrepreneur.cuisine.model;

import java.util.List;

public class EcologicalBalance {
	private String emballage;
	private float scoreEF;
	private float changementClimatique ;
	private float appauvrissementCoucheOzone;
	private float rayonnementsIonisants;
	private float formationPhotochimiqueOzone;
	private float particules;
	private float acidificationTerrestreEtEauxDouces;
	private float eutrophisationTerrestre ;
	private float eutrophisationEauxDouces;
	private float eutrophisationMarine;
	private float utilisationSol;
	private float ecotoxiciteEcosystemesAquatiqueEauDouce;
	private float epuisementEau;
	private float epuisementEnergie;
	private float epuisementMineraux;
	


	public EcologicalBalance() {
		this.setEmballage("");
		this.scoreEF = 0;
		this.changementClimatique = 0;
		this.appauvrissementCoucheOzone= 0;
		this.rayonnementsIonisants= 0;
		this.formationPhotochimiqueOzone= 0;
		this.particules= 0;
		this.acidificationTerrestreEtEauxDouces= 0;
		this.eutrophisationTerrestre = 0;
		this.eutrophisationEauxDouces= 0;
		this.eutrophisationMarine= 0;
		this.utilisationSol= 0;
		this.ecotoxiciteEcosystemesAquatiqueEauDouce= 0;
		this.epuisementEau= 0;
		this.epuisementEnergie= 0;
		this.epuisementMineraux= 0;
	}
	
	public void sum(float ponderation, EcologicalBalance ecologicalBalance) {

		this.scoreEF += ponderation * ecologicalBalance.scoreEF;
		this.changementClimatique += ponderation * ecologicalBalance.changementClimatique;
		this.appauvrissementCoucheOzone += ponderation * ecologicalBalance.appauvrissementCoucheOzone;
		this.rayonnementsIonisants += ponderation * ecologicalBalance.rayonnementsIonisants;
		this.formationPhotochimiqueOzone += ponderation * ecologicalBalance.formationPhotochimiqueOzone;
		this.particules+= ponderation * ecologicalBalance.particules;
		this.acidificationTerrestreEtEauxDouces += ponderation * ecologicalBalance.acidificationTerrestreEtEauxDouces;
		this.eutrophisationTerrestre += ponderation * ecologicalBalance.eutrophisationTerrestre;
		this.eutrophisationEauxDouces += ponderation * ecologicalBalance.eutrophisationEauxDouces;
		this.eutrophisationMarine += ponderation * ecologicalBalance.eutrophisationMarine;
		this.utilisationSol += ponderation * ecologicalBalance.utilisationSol;
		this.ecotoxiciteEcosystemesAquatiqueEauDouce += ponderation * ecologicalBalance.ecotoxiciteEcosystemesAquatiqueEauDouce;
		this.epuisementEau += ponderation * ecologicalBalance.epuisementEau;
		this.epuisementEnergie += ponderation * ecologicalBalance.epuisementEnergie;
		this.epuisementMineraux += ponderation * ecologicalBalance.epuisementMineraux;


	}
	
	public void ponderation(float ponderation) {
		this.scoreEF /= ponderation ;
		this.changementClimatique /= ponderation ;
		this.appauvrissementCoucheOzone /= ponderation ;
		this.rayonnementsIonisants /= ponderation ;
		this.formationPhotochimiqueOzone /= ponderation ;
		this.particules /= ponderation ;
		this.acidificationTerrestreEtEauxDouces /= ponderation ;
		this.eutrophisationTerrestre /= ponderation ;
		this.eutrophisationEauxDouces /= ponderation ;
		this.eutrophisationMarine /= ponderation ;
		this.utilisationSol /= ponderation ;
		this.ecotoxiciteEcosystemesAquatiqueEauDouce /= ponderation ;
		this.epuisementEau /= ponderation ;
		this.epuisementEnergie /= ponderation ;
		this.epuisementMineraux /= ponderation ;
	}

	public double updateEcologicalBalance(List<IngredientDTO> ingredients) {
		boolean valide = true;
		for (IngredientDTO ingredient : ingredients) {
			Ingredient ingr = ingredient.dtoToIngredient();
			if (ingr.getEcologicalBalance() != null){
			this.sum(ingredient.getQuantityGramme(ingr)/1000,ingr.getEcologicalBalance());}
			else{
				valide = false;
			}

		}
		if (!valide){
			return -1;
		}
		else{
			return this.ecologicalBalanceToEcoScore();
		}
	}
	
	public double ecologicalBalanceToEcoScore() {
		return 100 - 20*Math.log(10*this.scoreEF+1)/ Math.log(2+1/(100*Math.pow(this.scoreEF,4)));
	}
	public float getScoreEF() {
		return scoreEF;
	}

	public void setScoreEF(float scoreEF) {
		this.scoreEF = scoreEF;
	}

	public String getEmballage() {
		return emballage;
	}

	public void setEmballage(String emballage) {
		this.emballage = emballage;
	}

	public float getChangementClimatique() {
		return changementClimatique;
	}

	public void setChangementClimatique(float changementClimatique) {
		this.changementClimatique = changementClimatique;
	}

	public float getAppauvrissementCoucheOzone() {
		return appauvrissementCoucheOzone;
	}

	public void setAppauvrissementCoucheOzone(float appauvrissementCoucheOzone) {
		this.appauvrissementCoucheOzone = appauvrissementCoucheOzone;
	}

	public float getRayonnementsIonisants() {
		return rayonnementsIonisants;
	}

	public void setRayonnementsIonisants(float rayonnementsIonisants) {
		this.rayonnementsIonisants = rayonnementsIonisants;
	}

	public float getFormationPhotochimiqueOzone() {
		return formationPhotochimiqueOzone;
	}

	public void setFormationPhotochimiqueOzone(float formationPhotochimiqueOzone) {
		this.formationPhotochimiqueOzone = formationPhotochimiqueOzone;
	}

	public float getParticules() {
		return particules;
	}

	public void setParticules(float particules) {
		this.particules = particules;
	}

	public float getAcidificationTerrestreEtEauxDouces() {
		return acidificationTerrestreEtEauxDouces;
	}

	public void setAcidificationTerrestreEtEauxDouces(float acidificationTerrestreEtEauxDouces) {
		this.acidificationTerrestreEtEauxDouces = acidificationTerrestreEtEauxDouces;
	}

	public float getEutrophisationTerrestre() {
		return eutrophisationTerrestre;
	}

	public void setEutrophisationTerrestre(float eutrophisationTerrestre) {
		this.eutrophisationTerrestre = eutrophisationTerrestre;
	}

	public float getEutrophisationEauxDouces() {
		return eutrophisationEauxDouces;
	}

	public void setEutrophisationEauxDouces(float eutrophisationEauxDouces) {
		this.eutrophisationEauxDouces = eutrophisationEauxDouces;
	}

	public float getEutrophisationMarine() {
		return eutrophisationMarine;
	}

	public void setEutrophisationMarine(float eutrophisationMarine) {
		this.eutrophisationMarine = eutrophisationMarine;
	}

	public float getUtilisationSol() {
		return utilisationSol;
	}

	public void setUtilisationSol(float utilisationSol) {
		this.utilisationSol = utilisationSol;
	}

	public float getEcotoxiciteEcosystemesAquatiqueEauDouce() {
		return ecotoxiciteEcosystemesAquatiqueEauDouce;
	}

	public void setEcotoxiciteEcosystemesAquatiqueEauDouce(float ecotoxiciteEcosystemesAquatiqueEauDouce) {
		this.ecotoxiciteEcosystemesAquatiqueEauDouce = ecotoxiciteEcosystemesAquatiqueEauDouce;
	}

	public float getEpuisementEau() {
		return epuisementEau;
	}

	public void setEpuisementEau(float epuisementEau) {
		this.epuisementEau = epuisementEau;
	}

	public float getEpuisementEnergie() {
		return epuisementEnergie;
	}

	public void setEpuisementEnergie(float epuisementEnergie) {
		this.epuisementEnergie = epuisementEnergie;
	}

	public float getEpuisementMineraux() {
		return epuisementMineraux;
	}

	public void setEpuisementMineraux(float epuisementMineraux) {
		this.epuisementMineraux = epuisementMineraux;
	}
	
}
