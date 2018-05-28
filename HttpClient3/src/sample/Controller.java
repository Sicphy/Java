package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.net.Socket;

public class Controller {
    @FXML
    private Button putButton;
    @FXML
    private Button deleteButton;
    private Socket socket;
    private PutMethod putMethod;
    private DeleteMethod deleteMethod;


    @FXML
    private void deleteAction(ActionEvent event) {
        deleteWindowOpen();
    }

    @FXML
    private void putAction(ActionEvent event) {
        putWindowOpen();
    }


    public void initialize() throws Exception {
        //System.out.println("Hello");
        //int port = 8080;
        //Socket socket;
        //this.socket = new Socket("localhost", port);
        this.putMethod = new PutMethod(null);
        this.deleteMethod = new DeleteMethod(null);
        //PutController putController = new PutController(new PutMethod(null, socket.getOutputStream()));
        //DeleteController deleteController = new DeleteController(new DeleteMethod(socket.getOutputStream(), null));
        //socket.close();

    }

    @FXML
    private void deleteWindowOpen() {
        try {
            Stage primaryStage = (Stage)putButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/delete.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Delete delete = loader.getController();
            delete.setDeleteMethod(this.deleteMethod);
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(180);
            primaryStage.setMinWidth(380);
            primaryStage.setTitle("Delete");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void putWindowOpen() {
        try {
            Stage primaryStage = (Stage)putButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/put.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //Put putMethodController = loader.getController();
            Put put = loader.getController();
            put.setPutMethod(this.putMethod);
            primaryStage.setResizable(true);
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(180);
            primaryStage.setMinWidth(380);
            primaryStage.setTitle("Put");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
