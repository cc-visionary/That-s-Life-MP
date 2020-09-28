package gui.Menu;

import gui.GameSettings.GameSettingsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

/**
 * Contains methods to control the Menu UI (Menu.fxml)
 */

public class MenuController {
    @FXML
    public void onClickStart(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
        audioPlayer.play();

        FXMLLoader gameSettingsLoader = new FXMLLoader(getClass().getResource("/gui/GameSettings/GameSettings.fxml"));
        GameSettingsController gameSettingsController = new GameSettingsController();
        gameSettingsLoader.setController(gameSettingsController);

        try {
            stage.setScene(new Scene(gameSettingsLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickExit(ActionEvent event){
        AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
        audioPlayer.play();

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}
