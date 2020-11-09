package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("sample.fxml"))));
        Image image = new Image(getClass().getResourceAsStream("Icon_logo.png"));
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();
    }


    public static void main(String[] args) {
        new database();
        launch(args);
    }
}
