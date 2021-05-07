package guiforgame;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TwoPlayersPage extends Pane {

    protected final TextField player1;
    protected final TextField player2;
    protected final Button startButton;
    protected final Button backButton;

    public TwoPlayersPage() {

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        
        Text label1 = new Text();
        Text label2 = new Text();
        
        label1.setEffect(ds);
        label1.setCache(true);
        label1.setLayoutX(105);
        label1.setLayoutY(70);
        label1.setFill(Color.BLACK);
        label1.setText("Player 1");
        label1.setFont(Font.font(null, FontWeight.BOLD, 20));
        getChildren().add(label1);
        
        label2.setEffect(ds);
        label2.setCache(true);
        label2.setLayoutX(105);
        label2.setLayoutY(155);
        label2.setFill(Color.BLACK);
        label2.setText("Player 2");
        label2.setFont(Font.font(null, FontWeight.BOLD, 20));
        getChildren().add(label2);
        
        player1 = new TextField();
        player2 = new TextField();
        startButton = new Button();
        backButton = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(360.0);
        setPrefWidth(288.0);

        player1.setLayoutX(70.0);
        player1.setLayoutY(83.0);

        player2.setLayoutX(70.0);
        player2.setLayoutY(168.0);

        startButton.setLayoutX(84.0);
        startButton.setLayoutY(235.0);
        startButton.setMnemonicParsing(false);
        startButton.setPrefHeight(32.0);
        startButton.setPrefWidth(121.0);
        startButton.setText("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                  MangePaneShow.viewGameBoard2Pane();
            }
                });

        backButton.setLayoutX(84.0);
        backButton.setLayoutY(284.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(32.0);
        backButton.setPrefWidth(121.0);
        backButton.setText("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                MangePaneShow.viewStartPane();
            }
                });

        getChildren().add(player1);
        getChildren().add(player2);
        getChildren().add(startButton);
        getChildren().add(backButton);

    }
}
