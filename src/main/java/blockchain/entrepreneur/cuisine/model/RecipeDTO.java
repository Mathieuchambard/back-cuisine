package blockchain.entrepreneur.cuisine.model;

import java.util.List;
import java.util.Map;

public class RecipeDTO {
    private String nameId;
    private String name;

    private TimeRecipe timeRecipe;

    private NutriScore nutriscore;

    private Difficulty difficulty;

    private List<String> encodeImage;

    private float ecoScore;

    private String ingredients;





    public RecipeDTO(String nameId, String name,TimeRecipe timeRecipe,NutriScore nutriscore,Difficulty difficulty,float ecoScore,List<String> encodeImage,String ingredients) {
        this.nameId = nameId;
        this.name = name;
        this.timeRecipe=timeRecipe;
        this.nutriscore=nutriscore;
        this.difficulty=difficulty;
        this.encodeImage = encodeImage;
        this.ecoScore=ecoScore;
        this.ingredients=ingredients;
    }

    public float getEcoScore() {
        return ecoScore;
    }

    public void setEcoScore(float ecoScore) {
        this.ecoScore = ecoScore;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeRecipe getTimeRecipe() {
        return timeRecipe;
    }

    public void setTimeRecipe(TimeRecipe timeRecipe) {
        this.timeRecipe = timeRecipe;
    }

    public NutriScore getNutriscore() {
        return nutriscore;
    }

    public void setNutriscore(NutriScore nutriscore) {
        this.nutriscore = nutriscore;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getEncodeImage() {
        return encodeImage;
    }

    public void setEncodeImage(List<String> encodeImage) {
        this.encodeImage = encodeImage;
    }
}
