package net.jef.baristamatic.dataaccess;

import net.jef.baristamatic.model.*;

/**
 * Loads data provided by requirements document.  Ingredients and products definitions can be changed here.
 * This is a somewhat "low rent" data load and validation.  This data would probably normally be loaded
 * from a database or file or other service.
 */
public class BaristaData {

    private final String[][] ingredientDefinitions = {
        /* name */          /* cost */
            {"Coffee",          "75"},
            {"Decaf Coffee",    "75"},
            {"Sugar",           "25"},
            {"Cream",           "25"},
            {"Steamed Milk",    "35"},
            {"Foamed Milk",     "35"},
            {"Espresso",        "110"},
            {"Cocoa",           "90"},
            {"Whipped Cream",   "100"}

    };

    private final String[][] productDefinitions = {
            /* product name */   /* ingredient, amount */  /* ingredient, amount */
            {"Coffee",          "Coffee", "3",          "Sugar", "1",           "Cream", "1"},
            {"Decaf Coffee",    "Decaf Coffee", "3",    "Sugar", "1",           "Cream", "1"},
            {"Caffe Latte",     "Espresso", "2",        "Steamed Milk", "1"},
            {"Caffe Americano", "Espresso", "3"},
            {"Caffe Mocha",     "Espresso", "1",        "Cocoa", "1",           "Steamed Milk", "1",    "Whipped Cream", "1"},
            {"Cappuccino",      "Espresso", "2",        "Steamed Milk", "1",    "Foamed Milk", "1"}
    };

    private boolean validIngredientDef(String[] ingDef) {
        if (ingDef.length != 2) { return false; }       // def must have 2 elements
        if (ingDef[0].length() < 1) { return false; }   // name must be at least one character long
        try {
            Integer.parseInt(ingDef[1]);                // second element must be a number
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean validProductDef(String[] productDef) {
        if (productDef.length <= 0) { return false; }        // must have at least a name at element 0
        if (productDef.length % 2 == 0 ) { return false; }   // ingredients must have an matching amount. count must be odd
        for (int i = 1; i < productDef.length; i += 2) {     // each ingredient name should be at least one character long
            if(productDef[i].length() <  1) { return false; }
        }
        for(int i = 2; i < productDef.length; i += 2) {     // each ingredient amount needs to be a number
            try {
                Integer.parseInt(productDef[i]);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private Product createProductFromDefinition(String[] productDef, Ingredients ingredients) {
        String productName = productDef[0];
        Product product = new Product(productName);
        for (int i = 1; i < productDef.length; i += 2) {
            String ingredientName = productDef[i];
            Ingredient ingredient = ingredients.getIngredientByName(ingredientName);
            if (ingredient == null) {
                System.err.println("Product definition for: " + productName
                        + " contains invalid ingredient: " + ingredientName);
                return null;
            }
            int amount = Integer.parseInt(productDef[i+1]);
            product.addElement(ingredient, amount);
        }
        return product;
    }

    public Ingredients createIngredients() {
        Ingredients ingredients = new Ingredients();
        for (String[] def : ingredientDefinitions) {
            if (validIngredientDef(def)) {
                String name = def[0];
                int cost = Integer.parseInt(def[1]);
                Ingredient ingredient = new Ingredient(name, cost);
                ingredients.add(ingredient);
            } else {
                System.err.println("Invalid ingredient definition: " + (def.length == 0 ? null : def[0]));
            }
        }
        return ingredients;
    }

    public Inventory createAndStockInventory(Ingredients ingredients) {
        Inventory inventory = new Inventory();
        for (Ingredient ingredient : ingredients) {
            inventory.addItem(ingredient);
        }
        inventory.restockAll();
        return inventory;
    }

    public Products createProducts(Ingredients ingredients) {
        Products products = new Products();
        for (String[] prodDef : productDefinitions) {
            if (validProductDef(prodDef)) {
                Product product = createProductFromDefinition(prodDef, ingredients);
                if (product != null) {
                    products.addProduct(product);
                } else {
                    System.err.println("Failed creating product: " + (prodDef.length == 0 ? null : prodDef[0]));
                }
            } else {
                System.err.println("Invalid product definition: " + (prodDef.length == 0 ? null : prodDef[0]));
            }
        }
        return products;
    }
}
