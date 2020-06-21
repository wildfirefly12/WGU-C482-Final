package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{
        Parent mainWindow = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainWindow.fxml"));
        mainStage.setTitle("Inventory Management System");
        mainStage.setScene(new Scene(mainWindow));
        mainStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
