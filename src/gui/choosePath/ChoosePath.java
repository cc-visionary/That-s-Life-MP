package gui.choosePath;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import main.paths.Path;
import main.spaces.Space;

import java.util.ArrayList;

public class ChoosePath extends GridPane {

    public ChoosePath(Path path1, Path path2) {
        Label label = new Label("Choose Path:");

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab path1Tab = new Tab();
        path1Tab.setText(path1.getName());
        FlowPane path1Pane = new FlowPane();
        path1Pane.getChildren().addAll(new Label(path1.getDescription()), getUniqueSpaces(path1));
        path1Tab.setContent(path1Pane);

        Tab path2Tab = new Tab();
        path2Tab.setText(path2.getName());
        FlowPane path2Pane = new FlowPane();
        path2Pane.getChildren().addAll(new Label(path2.getDescription()), getUniqueSpaces(path2));
        path2Tab.setContent(path2Pane);

        path2Tab.setContent(path2Pane);

        tabPane.getTabs().addAll(path1Tab, path2Tab);

        Button button = new Button("Continue");

        add(label, 0, 0);
        add(tabPane, 0, 1);
        add(button, 0, 2);
    }

    private Label getUniqueSpaces(Path path) {
        ArrayList<String> uniqueSpaces = new ArrayList<String>();
        String label = "Spaces found:\n";
        for(Space space : path.getSpaces()) {
            if(!uniqueSpaces.contains(space.getType())) {
                uniqueSpaces.add(space.getType());
                label += " -" + space.getType() + "\n";
            }
        }
        Label uniqueSpacesLabel = new Label(label);
        return uniqueSpacesLabel;
    }
}
