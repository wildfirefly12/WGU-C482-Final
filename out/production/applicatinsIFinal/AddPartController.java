package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    @FXML
    private AnchorPane addPartAnchor;

    @FXML
    private Label addPartLabel;

    @FXML
    private RadioButton addPartInHouseRadio;

    @FXML
    private RadioButton addPartOutsourcedRadio;

    @FXML
    private HBox addPartIDField;

    @FXML
    private TextField addPartNameField;

    @FXML
    private TextField addPartInvField;

    @FXML
    private TextField addPartPriceField;

    @FXML
    private TextField addPartMaxField;

    @FXML
    private TextField addPartMinField;

    @FXML
    private TextField addPartCompNameField;

    @FXML
    private Label addPartNameLabel;

    @FXML
    private Label addPartInvLabel;

    @FXML
    private Label addPartPriceLabel;

    @FXML
    private Label addPartMaxLabel;

    @FXML
    private Label addPartMinLabel;

    @FXML
    private Label addPartCompNameLabel;

    @FXML
    private Label addPartIDLabel;

    @FXML
    private Button addPartCancelButton;

    @FXML
    private Button addPartSaveButton;

    @FXML
    private Label addPartMachIDLabel;

    @FXML
    private TextField addPartMachIDField;

    @FXML
    private GridPane addPartGrid;

    public AddPartController(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //toggle between In-House and Outsourced display
            //when InHouse is selected (selected by default) removes the Outsourced label and field and adds its own
        addPartGrid.getChildren().removeAll(addPartCompNameLabel, addPartCompNameField);

        addPartInHouseRadio.setOnAction(e -> {
                    addPartGrid.getChildren().removeAll(addPartCompNameLabel, addPartCompNameField);
                    addPartGrid.getChildren().addAll(addPartMachIDField, addPartMachIDLabel);
                });
            //when Outsourced is selected, removes the Inhouse label and field and adds its own
            addPartOutsourcedRadio.setOnAction(e -> {
                    addPartGrid.getChildren().removeAll(addPartMachIDField, addPartMachIDLabel);
                    addPartGrid.getChildren().addAll(addPartCompNameField, addPartCompNameLabel);
                });

        //adding part
        addPartSaveButton.setOnAction(e -> {
            //getting the input from the user
                String partName = addPartNameField.getText();
                double price = Double.parseDouble(addPartPriceField.getText());
                int stock = Integer.parseInt(addPartInvField.getText());
                int min = Integer.parseInt(addPartMinField.getText());
                int max = Integer.parseInt(addPartMaxField.getText());

            //if in house is selected, use InHouse constructor to create object and add to AllParts
            if(addPartInHouseRadio.isSelected()){
                if(min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Minimum value is greater than maximum value.");
                    alert.showAndWait();
                } else if(stock > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Stock value is greater than maximum value.");
                    alert.showAndWait();
                } else {
                    int machID = Integer.parseInt(addPartMachIDField.getText());
                    InHouse inhouse = new InHouse(partName, price, stock, min, max, machID);
                    Inventory.getAllParts().addAll(inhouse);
                }

            //if outsourced is selected, use outsourced constructor to create object and add to AllParts
            } else if(addPartOutsourcedRadio.isSelected()){
                if(min > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Minimum value is greater than maximum value.");
                    alert.showAndWait();
                } else if(stock > max) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Stock value is greater than maximum value.");
                    alert.showAndWait();
                } else {
                    String compName = addPartCompNameField.getText();
                    Outsourced outsourced = new Outsourced(partName, price, stock, min, max, compName);
                    Inventory.getAllParts().addAll(outsourced);
                }
            }

            //return to main screen
            Stage stage = (Stage) addPartSaveButton.getScene().getWindow();
            stage.close();

        });

        //return to main screen without saving
        addPartCancelButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to cancel?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Stage stage = (Stage) addPartCancelButton.getScene().getWindow();
                    stage.close();
                }
            });

        });
    }
}

