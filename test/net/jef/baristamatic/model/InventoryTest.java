package net.jef.baristamatic.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a coarsely grained unit test.  It does not mock dependencies.  Doing so, and breaking the test
 * into smaller pieces would provide value for code that will be used often and part of a Continuous Integration
 * type of environment.
 */

public class InventoryTest {
    Inventory inventory;
    Ingredient ing1;

    @Before
    public void setup() {
        inventory = new Inventory();
        ing1 = new Ingredient("test ing", 50);

    }
    @Test
    public void testAddItemAndIngredientAvailable() {
        inventory.addItem(ing1);
        inventory.restockAll();
        assertTrue(inventory.ingredientAvailable(ing1, 10));
    }

    @Test
    public void testDecreaseIngredient() {
        inventory.addItem(ing1);
        inventory.restockAll();
        inventory.decreaseIngredient(ing1, 5);
        assertTrue(inventory.ingredientAvailable(ing1, 5));
        assertFalse(inventory.ingredientAvailable(ing1, 6));
    }

    @Test
    public void testRestockAll() {
        inventory.addItem(ing1);
        assertFalse(inventory.ingredientAvailable(ing1, 1));
        inventory.restockAll();
        assertTrue(inventory.ingredientAvailable(ing1, 10));
    }

    @Test
    public void testInvalidIngredientAvailable() {
        Ingredient ing2 = new Ingredient("invalid ing", 25);
        assertFalse(inventory.ingredientAvailable(ing2, 1));
    }

    @Test
    public void testInvalidIngredientDecrease() {
        Ingredient ing2 = new Ingredient("invalid ing", 25);
        // make sure it doesn't throw exception
        inventory.decreaseIngredient(ing2, 1);
    }

    @Test
    public void testIterator() {
        for (InventoryItem item : inventory) {
            assertTrue(item instanceof InventoryItem);
        }
    }

}
