package gui.choose;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.cards.CareerCard.CareerCard;
import main.cards.SalaryCard.SalaryCard;
import main.decks.Deck;
import main.paths.Path;
import main.players.Player;
import main.spaces.Space;

import java.util.ArrayList;

public class ChoosePath {
    /**
     * Opens a ChoosePath modal to the GUI to let the user choose a Path based on the
     * paths available to them
     * @param player
     */
    public static void choosePath(Player player) {
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED);

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
        vbox.setMaxWidth(Screen.getPrimary().getBounds().getMaxX() / 4);
        vbox.setMaxHeight(Screen.getPrimary().getBounds().getMaxY() / 2);

        Label label = new Label("Choose a Path");

        TabPane tabPane = new TabPane();

        // for Path 1
        Tab path1Tab = new Tab();
        path1Tab.setText(player.getPath().getPath1().getName());
        FlowPane path1Pane = new FlowPane();
        path1Pane.setVgap(10);
        path1Pane.setPadding(new Insets(10));
        path1Pane.getChildren().addAll(new Label(player.getPath().getPath1().getDescription()), getUniqueSpaces(player.getPath().getPath1()));
        path1Tab.setContent(path1Pane);

        // for Path 2
        Tab path2Tab = new Tab();
        path2Tab.setText(player.getPath().getPath2().getName());
        FlowPane path2Pane = new FlowPane();
        path2Pane.setVgap(10);
        path2Pane.setPadding(new Insets(10));
        path2Pane.getChildren().addAll(new Label(player.getPath().getPath2().getDescription()), getUniqueSpaces(player.getPath().getPath2()));
        path2Tab.setContent(path2Pane);

        tabPane.getTabs().addAll(path1Tab, path2Tab);
        tabPane.getStyleClass().add("floating");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setMinHeight(Screen.getPrimary().getBounds().getMaxY() / 2);

        // continue button
        Button button = new Button("Continue");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // closes the modal for choosing a path for the user
                if(tabPane.getSelectionModel().getSelectedIndex() == 0) {
                    player.setPath(player.getPath().getPath1());
                } else {
                    player.setPath(player.getPath().getPath2());
                }
                newStage.close();
            }
        });
        button.setMaxWidth(Screen.getPrimary().getBounds().getMaxX() / 4);

        vbox.getChildren().addAll(label, tabPane, button);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));

        newStage.setScene(new Scene(vbox));
        newStage.showAndWait();
    }

    /**
     * Lets each player to choose their initial Path
     * @param path1
     * @param path2
     * @param player
     * @param careerDeck
     * @param salaryDeck
     */
    public static void choosePath(Path path1, Path path2, Player player, Deck careerDeck, Deck salaryDeck) {
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED);

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: aliceblue; -fx-font-size: 16; -fx-border-width: 1; -fx-border-color: gray");
        vbox.setMaxWidth(Screen.getPrimary().getBounds().getMaxX() / 4);
        vbox.setMaxHeight(Screen.getPrimary().getBounds().getMaxY() / 2);

        Label label = new Label("Choose a Path");

        TabPane tabPane = new TabPane();

        // for Path 1
        Tab path1Tab = new Tab();
        path1Tab.setText(path1.getName());
        FlowPane path1Pane = new FlowPane();
        path1Pane.setVgap(10);
        path1Pane.setPadding(new Insets(10));
        path1Pane.getChildren().addAll(new Label(path1.getDescription()), getUniqueSpaces(path1));
        path1Tab.setContent(path1Pane);

        // for Path 2
        Tab path2Tab = new Tab();
        path2Tab.setText(path2.getName());
        FlowPane path2Pane = new FlowPane();
        path2Pane.setVgap(10);
        path2Pane.setPadding(new Insets(10));
        path2Pane.getChildren().addAll(new Label(path2.getDescription()), getUniqueSpaces(path2));
        path2Tab.setContent(path2Pane);

        tabPane.getTabs().addAll(path1Tab, path2Tab);
        tabPane.getStyleClass().add("floating");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setMinHeight(Screen.getPrimary().getBounds().getMaxY() / 2);

        // continue button
        Button button = new Button("Continue");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // closes the modal for choosing a path for the user
                switch(tabPane.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        CareerCard careerCard = (CareerCard) careerDeck.pickTopCard();
                        SalaryCard salaryCard = (SalaryCard) salaryDeck.pickTopCard();
                        player.setPath(path1);
                        player.setCareerCard(careerCard);
                        player.setSalaryCard(salaryCard);
                        break;
                    case 1:
                        player.setPath(path2);
                        break;
                }
                player.getPath().getSpaces()[player.getLocation()].addPlayer(player);
                newStage.close();
            }
        });
        button.setMaxWidth(Screen.getPrimary().getBounds().getMaxX() / 4);

        vbox.getChildren().addAll(label, tabPane, button);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));

        newStage.setScene(new Scene(vbox));
        newStage.showAndWait();
    }

    private static Label getUniqueSpaces(Path path) {
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