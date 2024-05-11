import java.util.HashMap;
import java.util.Map;

// Define a class named "Customer"
class Customer {
    // Declare private fields for name, purchase history, and total spent
    private String name;
    private Map<Product, Integer> purchaseHistory;
    private double totalSpent;

    // Define a constructor that takes a name as an argument
    public Customer(String initName) {
        // Initialize the name field
        name = initName;
        // Initialize the purchaseHistory field with a new HashMap object
        purchaseHistory = new HashMap<>();
        // Initialize the totalSpent field to zero
        totalSpent = 0.0;
    }

    // Define a method to return the name of the customer
    public String getName() {
        return name;
    }

    // Define a method to add a purchase to the purchase history
    public void addPurchase(Product product, int amount) {
        // Get the current quantity of the product in the purchase history, or zero if it doesn't exist
        int quantity = purchaseHistory.getOrDefault(product, 0);
        // Add the new amount to the current quantity in the purchase history
        purchaseHistory.put(product, quantity + amount);
        // Update the total spent by adding the price of the product multiplied by the amount purchased
        totalSpent += product.getPrice() * amount;
    }

    // Define a method to print out the purchase history in a readable format
    public void printPurchaseHistory() {
        // Loop through each product in the purchase history
        for (Product product : purchaseHistory.keySet()) {
            // Get the quantity of the product in the purchase history
            int quantity = purchaseHistory.get(product);
            // Print out the quantity, product name, and total price of the product
            System.out.println(quantity + " x " + product.toString() + " ($" + getProductPrice(product) + ")");
        }
    }

    // Define a private method to calculate the total price of a product based on the quantity purchased
    private double getProductPrice(Product product) {
        // Get the quantity of the product in the purchase history
        int quantity = purchaseHistory.get(product);
        // Calculate the total price by multiplying the quantity by the price per unit of the product
        return quantity * product.getPrice();
    }

    // Define a method to return the total amount spent by the customer
    public double getTotalSpent() {
        return totalSpent;
    }

    // Override the toString() method to return a string representation of the Customer object
    @Override
    public String toString() {
        return name + " who has spent $" + String.format("%.2f", totalSpent);
    }
}