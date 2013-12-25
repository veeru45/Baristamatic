package net.jef.baristamatic.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This is a coarsely grained unit test.  It does not mock dependencies.  Doing so, and breaking the test
 * into smaller pieces would provide value for code that will be used often and part of a Continuous Integration
 * type of environment.
 */
public class IngredientsTest {
    @Test
    public void testCreationAndAddAndGetIngredientByName() {
        Ingredient ing1 = new Ingredient("test ing", 50);
        Ingredients ingredients = new Ingredients();
        ingredients.add(ing1);
        Ingredient ing2 = ingredients.getIngredientByName("test ing");
        assertEquals(ing1, ing2);
        assertNull(ingredients.getIngredientByName("jef"));
    }


}
