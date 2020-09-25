package gui.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class MenuController {
    @FXML
    public void onClickStart(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/audio/click.wav").toString()));
        mediaPlayer.play();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/gui/GameSettings/GameSettings.fxml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickExit(ActionEvent event){
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/audio/click.wav").toString()));
        mediaPlayer.play();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}
