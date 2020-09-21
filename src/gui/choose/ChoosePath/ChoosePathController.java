package gui.choose.ChoosePath;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.paths.Path;
import model.players.Player;
import model.spaces.Space;
import model.utilities.StringUtil;

import java.util.ArrayList;

public class ChoosePathController {
    @FXML
    private Label path1Description, path1Spaces, path2Description, path2Spaces;

    @FXML
    private Tab path1, path2;

    @FXML
    private TabPane tabPane;

    @FXML
    private Button continueButton;

    public ChoosePathController(Player player, Path path1, Path path2) {
        this.path1.setText(path1.getName());
        path1Description.setText(seperateDescription(path1.getDescription()));
        path1Spaces.setText(getUniqueSpaces(path1));

        this.path2.setText(path2.getName());
        path2Description.setText(seperateDescription(path2.getDescription()));
        path2Spaces.setText(getUniqueSpaces(path2));

        continueButton.setOnAction(e -> {
            if(tabPane.getSelectionModel().getSelectedIndex() == 0) {
                player.setPath(path1);
            } else {
                player.setPath(path2);
            }
        });
    }

    /**
     * Seperate a description into multiple lines so that it won't overflow
     * the label
     * @param description to be splitted into multiple line
     * @return description that was splitted inito multiple lines
     */
    private String seperateDescription(String description) {
        String splittedString = "";
        for(String string : StringUtil.splitStringLength(description, 40)) splittedString += string + "\n";
        return splittedString;
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
