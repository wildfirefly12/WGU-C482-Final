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

public class ModifyProductController implements Initializable {

    @FXML
    private AnchorPane modProductAnchor;

    @FXML
    private Pane modProductPane;

    @FXML
    private Label modProductLabel;

    @FXML
    private TableView<Part> modProductDeleteTable;

    @FXML
    private TableColumn<Part, String> modProductDeleteIDColumn;

    @FXML
    private TableColumn<Part, String> modProductDeleteNameColumn;

    @FXML
    private TableColumn<Part, String> modProductDeleteInvColumn;

    @FXML
    private TableColumn<Part, String> modProductDeletePriceColumn;

    @FXML
    private TableView<Part> modProductAddTable;

    @FXML
    private TableColumn<Part, String> modProductAddIDColumn;

    @FXML
    private TableColumn<Part, String> modProductAddNameColumn;

    @FXML
    private TableColumn<Part, String> modProductAddInvColumn;

    @FXML
    private TableColumn<Part, String> modProductAddPriceColumn;

    @FXML
    private TextField modProductSearchField;

    @FXML
    private Button modProductSearchButton;

    @FXML
    private Button modProductAddButton;

    @FXML
    private Button modProductDeleteButton;

    @FXML
    private Button modProductCancelButton;

    @FXML
    private Button modProductSaveButton;

    @FXML
    private Label modProductIDLabel;

    @FXML
    private Label modProductInvLabel;

    @FXML
    private Label modProductPriceLabel;

    @FXML
    private Label modProductMaxLabel;

    @FXML
    private Label modProductMinLabel;

    @FXML
    private TextField modProductIDField;

    @FXML
    private TextField modProductNameField;

    @FXML
    private TextField modProductInvField;

    @FXML
    private TextField modProductPriceField;

    @FXML
    private TextField modProductMaxField;

    @FXML
    private TextField modProductMinField;

    @FXML
    private Label modProductNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //modify product
        Product selectedProduct = MainWindowController.getSelectedProduct();

        modProductSaveButton.setOnAction(e -> {
            String name = modProductNameField.getText();
            String price = modProductPriceField.getText();
            String stock = modProductInvField.getText();
            String max = modProductMaxField.getText();
            String min = modProductMinField.getText();

            int id = selectedProduct.getId();

            double partsTotal = 0;

            for (Part part : selectedProduct.getAllAssociatedParts()) {
                double partPrice = part.getPrice();
                partsTotal = partsTotal + partPrice;
            }

            for (Product product : Inventory.getAllProducts()){
                    if (!name.isBlank() && !name.isEmpty()) {
                        selectedProduct.setName(name);
                    }

                    if (!price.isBlank() && !price.isEmpty()) {
                        double newPrice = Double.parseDouble(price);
                        selectedProduct.setPrice(newPrice);
                    }

                    if (!stock.isBlank() && !stock.isEmpty()) {
                        int newStock = Integer.parseInt(stock);
                        selectedProduct.setStock(newStock);
                    }

                    if (!max.isEmpty() && !max.isBlank()) {
                        int newMax = Integer.parseInt(max);
                        selectedProduct.setMax(newMax);
                    }

                    if (!min.isBlank() && !min.isEmpty()) {
                        int newMin = Integer.parseInt(min);
                        selectedProduct.setMin(newMin);
                    }


            }
            if(selectedProduct.getMin() > selectedProduct.getMax()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Minimum value is greater than maximum value.");
                alert.showAndWait();
            } else if(selectedProduct.getStock() > selectedProduct.getMax()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Stock is greater than max stock.");
                alert.showAndWait();
            } else if(selectedProduct.getStock() < selectedProduct.getMin()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Stock is less than min stock.");
                alert.showAndWait();
            } else if(partsTotal > selectedProduct.getPrice()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Price of product is less than the total price of its parts.");
                alert.showAndWait();
            } else if(selectedProduct.getAllAssociatedParts().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No associated part selected.");
                alert.showAndWait();
            } else {
                Inventory.updateProduct(id, selectedProduct);
                Stage stage = (Stage) modProductSaveButton.getScene().getWindow();
                stage.close();
            }
        });

        /*POPULATE PARTS TABLE*/
        modProductAddIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProductAddNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProductAddInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProductAddPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        modProductAddTable.setItems(Inventory.getAllParts());

        /*POPULATE ASSOCIATED PARTS TABLE*/
        modProductDeleteIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProductDeleteNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProductDeleteInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProductDeletePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        modProductDeleteTable.setItems(selectedProduct.getAllAssociatedParts());

        /*Cancel out of window*/
        modProductCancelButton.setOnAction(e -> {
            //return to main screen
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to cancel?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Stage stage = (Stage) modProductCancelButton.getScene().getWindow();
                    stage.close();
                }
            });
        });

        /*Add associated part to product*/
        modProductAddButton.setOnAction(e -> {
            Part selectedPart = modProductAddTable.getSelectionModel().getSelectedItem();
            selectedProduct.addAssociatedPart(selectedPart);
        });

        /*Delete associated part from product*/
        modProductDeleteButton.setOnAction( e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to cancel?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Part selected = modProductDeleteTable.getSelectionModel().getSelectedItem();
                    selectedProduct.deleteAssociatedPart(selected);
                    modProductDeleteTable.setItems(selectedProduct.getAllAssociatedParts());
                }
            });
        });
    }
}
