package main;

import javafx.collections.*;

public class Inventory {

    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;


    public static void addPart (Part part) {
        allParts.add(part);
    }
    public static Part lookupPart(int partID){
        return allParts.get(partID);
    }

    public static void updatePart(int partID, Part updatedPart) {
        allParts.set(partID, updatedPart);
    }
    public static boolean deletePart (Part part){
        try {
            allParts.remove(part);
            return true;
        } catch (Exception e){
            return false;
        }
    }




    public static void addProduct(Product prod) {
        allProducts.add(prod);
    }
    public static Product lookupProduct (int prodID){
        return allProducts.get(prodID);
    }
    public static Part lookupPart(String partString){
        //TODO : figure out how to get what I want from here
        ObservableList<Part> partObservableList = getAllParts();
        //ObservableList<Part> returnList = FXCollections.observableArrayList();
        for (Part partLooper: partObservableList) {
            if(partLooper.getName().contains(partString)){
                //returnList.add(partLooper);
                return partLooper;
            }
        }
        return null;
    }

    public static void updateProduct(int prodID, Product newProduct){
        allProducts.set(prodID, newProduct);
    }
    public static boolean deleteProduct(Product product) {
        try {
            allProducts.remove(product);
            return true;
        }catch (Exception e) {
            return false;
        }
    }



    public static ObservableList<Part> getAllParts(){
        return  allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
