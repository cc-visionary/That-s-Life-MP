import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main App
 */

public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Game of Life");

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/gui/Menu/Menu.fxml"))));

        primaryStage.show();
    }
}
