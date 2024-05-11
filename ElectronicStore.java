// Importing necessary libraries
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

// Defining a class named ElectronicStore
public class ElectronicStore {
    // Defining instance variables
    private String name;
    private double revenue;
    private List<Product> products;
    private List<Customer> customers;

    // Constructor for the ElectronicStore class
    public ElectronicStore(String name) {
        this.name = name;
        revenue = 0.0;
        products = new ArrayList<>();
        customers = new ArrayList<>();
    }

    // Method to get the name of the store
    public String getName() {
        return name;
    }

    // Method to add a product to the store's inventory
    public boolean addProduct(Product product) {
        for (Product p : products) {
            if (p.toString().equalsIgnoreCase(product.toString())) {
                return false; // product already exists
            }
        }
        products.add(product);
        return true;
    }

    // Method to register a new customer to the store
    public boolean registerCustomer(Customer customer) {
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(customer.getName())) {
                return false; // customer already exists
            }
        }
        customers.add(customer);
        return true;
    }

    // Method to get a list of unique customers
    public List<Customer> getCustomers() {
        List<Customer> uniqueCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (!uniqueCustomers.contains(customer)) {
                uniqueCustomers.add(customer);
            }
        }
        return uniqueCustomers;
    }

    // Method to search for products by name
    public List<Product> searchProducts(String x) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.toString().toLowerCase().contains(x.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // Method to search for products by name and price range
    public List<Product> searchProducts(String x, double minPrice, double maxPrice) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products) {
            String productString = product.toString().toLowerCase();
            if (productString.contains(x.toLowerCase())
                    && (minPrice < 0 || product.getPrice() >= minPrice)
                    && (maxPrice < 0 || product.getPrice() <= maxPrice)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // Method to add stock to a product
    public boolean addStock(Product product, int amount) {
        for (Product p : products) {
            if (p.toString().equalsIgnoreCase(product.toString())) {
                int currentStock = product.getStockQuantity();
                product.setStockQuantity(currentStock + amount);
                return true;// product found and stock updated
            }
        }
        return false; // product not found
    }

    // Method to sell a product to a customer
    public boolean sellProduct(Product product, Customer customer, int amount) {
        if (!customers.contains(customer) || !products.contains(product) || product.getStockQuantity() < amount) {
            return false; // invalid sale
        }
        customer.addPurchase(product, amount);
        revenue += product.sellUnits(amount);
        return true; // successful sale
    }

    // Method to get the top X customers by total amount spent
    public List<Customer> getTopXCustomers(int x) {
        List<Customer> sortedCustomers = new ArrayList<>(customers);
        sortedCustomers.sort(Comparator.comparing(Customer::getTotalSpent).reversed());
        return sortedCustomers.subList(0, Math.min(x, sortedCustomers.size()));
    }

    public boolean saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this); // write current object to file
            return true; // success
        } catch (IOException e) { // handle IO exceptions
            e.printStackTrace();
            return false; // failed to write to file
        }
    }

    public static ElectronicStore loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ElectronicStore) ois.readObject(); // read object from file
        } catch (IOException | ClassNotFoundException e) { // handle IO and class not found exceptions
            e.printStackTrace();
            return null; // failed to read from file
        }
    }
}
