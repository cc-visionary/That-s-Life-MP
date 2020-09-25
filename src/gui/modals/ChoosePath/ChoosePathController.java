package gui.modals.ChoosePath;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.Paths.Path;
import model.Players.Player;
import model.Spaces.Space;
import utilities.StringUtil;

import java.util.ArrayList;

public class ChoosePathController {
    @FXML
    private Label path1Description, path1Spaces, path2Description, path2Spaces;

    @FXML
    private Tab path1Tab, path2Tab;

    @FXML
    private TabPane tabPane;

    @FXML
    private Button continueButton;

    public void setPaths(Player player, Path path1, Path path2) {
        path1Tab.setText(path1.getName());
        path1Description.setText(StringUtil.splitStringIntoNewLine(path1.getDescription(), 40));
        path1Spaces.setText(getUniqueSpaces(path1));

        path2Tab.setText(path2.getName());
        path2Description.setText(StringUtil.splitStringIntoNewLine(path2.getDescription(), 40));
        path2Spaces.setText(getUniqueSpaces(path2));

        continueButton.setOnAction(e -> {
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
