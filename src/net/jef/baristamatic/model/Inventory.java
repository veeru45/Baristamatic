package net.jef.baristamatic.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Manageable list of InventoryItems. Favors composition over inheritance. Provides added functionality
 * of checking inventory levels, and decreasing stock, and restocking all items to capacity.
 */
public class Inventory implements Iterable<InventoryItem> {
    private final Set<InventoryItem> inventorySet = new TreeSet<>();

    private InventoryItem inventoryItemByIngredient(Ingredient ingredient) {
        for (InventoryItem item : inventorySet) {
            if (item.getIngredient().equals(ingredient)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Ingredient ingredient) {
        int DEFAULT_CAPACITY = 10;
        InventoryItem item = new InventoryItem(ingredient, DEFAULT_CAPACITY);
        inventorySet.add(item);
    }

    public boolean ingredientAvailable(Ingredient ingredient, int amount) {
        InventoryItem item = inventoryItemByIngredient(ingredient);
        if (item == null) {
            return false;
        }
        return item.getOnhand() >= amount;
    }

    public void decreaseIngredient(Ingredient ingredient, int amount) {
        InventoryItem item = inventoryItemByIngredient(ingredient);
        if (item != null) {
            item.decreaseStock(amount);
        }
    }

    public void restockAll() {
         for (InventoryItem item : inventorySet) {
             item.restock();
         }
     }

    @Override
    public Iterator<InventoryItem> iterator() {
         // Protect against modifications via iterator.
        return Collections.unmodifiableSet(inventorySet).iterator();
    }
}
