package net.jef.baristamatic;

import net.jef.baristamatic.controller.Machine;
import net.jef.baristamatic.dataaccess.BaristaData;
import net.jef.baristamatic.model.Ingredients;
import net.jef.baristamatic.model.Inventory;
import net.jef.baristamatic.model.Products;
import net.jef.baristamatic.view.MachineMenu;

/**
 * Main class for simulating / exercising Baristamatic.
 *      Create a data loader.
 *      Create and populate model.
 *      Create controller.
 *      Create view and pass in controller.
 */
public class Baristamatic {
    public static void main(String[] args) {
        // Create a data loader
        BaristaData baristaData = new BaristaData();
        // Load model.  Ingredients are base data used as reference to create both Inventory and Products.
        Ingredients ingredients = baristaData.createIngredients();
        Inventory inventory = baristaData.createAndStockInventory(ingredients);
        Products products = baristaData.createProducts(ingredients);
        // Create a machine
        Machine machine = new Machine(inventory, products);
        // Create the interface and pass in the machine
        MachineMenu machineMenu = new MachineMenu(machine);
        // Start conversation with user
        machineMenu.start();
    }
}
