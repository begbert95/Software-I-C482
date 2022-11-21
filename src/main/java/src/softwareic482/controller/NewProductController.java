package src.softwareic482.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import src.softwareic482.Inventory;
import src.softwareic482.Main;
import src.softwareic482.Part;
import src.softwareic482.Product;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewProductController implements Initializable {
    public Button addProductCancel;
    public Button addProductAdd;
    public Button newProdAddPart;
    public Button newProdDropPart;
    @FXML
    private TextField productIDField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField productStockField;
    @FXML
    private TextField productMinField;
    @FXML
    private TextField productMaxField;
    @FXML
    private TextField searchPartListField;
    @FXML
    private TableView<Part> addProductPartTable;
    /*
    private TableColumn<Part, Integer> partIDColumn;
    private TableColumn<Part, String> partNameColumn;
    private TableColumn<Part, Double> partPriceColumn;
    private TableColumn<Part, Integer> partStockColumn;
    */
    @FXML
    private TableView<Part> addProductAssocParts;

    private ObservableList<Part> allParts = Inventory.getAllParts();
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();
    private int partID;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        partID = Main.getNextID("product");
        productIDField.setText(String.valueOf(partID));
        addProductPartTable.setItems(allParts);
        addProductAssocParts.setItems(assocParts);
    }
    public void addPartToProduct(){
        Part p = addProductPartTable.getSelectionModel().getSelectedItem();

        allParts.remove(p);
        assocParts.add(p);

        //shouldn't need this part
        //addProductPartTable.setItems(allParts);
        //addProductAssocParts.setItems(assocPart);
    }

    @FXML
    private void removePartFromProduct(){
        Part p = addProductAssocParts.getSelectionModel().getSelectedItem();

        assocParts.remove(p);
        allParts.add(p);
    }

    public void toHome (ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/IMSHomeScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    public void setProductData(){
        //TODO pending verification on if I should have associated parts in the constructor
        ObservableList<Part> selected = addProductAssocParts.getItems();
        Product newP = new Product(Integer.parseInt(productIDField.getText()), productNameField.getText(), Double.parseDouble(productPriceField.getText()), Integer.parseInt(productStockField.getText()), Integer.parseInt(productMinField.getText()), Integer.parseInt(productMaxField.getText()));
        for (Part p: selected ) {
            newP.addAssociatedPart(p);
        }
    }
    public void addProductAdd(ActionEvent actionEvent) throws IOException {
        setProductData();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomeView.fxml")));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();

    }
    private void blankPage(){
        productIDField.setText(String.valueOf(Main.getNextID("product")));
        productNameField.setText("");
        productPriceField.setText("");
        productStockField.setText("");
        productMinField.setText("");
        productMaxField.setText("");
        addProductPartTable.setItems(Inventory.getAllParts());
        ObservableList<Part> assocPart = FXCollections.observableArrayList();
        addProductAssocParts.setItems(assocPart);
    }
    private void displayProduct(Product prod){
        productIDField.setText(String.valueOf(prod.getId()));
        productNameField.setText(prod.getName());
        productPriceField.setText(String.valueOf(prod.getPrice()));
        productStockField.setText(String.valueOf(prod.getStock()));
        productMinField.setText(String.valueOf(prod.getMin()));
        productMaxField.setText(String.valueOf(prod.getMax()));
        addProductPartTable.setItems(Inventory.getAllParts());
        addProductAssocParts.setItems(prod.getAssociatedParts());
    }

    @FXML
    private void searchPartList(KeyEvent actionEvent){
        String s = searchPartListField.getText();
        ObservableList<Part> searchItems = FXCollections.observableArrayList();
        try{
            int i = Integer.parseInt(s);
            for(Part p : allParts){
                if(p.getId() == i){
                    searchItems.add(p);
                    addProductPartTable.setItems(searchItems);
                }
            }
        } catch (NumberFormatException e) {
            for (Part p : allParts){
                if(p.getName().contains(s)){
                    searchItems.add(p);
                    addProductPartTable.setItems(searchItems);
                }
            }
        }
    }
}
