public class Assignment3LoadTester {
    public static void main(String[] args){
        System.out.println("Loading a store from a file that doesn't exist, should be null: " + ElectronicStore.loadFromFile("doesntexist.txt"));

        System.out.println("Loading a store for file that does exist (assuming you have run Assignment3Tester already)...");
        ElectronicStore store = ElectronicStore.loadFromFile("store.txt");

        System.out.println("Printing out all customers sorted by amount of money spent");
        System.out.println("First 4 should be: Tom B. (17018), Galadriel (10000), Frodo (8200), Fredigar (6000)");
        System.out.println("Followed by 5 others in any order with $0 each");
        for(Customer c: store.getTopXCustomers(store.getCustomers().size()+1)){
            System.out.println(c);
        }
        for(Customer c: store.getCustomers()){
            if(c.getName().equals("Tom B.")){
                System.out.println("Printing Tom B.'s purchases");
                System.out.println("Should have 4xDesktop, 1xFridge, 5xOvens");
                c.printPurchaseHistory();
            }
        }
    }
}
