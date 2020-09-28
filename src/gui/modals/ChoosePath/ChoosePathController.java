package gui.modals.ChoosePath;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import model.Paths.Path;
import model.Players.Player;
import model.Spaces.Space;
import utilities.StringUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the Choose Path UI (ChoosePath.fxml)
 */

public class ChoosePathController implements Initializable {
    @FXML
    private Label path1Description, path1Spaces, path2Description, path2Spaces;

    @FXML
    private Tab path1Tab, path2Tab;

    @FXML
    private TabPane tabPane;

    @FXML
    private Button continueButton;

    private Player player;
    private Path path1, path2;

    public ChoosePathController(Player player, Path path1, Path path2) {
        this.player = player;
        this.path1 = path1;
        this.path2 = path2;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        path1Tab.setText(path1.getName());
        path1Description.setText(StringUtil.splitStringIntoNewLine(path1.getDescription(), 200));
        path1Spaces.setText(getUniqueSpaces(path1));

        path2Tab.setText(path2.getName());
        path2Description.setText(StringUtil.splitStringIntoNewLine(path2.getDescription(), 200));
        path2Spaces.setText(getUniqueSpaces(path2));

        continueButton.setOnAction(e -> {
            AudioClip audioPlayer = new AudioClip(new Media(getClass().getResource("/audio/click.wav").toString()).getSource());
            audioPlayer.play();

            if(tabPane.getSelectionModel().getSelectedIndex() == 0) player.setPath(path1);
            else player.setPath(path2);

            ((Stage)((Node) e.getSource()).getScene().getWindow()).close();
        });
    }

    /**
     * Gets the unique spaces in each Path
     * @param path path whose unique spaces will be extracted
     * @return returns the formatted labell
     */
    private String getUniqueSpaces(Path path) {
        ArrayList<String> uniqueSpaces = new ArrayList<String>();
        String label = "Spaces found:\n";
        for(Space space : path.getSpaces()) {
            if(!uniqueSpaces.contains(space.getType())) {
                uniqueSpaces.add(space.getType());
                label += " -" + space.getType() + "\n";
            }
        }
        return label;
    }
}
