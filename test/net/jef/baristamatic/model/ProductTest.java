package net.jef.baristamatic.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a coarsely grained unit test.  It does not mock dependencies.  Doing so, and breaking the test
 * into smaller pieces would provide value for code that will be used often and part of a Continuous Integration
 * type of environment.
 */
public class ProductTest {

    @Test
    public void testCreation() {
        Product product = new Product("Test Product");
        assertEquals("Test Product", product.getName());
    }

    @Test
    public void testAddingAndGettingElementsAndGettingCost() {
        Product product = new Product("Test Product");
        Ingredient ing1 = new Ingredient("Test Ing1", 50);
        Ingredient ing2 = new Ingredient("Test Ing2", 100);
        product.addElement(ing1, 3);
        product.addElement(ing2, 1);
        List<ProductElement> elements = product.getElements();
        assertEquals("Test Ing1", elements.get(0).getName());
        assertEquals("Test Ing2", elements.get(1).getName());
        assertEquals(250, product.getCost());
    }

    @Test
    public void testComparing() {
        Product product1 = new Product("1 Test Product");
        Ingredient ing1 = new Ingredient("Test Ing1", 50);
        Ingredient ing2 = new Ingredient("Test Ing2", 100);
        product1.addElement(ing1, 3);
        Product product2 = new Product("2 Test Product");
        product2.addElement(ing2, 1);
        assertTrue(product1.compareTo(product2) < 0);
        assertTrue(product2.compareTo(product1) > 0);
        assertTrue(product2.compareTo(product2) == 0);
    }
}
