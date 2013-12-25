package net.jef.baristamatic.controller;

import net.jef.baristamatic.model.*;

/**
 * Machine acts as controller.  Interacts with Machine view to provide model elements, information about the
 * model, and control interactions between user, products, and inventory.
 */
public class Machine {
    private final Inventory inventory;
    private final Products products;

    public Machine(Inventory inventory, Products products) {
        this.inventory = inventory;
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int productsSize(){
        return products.size();
    }

    public boolean productAvailable(Product product) {
        for (ProductElement element : product.getElements()) {
            Ingredient ingredient = element.getIngredient();
            int amount = element.getAmount();
            if (!inventory.ingredientAvailable(ingredient, amount)) {
                // If any one ingredient is not available, then product is not available.
                return false;
            }
        }
        // All required ingredients are available.
        return true;
    }

    public Product getProductByIndex(int index) {
        return products.getProduct(index);
    }

    private void decreaseInventory(Product product) {
        for (ProductElement element : product.getElements()) {
            Ingredient ingredient = element.getIngredient();
            int amount = element.getAmount();
            inventory.decreaseIngredient(ingredient, amount);
        }
    }

    public boolean dispense(Product product) {
        // check inventory
        if (productAvailable(product)) {
            // Product will be dispensed.  View will inform user.
            // If there were an actual physical action to take it would happen here.
            decreaseInventory(product);
            return true;
        } else {
            // In this implementation the only reason to not dispense is that it is "Out of stock".
            return false;
        }
    }

    public void restockAll() {
        inventory.restockAll();
    }
}
