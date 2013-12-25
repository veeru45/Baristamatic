package net.jef.baristamatic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * List of all products available to dispense.  Essentially a List, yet favors composition over inheritance.
 * Added functionality of keeping list sorted on addition of a new product.
 */
public class Products implements Iterable<Product> {
    private final List<Product> productsList = new ArrayList<>();

    public void addProduct(Product product) {
        productsList.add(product);
        Collections.sort(productsList);
    }

    public Product getProduct(int index) {
        return productsList.get(index);
    }

    public int size() {
        return productsList.size();
    }

    @Override
    public Iterator<Product> iterator() {
        // Protect against modification via iterator.
        return Collections.unmodifiableList(productsList).iterator();
    }
}
