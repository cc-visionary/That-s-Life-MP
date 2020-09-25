import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
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

        // when primary stage is closed, all the other stage should also be closed.
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
        });

        primaryStage.show();
    }
}
