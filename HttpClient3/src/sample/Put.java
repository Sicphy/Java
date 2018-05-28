package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.awt.*;

public class Put {
    @FXML
    private TextField putTextField;
    @FXML
    private Label putLabel;
    @FXML
    private Button openButton;
    @FXML
    private Button sendPutRequestButton;
    @FXML
    private Button getBackButton;
    @FXML
    private Label getBackLabel;

    private PutController putController;

    public void setPutMethod(PutMethod putMethod) {
        putController = new PutController(putMethod);
    }

    @FXML
    private void sendPutRequestAction(ActionEvent event) {
        putController.setPathName(putTextField.getText());
        putController.startMethod();
    }

    @FXML
    private void openAction(ActionEvent event) {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();
        putTextField.setText(file);
       // putController.setPathName(file);
    }

    @FXML
    private void getBackAction(ActionEvent event) {
        startWindowOpen();
    }

    @FXML
    private void startWindowOpen() {
        try {
            Stage primaryStage = (Stage)putLabel.getScene().getWindow();
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
