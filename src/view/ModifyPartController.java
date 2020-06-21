package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    @FXML
    private Label modifyPartLabel;

    @FXML
    private RadioButton modPartInHouseRadio;

    @FXML
    private RadioButton modPartOutsourcedRadio;

    @FXML
    private GridPane modPartGrid;

    @FXML
    private Label modPartMachIDLabel;

    @FXML
    private TextField modPartMachIDField;

    @FXML
    private Label modPartCompNameLabel;

    @FXML
    private TextField modPartCompNameField;

    @FXML
    private Label modPartIDLabel;

    @FXML
    private Label modPartNameLabel;

    @FXML
    private Label modPartInvLabel;

    @FXML
    private Label modPartPriceLabel;

    @FXML
    private Label modPartMaxLabel;

    @FXML
    private Label modPartMinLabel;

    @FXML
    private HBox modPartIDField;

    @FXML
    private TextField modPartNameField;

    @FXML
    private TextField modPartInvField;

    @FXML
    private TextField modPartPriceField;

    @FXML
    private TextField modPartMaxField;

    @FXML
    private TextField modPartMinField;

    @FXML
    private Button modPartSaveButton;

    @FXML
    private Button modPartCancelButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Part selectedPart = MainWindowController.getSelectedPart();



        //mod radio buttons to group
        ToggleGroup modPart = new ToggleGroup();
        modPartInHouseRadio.setToggleGroup(modPart);
        modPartOutsourcedRadio.setToggleGroup(modPart);
        modPartGrid.getChildren().removeAll(modPartCompNameLabel, modPartCompNameField);

        if(selectedPart instanceof InHouse){
            modPart.selectToggle(modPartInHouseRadio);
        } else {
            modPart.selectToggle(modPartOutsourcedRadio);
        }

        //toggle between In-House and Outsourced display
        modPartInHouseRadio.setOnAction(e -> {
            modPartGrid.getChildren().removeAll(modPartCompNameLabel, modPartCompNameField);
            modPartGrid.getChildren().addAll(modPartMachIDField, modPartMachIDLabel);
        });
        modPartOutsourcedRadio.setOnAction(e -> {
            modPartGrid.getChildren().removeAll(modPartMachIDField, modPartMachIDLabel);
            modPartGrid.getChildren().addAll(modPartCompNameField, modPartCompNameLabel);
        });

        //modify part
            modPartSaveButton.setOnAction(e -> {
                for(Part part : Inventory.getAllParts()){
                    //get user input
                    String partName = modPartNameField.getText();
                    String price = modPartPriceField.getText();
                    String inv = modPartInvField.getText();
                    String min = modPartMinField.getText();
                    String max = modPartMaxField.getText();
                    String machID = modPartMachIDField.getText();
                    String compName = modPartCompNameField.getText();

                    int id = selectedPart.getId();

                    if(!partName.isBlank() && !partName.isEmpty()){
                        selectedPart.setName(partName);
                    }

                    if(!price.isBlank() && !price.isEmpty()){
                        double newPrice = Double.parseDouble(price);
                        selectedPart.setPrice(newPrice);
                    }

                    if(!inv.isBlank() && !inv.isEmpty()){
                        int newInv = Integer.parseInt(inv);
                        selectedPart.setStock(newInv);
                    }

                    if(!min.isBlank() && !min.isEmpty()){
                        int newMin = Integer.parseInt(min);
                        selectedPart.setMin(newMin);
                    }

                    if(!max.isBlank() && !max.isEmpty()){
                        int newMax = Integer.parseInt(max);
                        selectedPart.setMax(newMax);
                    }

                    if(selectedPart instanceof InHouse){
                        if (!machID.isBlank() && !machID.isEmpty()) {
                            int newMachID = Integer.parseInt(machID);
                            ((InHouse) selectedPart).setMachineID(newMachID);
                        }
                    } else {
                        if (!compName.isBlank() & !compName.isEmpty()) {
                            ((Outsourced)selectedPart).setCompanyName(compName);
                        }
                    }

                    Inventory.updatePart(id, selectedPart);
                }

                Stage stage = (Stage) modPartSaveButton.getScene().getWindow();
                stage.close();
            });

        //cancel and exit to main menu
        modPartCancelButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to cancel?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Stage stage = (Stage) modPartCancelButton.getScene().getWindow();
                    stage.close();
                }
            });
        });
    }
}