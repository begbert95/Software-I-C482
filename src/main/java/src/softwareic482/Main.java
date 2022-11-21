package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
        primaryStage.setTitle("Inventory Management System - Home");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main (String[] args) {

        //presetting some initial data for testing. In an actual production environment, this would be saved to a database
        Product product1 = new Product(1, "fried chicken", 4.20, 7, 1, 5);
        Product product2 = new Product(3, "roasted lamb", 7, 5, 3, 7);
        Product product3 = new Product(4, "brisket", 6.90, 30, 1, 2);
        Product product4 = new Product(7, "filet mignon", 13, 4, 1, 1);
        Product product5 = new Product(9, "raw chicken", 1, 69, 6, 420);


        launch(args);
    }

    public static int getNextID (String itemType) {
        //NOTE: shouldn't need any validators because this is only called within the program itself
        // In a real environment, it would be good to include anyway since there may be a lot of code
        // and can help troubleshoot bugs

        int nextID = 0;
        int itemID = 0;
        Inventory inv = new Inventory();


        if (itemType == "part") {
            ObservableList<Part> list = Inventory.getAllParts();
            for (Part part : list) {
                itemID = part.getId();
                if (itemID > nextID) {
                    nextID = itemID;
                }
            }
        } else if (itemType == "product") {
            ObservableList<Product> list = Inventory.getAllProducts();
            for (Product prod : list) {
                itemID = prod.getId();
                if (itemID > nextID) {
                    nextID = itemID;
                }
            }
        }
        return nextID;
    }



}


