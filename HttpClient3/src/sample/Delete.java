package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import javafx.event.ActionEvent;

public class Delete {
    @FXML
    private Button sendDeleteRequestButton;
    @FXML
    private Label deleteLabel;
    @FXML
    private TextField deleteTextField;
    @FXML
    private Button getBackButton;
    @FXML
    private Label getBackLabel;

    private DeleteController deleteController;

    public void setDeleteMethod(DeleteMethod deleteMethod) {
        this.deleteController = new DeleteController(deleteMethod);
    }

    @FXML
    private void sendDeleteRequestAction(ActionEvent event) {
        deleteController.setFileName(deleteTextField.getText());
        deleteController.startMethod();
    }

    @FXML
    private void getBackAction(ActionEvent event) {
        startWindowOpen();
    }

    @FXML
    private void startWindowOpen() {
        try {
            Stage primaryStage = (Stage)deleteLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(180);
            primaryStage.setMinWidth(380);
            primaryStage.setTitle("Http Client");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
