package gui.menu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MenuController extends Application{
    Stage window;
    Button button, button1, button2, button3, button4, button5, button6, button7;
    Scene scene, scene2;
    private Integer player = 2, cash = 200000;
    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage Primarystage) throws Exception {
        Primarystage.setTitle("Game of Life");

        window = Primarystage;
        Group layout = new Group();
        Group layout2 = new Group();

        button = new Button();
        button.setText("Start");
        button.setLayoutX(230);
        button.setLayoutY(350);

        button1 = new Button();
        button1.setText("Exit");
        button1.setLayoutX(230);
        button1.setLayoutY(400);

//window.setScene(scene2)
        button.setOnAction(e -> window.setScene(scene2));
        button1.setOnAction(e -> window.close());

        scene = new Scene(layout, 500, 500, Color.HOTPINK);
        ImageView iv = new ImageView();
        Image image = new Image("file:LIFE.png");
        iv.setImage(image);

        layout.getChildren().add(iv);
        layout.getChildren().add(button);
        layout.getChildren().add(button1);

        window.setScene(scene);
        window.show();

        scene2 = new Scene(layout2, 500,500, Color.HOTPINK);
        ImageView iv1 = new ImageView();
        Image image1 = new Image("file:LIFE.png");
        iv1.setImage(image1);
        Text text1 = new Text("Number of Players (2-3): ");
        Text text2 = new Text("Starting Money: ");
        Text text3 = new Text();
        Text text4 = new Text();
        text1.setLayoutX(100);
        text1.setLayoutY(350);
        text2.setLayoutX(100);
        text2.setLayoutY(400);

        text3.setText(player.toString());
        text3.setLayoutX(300);
        text3.setLayoutY(350);

        button4 = new Button();
        button4.setText("-");
        button4.setLayoutX(250);
        button4.setLayoutY(330);
        button4.setDisable(true);
        button4.setOnAction(e -> {
            player = player -1;
            text3.setText(player.toString());
            text3.setLayoutX(300);
            text3.setLayoutY(350);
            if(player == 2)
            {
                button4.setDisable(true);
                button5.setDisable(false);
            }
        });

        button5 = new Button();
        button5.setText("+");
        button5.setLayoutX(350);
        button5.setLayoutY(330);
        button5.setOnAction(e -> {
            player +=1;
            text3.setText(player.toString());
            text3.setLayoutX(300);
            text3.setLayoutY(350);
            if(player == 3)
            {
                button5.setDisable(true);
                button4.setDisable(false);
            }
        });

        text4.setText(cash.toString());
        text4.setLayoutX(290);
        text4.setLayoutY(400);

        button6 = new Button();
        button6.setText("-");
        button6.setLayoutX(250);
        button6.setLayoutY(380);
        button6.setOnAction(e -> {
            cash -=50000;
            text4.setText(cash.toString());
            text4.setLayoutX(290);
            text4.setLayoutY(400);
            if(cash == 150000)
                button6.setDisable(true);
            else button7.setDisable(false);
        });

        button7 = new Button();
        button7.setText("+");
        button7.setLayoutX(350);
        button7.setLayoutY(380);
        button7.setOnAction(e -> {
            cash +=50000;
            text4.setText(cash.toString());
            text4.setLayoutX(290);
            text4.setLayoutY(400);
            if(cash == 1000000)
                button7.setDisable(true);
            else button6.setDisable(false);
        });

        button2 = new Button("Back");
        button2.setLayoutX(10);
        button2.setLayoutY(450);
        button2.setOnAction(e -> window.setScene(scene));

        button3 = new Button();
        button3.setText("Start");
        button3.setLayoutX(450);
        button3.setLayoutY(450);

        layout2.getChildren().addAll(iv1, text1,text2, text3, text4, button2,button3, button4, button5, button6, button7);
    }

    @FXML
    public void onClickStart(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader gameSettingLoader = new FXMLLoader(getClass().getResource("/gui/gameSettings/GameSettings.fxml"));

//        stage.setScene(new Scene(some fxml));
        stage.setMaximized(true);
    }

    @FXML
    public void onClickExit(ActionEvent event){
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}
