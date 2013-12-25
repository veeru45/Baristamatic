package net.jef.baristamatic.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a coarsely grained unit test.  It does not mock dependencies.  Doing so, and breaking the test
 * into smaller pieces would provide value for code that will be used often and part of a Continuous Integration
 * type of environment.
 */
public class ProductsTest {

    @Test
    public void testCreationAndAddAndGetAndIterator() {
        Products products = new Products();
        Product product = new Product("Test Product");
        products.addProduct(product);
        assertTrue(products.size() == 1);
        assertEquals(product, products.getProduct(0));
        for(Product prod : products) {
            assertEquals(product, prod);
        }
    }


}
