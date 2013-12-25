package net.jef.baristamatic.model;

/**
 * Represents an item in inventory.  Composes an Ingredient with information about stock on hand, and capacity of
 * stock in machine.  Provides methods to manage stock onhand.  (e.g. decrease stock, restock, report amount on hand.)
 * Implements Comparable so that collections can sort by name.
 */
public class InventoryItem implements Comparable<InventoryItem>{
    private final Ingredient ingredient;
    private final int max;
    private int onhand;

    public InventoryItem(Ingredient ingredient, int max) {
        this.ingredient = ingredient;
        this.max = max;
        this.onhand = 0;
    }

    public String getName() { return ingredient.getName(); }

    public Ingredient getIngredient() { return ingredient; }

    public int getOnhand() { return onhand; }

    public void decreaseStock(int amount) {
        onhand -= amount;
    }

    public void restock() {
        onhand = max;
    }

    @Override
    public int compareTo(InventoryItem item) {
        return getName().compareTo(item.getName());
    }
}
