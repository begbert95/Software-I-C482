package main;

import javafx.collections.ObservableList;
//TODO: should this extend?
public class Product extends Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts;
    //TODO : make sure it matches the UML Diagram
    public Product(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
        Inventory.addProduct(this);
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
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part part) {
        // delete part from associated parts
        boolean success;
        try{
            associatedParts.remove(part);
            success = true;
        } catch (Exception e){
            success = false;
        }

        if (!success) {
            System.out.println("Part not deleted");
            return false;
        }
        System.out.println("Part deleted");
        return true;
    }

    public ObservableList<Part> getAssociatedParts() {
        // return associated parts
        return associatedParts;
    }
    //endregion
}
