import java.util.ObservableList;

public class Product extends Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    // region **************************************** SETTERS
    // ****************************************
    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }
    // endregion


    // region **************************************** GETTERS ****************************************
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    //endregion


    // region **************************************** OBSERVABLE ***************************************
    public void addAssociatedPart(Part part) {
        // add part to associated parts
    }

    public boolean deleteAssociatedPart(Part part) {
        // delete part from associated parts

        if (success == true) {
            System.out.println("Part deleted");
            return true;
        } else {
            System.out.println("Part not deleted");
            return false;
        }
    }

    public ObservableList<Part> getAssociatedParts() {
        // return associated parts
    }
    //endregion
}
