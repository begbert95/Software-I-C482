package src.softwareic482.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.softwareic482.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PartController implements Initializable {
    @FXML
    private Button addPartAddButton;
    @FXML Button addPartCancelButton;
    @FXML
    private Label errorBox;
    private Label srcLabel;
    @FXML
    private TextField srcText;
    @FXML
    private TextField addPartID;
    @FXML
    private TextField addPartName;
    @FXML
    private TextField addPartPrice;
    @FXML
    private TextField addPartStock;
    @FXML
    private TextField addPartMin;
    @FXML
    private TextField addPartMax;
    @FXML
    private RadioButton newPartInHouseRadio;
    @FXML
    private RadioButton newPartOutsourcedRadio;


    private int partID, machineID, partStock, partMin, partMax;
    private double partPrice;
    private String partName, partCompany;
    private char partSource;


/*
    public void start () throws Exception{
        setPartID();
        disableSourceFields();
        Parent root = FXMLLoader.load(getClass().getResource("/view/PartView.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 800);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start (Stage stage) throws Exception {


        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("/view/PartView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Inventory Management System - Add Part");
        stage.setScene(scene);
        stage.show();
    } */

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        newPartID();
        disableSourceFields();
    }

    @FXML
    protected void toHome (ActionEvent actionEvent) throws IOException{

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomeView.fxml")));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 800);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    protected void partAddAction (ActionEvent actionEvent) throws IOException {
        if(extractPartData()){
            createPart();
        }
        toHome(actionEvent);

    }

    protected void partCancelAction (ActionEvent actionEvent) throws IOException{
        toHome(actionEvent);
    }
    protected void newPartID (){
        partID = Main.getNextID("part");
        addPartID.setText(String.valueOf(partID));
    }
    protected void enableSourceFields(){
        srcLabel.setVisible(true);
        srcLabel.setDisable(false);
        srcText.setDisable(false);
        srcText.setVisible(true);
    }

    protected void disableSourceFields(){
        srcLabel.setVisible(false);
        srcLabel.setDisable(true);
        srcText.setDisable(true);
        srcText.setVisible(false);
    }

    protected void createPart(){
        if(partSource == 'i'){
            new InHouse(partID, partName, partPrice, partStock, partMin, partMax, machineID);
        } else if(partSource == 'o'){
            new Outsourced(partID, partName, partPrice, partStock, partMin, partMax, partCompany);
        }
    }

    @FXML
    protected void showInHouse(ActionEvent actionEvent){
        newPartInHouseRadio.setSelected(true);
        newPartOutsourcedRadio.setSelected(false);
        srcLabel.setText("Machine ID");
        partSource = 'i';
        enableSourceFields();
    }

    @FXML
    protected void showOutsourced(ActionEvent actionEvent){
        newPartInHouseRadio.setSelected(false);
        newPartOutsourcedRadio.setSelected(true);
        srcLabel.setText("Company Name");
        partSource = 'o';
        enableSourceFields();
    }
    protected void showError(String s){
        errorBox.setText(s);
        errorBox.setVisible(true);
        errorBox.setDisable(false);
    }

    protected boolean extractPartData(){

        try{
            partStock = Integer.parseInt(addPartStock.getText());
        }catch(Exception e){
            showError("Invalid stock data type! Must be a whole number");
            return false;
        }

        try{
            partMin = Integer.parseInt(addPartMin.getText());
        }catch(Exception e){
            showError("Invalid 'minimum' data type! Must be a whole number");
            return false;
        }

        try{
            partMax = Integer.parseInt(addPartMax.getText());
        }catch(Exception e){
            showError("Invalid 'maximum' data type! Must be a whole number");
            return false;
        }

        try{
            partName = addPartName.getText();
        }catch(Exception e){
            showError("Invalid 'name' data type! Must be text");
            return false;
        }

        try{
            partPrice = Double.parseDouble(addPartPrice.getText());
        }catch(Exception e){
            showError("Invalid 'price' data type! Must be a number");
            return false;
        }

        if(partMin > partMax){
            showError("The minimum cannot be greater than the maximum!");
        }
        else if(partStock < partMin){
            showError("Stock cannot be less than the minimum!");
        }
        else if(partStock > partMax){
            showError("Stock cannot be greater than the maximum!");
        }
        else {
            return true;
        }
        return false;
    }

    public void showPart(int n){
        //TODO figure out what I should do for this so it properly shows InHouse vs Outsourced. Overload?
        Inventory.lookupPart(n);

        if(Inventory.lookupPart(n) instanceof InHouse){
            InHouse p = (InHouse) Inventory.lookupPart(n);
            newPartInHouseRadio.setSelected(true);
            newPartOutsourcedRadio.setSelected(false);
            enableSourceFields();
            srcText.setText(String.valueOf(p.getMachineID()));
            addPartID.setText(String.valueOf(p.getId()));
            addPartName.setText(p.getName());
            addPartPrice.setText(String.valueOf(p.getPrice()));
            addPartStock.setText(String.valueOf(p.getStock()));
            addPartMin.setText(String.valueOf(p.getMin()));
            addPartMax.setText(String.valueOf(p.getMax()));
        }
        else if (Inventory.lookupPart(n) instanceof Outsourced){
            Outsourced p = (Outsourced) Inventory.lookupPart(n);
            newPartInHouseRadio.setSelected(false);
            newPartOutsourcedRadio.setSelected(true);
            enableSourceFields();
            srcText.setText(String.valueOf(p.getCompanyName()));
            addPartID.setText(String.valueOf(p.getId()));
            addPartName.setText(p.getName());
            addPartPrice.setText(String.valueOf(p.getPrice()));
            addPartStock.setText(String.valueOf(p.getStock()));
            addPartMin.setText(String.valueOf(p.getMin()));
            addPartMax.setText(String.valueOf(p.getMax()));
        }

    }


}
