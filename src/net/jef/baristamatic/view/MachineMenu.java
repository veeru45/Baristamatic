package net.jef.baristamatic.view;

import net.jef.baristamatic.controller.Machine;
import net.jef.baristamatic.model.InventoryItem;
import net.jef.baristamatic.model.Product;

import java.util.Scanner;

/**
 * Provides the view (menu) for the user.  Presents a inventory list with amounts on hand,
 * a list of drinks to be ordered by number.  Passes orders to the machine to dispense.
 * Tells machine to restock and allows exiting the program.
 *
 * Ignores blank input lines, and outputs a blank line between printed lines (per requirements).
 */
public class MachineMenu {

    private final Machine machine;

    public MachineMenu(Machine machine) {
        this.machine = machine;
    }

    private String formatCost(int cost) {
        StringBuilder formattedCost = new StringBuilder();
        formattedCost.append('$');
        int dollars = cost / 100;
        int cents = cost % 100;
        if (dollars > 0) {
            formattedCost.append(dollars);
        }
        formattedCost.append('.');
        formattedCost.append(String.format("%2d", cents));
        return formattedCost.toString();
    }

    private void displayInventory() {
        System.out.println("Inventory:");
        System.out.println();
        for (InventoryItem item : machine.getInventory()) {
            System.out.println(item.getName() + "," + item.getOnhand());
            System.out.println();
        }
    }

    private void displayMenu() {
        System.out.println("Menu:");
        System.out.println();
        int orderNumber = 1;
        for (Product product : machine.getProducts()) {
            System.out.println(orderNumber++ + "," + product.getName() + ","
                    + formatCost(product.getCost()) + "," + machine.productAvailable(product));
            System.out.println();
        }
    }

    private boolean validOrderNumber(int productNumber) {
        return ((productNumber > 0) && (productNumber <= machine.productsSize()));
    }

    private boolean processInput(String input) {
        if (input.equalsIgnoreCase("q")) {
            return false;
        } else if (input.equalsIgnoreCase("r")) {
            machine.restockAll();
            return true;
        }
        // Either a valid order number or some invalid input
        int orderNumber;
        try {
            orderNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // input isn't a number
            System.out.println("Invalid selection: " + input);
            System.out.println();
            return true;
        }
        if (validOrderNumber(orderNumber)) {
            Product product = machine.getProductByIndex(orderNumber - 1);
            if (machine.dispense(product)) {
                System.out.println("Dispensing: " + product.getName());
                System.out.println();
            } else {
                System.out.println("Out of stock: " + product.getName());
                System.out.println();
            }
        } else {
            System.out.println("Invalid selection: " + input);
            System.out.println();
        }
        return true;
    }

    public void start() {
        Scanner inputScanner = new Scanner(System.in);
        String input;
         do {
            displayInventory();
            displayMenu();
            do {
                input = inputScanner.nextLine();
            } while (input.equals(""));  // ignore blank lines
            System.out.println();
         } while (processInput(input));
        inputScanner.close();
    }

}
