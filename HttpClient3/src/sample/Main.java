package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setMinHeight(180);
        primaryStage.setMinWidth(370);
        primaryStage.setTitle("Http Client");
       // primaryStage.setOnHidden(event -> ((Controller) loader.getController()).exitApplication(event));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
