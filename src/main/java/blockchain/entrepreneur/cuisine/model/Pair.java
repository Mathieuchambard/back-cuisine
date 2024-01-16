package blockchain.entrepreneur.cuisine.model;

public class Pair {
    private String nameId;
    private String name;

    public Pair(String nameId, String name) {
        this.nameId = nameId;
        this.name = name;
    }

    public String getNameId() {
        return nameId;
    }

    public String getName() {
        return name;
    }
}
