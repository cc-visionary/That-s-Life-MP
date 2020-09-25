import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/audio/main.wav").toString()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.9);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
    }
}
