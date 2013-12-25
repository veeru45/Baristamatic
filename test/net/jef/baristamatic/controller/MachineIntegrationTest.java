package net.jef.baristamatic.controller;

import net.jef.baristamatic.dataaccess.BaristaData;
import net.jef.baristamatic.model.Ingredients;
import net.jef.baristamatic.model.Inventory;
import net.jef.baristamatic.model.Product;
import net.jef.baristamatic.model.Products;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test is a coarse integration test.  There are gains to be had from breaking this into a true unit
 * test and mocking dependencies.  For the purposes of not over complicating the interview problem, I did not do that.
 * This test provides 100% code coverage but doesn't necessarily give granular, immediate viability to the exact failure.
 */
public class MachineIntegrationTest {
    Ingredients ingredients;
    Inventory inventory;
    Products products;
    Machine machine;

    @Before
    public void setup() {
        BaristaData data = new BaristaData();
        ingredients = data.createIngredients();
        inventory = data.createAndStockInventory(ingredients);
        products = data.createProducts(ingredients);
        machine = new Machine(inventory, products);
    }

    @Test
    public void testProductSize() {
        assertEquals(6, machine.productsSize());
    }

    @Test
    public void testGetProductByIndex() {
        Product prod1 = machine.getProductByIndex(0);
        assertEquals("Caffe Americano", prod1.getName());
        assertEquals(330, prod1.getCost());
    }

    @Test
    public void testDispenseAndAvailableAndRestock() {
        Product prod1 = machine.getProductByIndex(0);
        assertTrue(machine.productAvailable(prod1));
        assertTrue(machine.dispense(prod1));
        assertTrue(machine.productAvailable(prod1));
        assertTrue(machine.dispense(prod1));
        assertTrue(machine.productAvailable(prod1));
        assertTrue(machine.dispense(prod1));
        assertFalse(machine.productAvailable(prod1));
        assertFalse(machine.dispense(prod1));
        machine.restockAll();
        assertTrue(machine.productAvailable(prod1));
        assertTrue(machine.dispense(prod1));
    }

    @Test
    public void testGetters() {
        inventory = machine.getInventory();
        products = machine.getProducts();
        assertTrue(inventory instanceof Inventory);
        assertTrue(products instanceof Products);
    }
}
