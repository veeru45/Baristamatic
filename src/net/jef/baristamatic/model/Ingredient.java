package net.jef.baristamatic.model;

/**
 * Represent an Ingredient used to compose a Product.
 * Contains a name and a cost.
 */
public class Ingredient {
    private final String name;
    private final int cost;

    public Ingredient(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() { return name; }

    public int getCost() { return cost; }

}
