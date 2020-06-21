package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {

    @FXML
    private AnchorPane mainWindowAnchor;

    @FXML
    private Label mainTitleLabel;

    @FXML
    private Pane mainPartPane;

    @FXML
    private Label mainPartsLabel;

    @FXML
    private TextField mainPartsSearchField;

    @FXML
    private Button mainPartsSearchButton;

    @FXML
    private TableView<Part> mainPartsTable;

    @FXML
    private TableColumn<Part, String> mainPartIDColumn;

    @FXML
    private TableColumn<Part, String> mainPartNameColumn;

    @FXML
    private TableColumn<Part, String> mainPartInvColumn;

    @FXML
    private TableColumn<Part, String> mainPartPriceColumn;

    @FXML
    private Button mainPartsAddButton;

    @FXML
    private Button mainPartsDeleteButton;

    @FXML
    private Button mainPartsModifyButton;

    @FXML
    private Button mainExitButton;

    @FXML
    private Pane mainProductsPane;

    @FXML
    private Label mainProductsLabel;

    @FXML
    private TextField mainProductSearchField;

    @FXML
    private Button mainProductSearchButton;

    @FXML
    private Button mainProductAddButton;

    @FXML
    private Button mainProductModifyButton;

    @FXML
    private Button mainProductDeleteButton;

    @FXML
    private TableView<Product> mainProductTable;

    @FXML
    private TableColumn<Product, String> mainProductIDColumn;

    @FXML
    private TableColumn<Product, String> mainProductNameColumn;

    @FXML
    private TableColumn<Product, String> mainProductInvColumn;

    @FXML
    private TableColumn<Product, String> mainProductPriceColumn;

    public MainWindowController() {
    }

    /*GET SELECTED PART & ID*/
            private static Part selectedPart;
            public static void setSelectedPart(Part selected){
                selectedPart = selected;
            }
            public static Part getSelectedPart() {
                return selectedPart;
            }

    /*GET SELECTED PRODUCT & ID*/
        private static Product selectedProduct;
        public static void setSelectedProduct(Product selected){
            selectedProduct = selected;
        }
        public static Product getSelectedProduct(){
            return selectedProduct;
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*OPEN OTHER WINDOWS*/
            //Add parts window opens
            mainPartsAddButton.setOnAction(e -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("AddPart.fxml"));
                    Parent parent = fxmlLoader.load();
                    Stage newWindow = new Stage();
                    newWindow.initModality(Modality.APPLICATION_MODAL);
                    newWindow.setTitle("Add Part");
                    newWindow.setScene(new Scene(parent));
                    newWindow.show();
                } catch (IOException error) {
                    error.printStackTrace();
                }
            });

            //add products window opens
            mainProductAddButton.setOnAction(e -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("AddProduct.fxml"));
                    Parent parent = fxmlLoader.load();
                    Stage newWindow = new Stage();
                    newWindow.initModality(Modality.APPLICATION_MODAL);
                    newWindow.setTitle("Add Product");
                    newWindow.setScene(new Scene(parent));
                    newWindow.show();
                } catch (IOException error) {
                    error.printStackTrace();
                }
            });



            //modify parts window opens
            mainPartsModifyButton.setOnAction(e -> {
                Part selected = mainPartsTable.getSelectionModel().getSelectedItem();
                MainWindowController.setSelectedPart(selected);
                if (selected == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No part is selected.");
                    alert.showAndWait();
                } else {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyPart.fxml"));
                        Parent parent = fxmlLoader.load();
                        Stage newWindow = new Stage();
                        newWindow.initModality(Modality.APPLICATION_MODAL);
                        newWindow.setTitle("Modify Part");
                        newWindow.setScene(new Scene(parent));
                        newWindow.show();
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                }
            });

            //modify products window opens
            mainProductModifyButton.setOnAction(e -> {
                Product selected = mainProductTable.getSelectionModel().getSelectedItem();
                MainWindowController.setSelectedProduct(selected);

                if (selected == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No product is selected.");
                    alert.showAndWait();
                } else {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ModifyProduct.fxml"));
                        Parent parent = fxmlLoader.load();
                        Stage newWindow = new Stage();
                        newWindow.initModality(Modality.APPLICATION_MODAL);
                        newWindow.setTitle("Modify Product");
                        newWindow.setScene(new Scene(parent));
                        newWindow.show();
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                }
            });

        /*ADD CONTENT TO TABLES*/
            //Parts
                mainPartIDColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
                mainPartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
                mainPartInvColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
                mainPartPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));
                mainPartsTable.setItems(Inventory.getAllParts());

                //populating test items
                InHouse one = new InHouse("one", 2, 3, 4, 5, 1);
                Outsourced two = new Outsourced("two", 3, 4, 5, 6, "seven");
                InHouse three = new InHouse("three", 4, 5, 6, 7, 8);

                Inventory.getAllParts().addAll(one, two, three);

            //Products
                mainProductIDColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
                mainProductNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
                mainProductInvColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
                mainProductPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
                mainProductTable.setItems(Inventory.getAllProducts());

                Product four = new Product("one", 30, 3, 1, 3);
                Product five = new Product("two", 10, 1, 2, 4);

                Inventory.getAllProducts().addAll(four, five);

        /*EXIT*/
            mainExitButton.setOnAction(e -> {
                Stage stage = (Stage) mainExitButton.getScene().getWindow();
                stage.close();
            });

        /*SEARCH*/
            mainPartsSearchButton.setOnAction(e -> {

                String search = mainPartsSearchField.getText();
                ObservableList<Part> filteredPartList = FXCollections.observableArrayList();

                try{
                     Part intSearch = Inventory.lookupPart(Integer.parseInt(search));
                     filteredPartList.add(intSearch);
                }catch(Exception isString){
                    filteredPartList = Inventory.lookupPart(search);
                }
                mainPartsTable.setItems(filteredPartList);

                if(search.isEmpty() || search.isBlank()){
                    mainPartsTable.setItems(Inventory.getAllParts());
                }
            });

            mainProductSearchButton.setOnAction(e -> {
                String search = mainProductSearchField.getText();
                ObservableList<Product> filteredProductList = FXCollections.observableArrayList();

                try{
                    Product intSearch = Inventory.lookupProduct(Integer.parseInt(search));
                    filteredProductList.add(intSearch);
                } catch (Exception isString){
                    filteredProductList = Inventory.lookupProduct(search);
                }
                mainProductTable.setItems(filteredProductList);

                if(search.isBlank() || search.isEmpty()){
                    mainProductTable.setItems(Inventory.getAllProducts());
                }
            });

        /*DELETE ITEMS*/
            mainPartsDeleteButton.setOnAction( e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure you want to delete?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        Part selected = mainPartsTable.getSelectionModel().getSelectedItem();
                        Inventory.deletePart(selected);
                        mainPartsTable.setItems(Inventory.getAllParts());
                    }
                });
            });

        mainProductDeleteButton.setOnAction( e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Product selected = mainProductTable.getSelectionModel().getSelectedItem();
                    Inventory.deleteProduct(selected);
                    mainProductTable.setItems(Inventory.getAllProducts());
                }
                });
        });
    }

}


