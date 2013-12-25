package net.jef.baristamatic.model;

/**
 * Represent a ProductElement.  Contains Ingredient and amount.  A collection of these make up a Product.
 * Analogous to a line in a recipe.  (e.g. 1 cup of sugar.)
 */
public class ProductElement {
    private final Ingredient ingredient;
    private final int amount;

    public ProductElement(Ingredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Ingredient getIngredient() { return ingredient; }

    public int getAmount() { return amount; }

    public String getName() { return ingredient.getName(); }

    public int getCost() { return ingredient.getCost() * amount; }
}
