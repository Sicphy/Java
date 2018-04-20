package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Threads.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();

        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        CheckBox plusCheckBox = new CheckBox("+");
        CheckBox minusCheckBox = new CheckBox("-");
        Button addButton = new Button("Add");
        Button resetButton = new Button("Reset");
        Label winnerLabel = new Label("Введите требуемое число.");
        Label requredNumberLabel = new Label("Требуемое число:");
        Button requredButton = new Button("enter");
        TextField requredField = new TextField();
        Button startButton = new Button("start");
        MainThread mainThread = new MainThread();
        requredField.setMaxWidth(80);

        root.add(plusCheckBox, 0, 1);
        root.add(minusCheckBox, 1, 1);
        root.add(addButton, 0, 2);
        root.add(resetButton, 1, 2);
        root.add(winnerLabel, 0, 0, 3,1);
        root.add(startButton, 2, 2);

        GridPane.setHalignment(requredNumberLabel, HPos.RIGHT);
        root.add(requredNumberLabel, 0, 3);
        root.add(requredField, 1, 3);
        root.add(requredButton, 2, 3);


        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(mainThread.getRequiredValue() != null) {
                    if (plusCheckBox.isSelected()) {
                        mainThread.createThread('+');
                    }

                    if (minusCheckBox.isSelected()) {
                        mainThread.createThread('-');
                    }

                    if (plusCheckBox.isSelected() && minusCheckBox.isSelected()) {
                        winnerLabel.setText("Должен быть выбран только один чекбокс.");
                    }


                    if (!plusCheckBox.isSelected() && !minusCheckBox.isSelected()) {
                        winnerLabel.setText("Чекбокс должен быть выбран.");
                    }
                } else {
                    winnerLabel.setText("Введите требуемое число вначале.");
                }
            }
        });

        resetButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

            }
        });


        requredButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                mainThread.setRequiredValue(Integer.parseInt(requredField.getText()));
                winnerLabel.setText("Требуемое число: " + requredField.getText() + ", добавте потоки и нажмите старт.");
            }
        });


        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(mainThread.isEmpty()) {
                    winnerLabel.setText("Добавте хотя бы один поток.");
                    return;
                }

                mainThread.startRace();
                int temp = mainThread.getWinnerId();
                winnerLabel.setText("Победитель поток с id: " + temp + ".");
            }
        });


        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Thread Racer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}