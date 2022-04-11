import java.util.Observable;

public class Inventory {
    private static allParts;
    private static allProducts;

    //constructor
    public Inventory() {
        allParts = new ArrayList<Part>();
        allProducts = new ArrayList<Product>();
    }

    public static void addPart(newPart: Part) {
        allParts.add(newPart);
    }

    public static void deletePart (selectedPart:Part){
        allParts.remove(selectedPart);
    };

    public static void updatePart;
    public static void addProduct;
    public static void deleteProduct;
    public static void updateProduct;
    public static getAllParts;
    public static getAllProducts;
}
