package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    @FXML
    private AnchorPane addProductAnchor;

    @FXML
    private Pane addProductPane;

    @FXML
    private Label addProductLabel;

    @FXML
    private TableView<Part> addProductDeleteTable;

    @FXML
    private TableColumn<Part, String> addProductDeleteIDColumn;

    @FXML
    private TableColumn<Part, String> addProductDeleteNameColumn;

    @FXML
    private TableColumn<Part, String> addProductDeleteInvColumn;

    @FXML
    private TableColumn<Part, String> addProductDeletePriceColumn;

    @FXML
    private TableView<Part> addProductAddTable;

    @FXML
    private TableColumn<Part, String> addProductAddIDColumn;

    @FXML
    private TableColumn<Part, String> addProductAddNameColumn;

    @FXML
    private TableColumn<Part, String> addProductAddInvColumn;

    @FXML
    private TableColumn<Part, String> addProductAddPriceColumn;

    @FXML
    private Label addProductIDLabel;

    @FXML
    private Label addProductInvLabel;

    @FXML
    private Label addProductPriceLabel;

    @FXML
    private Label addProductMaxLabel;

    @FXML
    private Label addProductMinLabel;

    @FXML
    private TextField addProductPartSearchField;

    @FXML
    private Button addProductPartSearchButton;

    @FXML
    private Button addProductAddButton;

    @FXML
    private Button addProductDeleteButton;

    @FXML
    private Button addProductCancelButton;

    @FXML
    private Button addProductSaveButton;

    @FXML
    private TextField addProductIDField;

    @FXML
    private TextField addProductNameField;

    @FXML
    private TextField addProductInvField;

    @FXML
    private TextField addProductPriceField;

    @FXML
    private TextField addProductMaxField;

    @FXML
    private TextField addProductMinField;

    @FXML
    private Label addProductNameLabel;

    public AddProductController(){

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Product newProduct = new Product("", 0, 0, 0, 0);

        /*POPULATE PARTS TABLE*/
        addProductAddIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductAddNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductAddInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductAddPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductAddTable.setItems(Inventory.getAllParts());

        /*POPULATE ASSOCIATED PARTS TABLE*/
        addProductDeleteIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductDeleteNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductDeleteInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductDeletePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductDeleteTable.setItems(newProduct.getAllAssociatedParts());

        //Product newProduct;

        addProductSaveButton.setOnAction( e -> {
            //get user input
                String name = addProductNameField.getText();
                double price = Double.parseDouble(addProductPriceField.getText());
                int inv = Integer.parseInt(addProductInvField.getText());
                int min = Integer.parseInt(addProductMinField.getText());
                int max = Integer.parseInt(addProductMaxField.getText());
                double partsTotal = 0;

                for(Part part : newProduct.getAllAssociatedParts()){
                    double partPrice = part.getPrice();
                    partsTotal = partsTotal + partPrice;
                }

            //use product constructor to create new object/add product to allProducts
                if(min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Minimum value is greater than maximum value.");
                    alert.showAndWait();
                } else if(inv > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Stock is greater than max stock.");
                    alert.showAndWait();
                } else if(inv < min) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Stock is less than min stock.");
                    alert.showAndWait();
                } else if(partsTotal > price) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Price of product is less than the total price of its parts.");
                    alert.showAndWait();
                } else if(name.isEmpty() || name.isBlank() || name == "") {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please enter a product name.");
                        alert.showAndWait();
                } else if(price <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter a valid price.");
                    alert.showAndWait();
                } else {
                    newProduct.setName(name);
                    newProduct.setPrice(price);
                    newProduct.setMin(min);
                    newProduct.setMax(max);
                    Inventory.getAllProducts().add(newProduct);
                    addProductDeleteTable.setItems(newProduct.getAllAssociatedParts());
                }

            //return to main screen
                if(newProduct.getAllAssociatedParts().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No associated part selected.");
                    alert.showAndWait();
                } else {
                    Stage stage = (Stage) addProductSaveButton.getScene().getWindow();
                    stage.close();
                }
        });

        /*CANCEL*/
        addProductCancelButton.setOnAction(e -> {
            //return to main screen
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to cancel?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Stage stage = (Stage) addProductCancelButton.getScene().getWindow();
                    stage.close();
                }
            });
        });

        /*ADD ASSOCIATED PART*/
            addProductAddButton.setOnAction(e -> {
                Part newPart = addProductAddTable.getSelectionModel().getSelectedItem();
                newProduct.addAssociatedPart(newPart);
                addProductDeleteTable.setItems(newProduct.getAllAssociatedParts());
            });

        /*DELETE ASSOCIATED PART*/
            addProductDeleteButton.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure you want to cancel?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        Part newPart = addProductDeleteTable.getSelectionModel().getSelectedItem();
                        newProduct.deleteAssociatedPart(newPart);
                        addProductDeleteTable.setItems(newProduct.getAllAssociatedParts());
                    }
                });
            });
    }
}