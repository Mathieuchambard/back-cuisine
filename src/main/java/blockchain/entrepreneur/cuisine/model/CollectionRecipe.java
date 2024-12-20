package blockchain.entrepreneur.cuisine.model;

import java.util.ArrayList;
import java.util.List;

public class CollectionRecipe {
    private List<String> listRecipe ;
    private String name;
    private String nameId;
    private String description;
    private Integer nRecipe;

    public boolean inCollection(String nameID){
        return this.listRecipe.contains(nameID);
    }

    public List<String> getListRecipe() {
        return listRecipe;
    }

    public void setListRecipe(List<String> listRecipe) {
        this.listRecipe = listRecipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getnRecipe() {
        return nRecipe;
    }

    public void setnRecipe(Integer nRecipe) {
        this.nRecipe = nRecipe;
    }

    public CollectionRecipe() {

    }


}
