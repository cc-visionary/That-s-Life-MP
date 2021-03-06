import gui.Menu.MenuController;
import gui.modals.Modal;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

/**
 * <p>Main App to control everything</p>
 * <p>Allows the user to do the following:</p>
 * <ol>
 *     <li>Start Game where Player will be redirected to the Game Settings</li>
 *     <li>Exit the Game</li>
 * </ol>
 *
 */

public class App extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image("/images/logo300x300.png"));
        primaryStage.setTitle("Game of Life");

        // Plays the Game Music
        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/datasette.mp3").toString()).getSource());
        audioPlayer.setCycleCount(AudioClip.INDEFINITE);
        audioPlayer.play();

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/gui/Menu/Menu.fxml"));
        MenuController menuController = new MenuController();
        menuLoader.setController(menuController);

        primaryStage.setScene(new Scene(menuLoader.load()));

        // when primary stage is closed, all the other stage should also be closed.
        primaryStage.setOnCloseRequest(confirmCloseEventHandler);

        primaryStage.show();
    }

    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");

        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);

        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Confirm Exit");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(primaryStage);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) event.consume();
        else Platform.exit();
    };
}
