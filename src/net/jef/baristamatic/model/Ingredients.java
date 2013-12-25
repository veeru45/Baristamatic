package net.jef.baristamatic.model;

import java.util.ArrayList;

/**
 * Represents list of Ingredients.  Extending List to provide case insensitive lookup of elements by name.
 */
public class Ingredients extends ArrayList<Ingredient> {

    public Ingredient getIngredientByName(String name) {
        for (Ingredient ingredient : this) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }
        return null;
    }
}
