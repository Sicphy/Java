import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Gui extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();

        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        Button putButton = new Button("Put");
        Button deleteButton = new Button("Delete");
        Label putLabel = new Label("Choose file for sending:");
        Button openButton = new Button("open");
        TextField putTextField = new TextField();
        Button sendRequestButton = new Button("Send");
        Button getBackButton = new Button();
        Label getBackLabel = new Label("Get Back");
        Label deleteLabel = new Label("Enter the delete file name:");
        TextField deleteTextField = new TextField();

        getBackButton.setStyle("-fx-background-image: url(left-arrow.png);" +
        "-fx-focus-color: transparent;" + "-fx-faint-focus-color: transparent;" + "-fx-border-color: transparent;" +
        " -fx-background-color: transparent;");
        putButton.setMinWidth(80);
        deleteButton.setMinWidth(80);


        root.add(putButton, 3, 3);
        root.add(deleteButton, 4, 3);
       // root.add(winnerLabel, 0, 0, 3,1);

       // GridPane.setHalignment(requredNumberLabel, HPos.RIGHT);
       // root.add(requredNumberLabel, 0, 3);

        putButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                root.add(putLabel, 2, 1,2,1);
                root.add(putTextField, 2, 2);
                root.add(openButton, 3, 2);
                root.add(sendRequestButton, 2, 3,2,1);
                root.add(getBackButton, 0,0);
                root.add(getBackLabel, 1,0,2,1);
            }
        });

        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
                dialog.setMode(FileDialog.LOAD);
                dialog.setVisible(true);
                String file = dialog.getDirectory() + dialog.getFile();
                putTextField.setText(file);
            }
        });

        getBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                root.add(putButton, 3, 3);
                root.add(deleteButton, 4, 3);
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                root.add(deleteLabel, 2, 1,2,1);
                root.add(deleteTextField, 2, 2);
                root.add(sendRequestButton, 3, 2);
                root.add(getBackButton, 0,0);
                root.add(getBackLabel, 1,0,2,1);
            }
        });



        Scene scene = new Scene(root, 370, 180);
        primaryStage.setTitle("Http Client");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
