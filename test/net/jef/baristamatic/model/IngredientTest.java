package net.jef.baristamatic.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is a coarsely grained unit test.  Breaking the test into smaller pieces could provide value
 * for code that will be used often and part of a Continuous Integration type of environment.
 */
public class IngredientTest {

    @Test
    public void testCreationAndGetters() {
        Ingredient ingredient = new Ingredient("Test Ingred", 50);
        assertEquals("Test Ingred", ingredient.getName());
        assertEquals(50, ingredient.getCost());
    }
}
