package model.spaces.MagentaSpace;

import gui.choose.ChoosePath.ChoosePathController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Constants;
import model.players.Player;

/**
 * Represents the Magenta Space - Which Path Space
 *      which is a junction where players can choose to continue the current path or switch to another one
 */

final public class WhichPathSpace extends MagentaSpace {
    public WhichPathSpace() {
        super(Constants.WHICH_PATH);
    }

    /**
     * Junction where the Player can choose between 2 Paths
     * @param player  player who landed on the junction
     * @return        path chosen by the Player
     */
    public void choosePath(Player player) {
        // if path 2 is not null, allow the user to choose between the paths
        if(player.getPath().getPath2() != null) {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/choose/ChoosePath/ChoosePath.fxml"));
            try {
                stage.setScene(new Scene(fxmlLoader.load()));
                ((ChoosePathController) fxmlLoader.getController()).setPaths(player, player.getPath().getPath1(), player.getPath().getPath2());
            } catch(Exception e) {
                e.printStackTrace();
            }
            stage.showAndWait();
        } else { // if not, automatically return the Path 1;
            player.setPath(player.getPath().getPath1());
        }
    }
}
