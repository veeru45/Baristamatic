package net.jef.baristamatic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Product that can be dispensed by machine.  Made up of a list of ProductElements.
 */
public class Product implements Comparable<Product> {
    private final String name;
    private final List<ProductElement> elements = new ArrayList<>();

    public Product(String name) { this.name = name; }

    public String getName() { return name; }

    public List<ProductElement> getElements() { return elements; }

    public void addElement(Ingredient ingredient, int amount) {
        elements.add(new ProductElement(ingredient, amount));
    }

    public int getCost() {
        int cost = 0;
        for (ProductElement item : elements) {
            cost += item.getCost();
        }
        return cost;
    }

    @Override
    public int compareTo(Product product) {
        return getName().compareTo(product.getName());
    }
}
