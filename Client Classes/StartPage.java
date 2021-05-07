package guiforgame;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StartPage extends Pane {
    
    protected final Button singlePlayerButton;
    protected final Button multiPlayerButton;
    protected final Button highestScoreButton;
    protected final Button showReplays;
    protected final Button aboutButton;
    protected final Button exitButton;
    String regex = "\\d+"; 
    String str="";
    public StartPage() {
        connectServer.connect();
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Text label = new Text();
        label.setEffect(ds);
        label.setCache(true);
        label.setLayoutX(350);
        label.setLayoutY(50);
        label.setFill(Color.WHITE);
        label.setText("Tic Tac Toe");
        label.setFont(Font.font(null, FontWeight.BOLD, 40));
        getChildren().add(label);
     
        singlePlayerButton = new Button();
        multiPlayerButton = new Button();
        highestScoreButton = new Button();
        showReplays = new Button();
        aboutButton = new Button();
        exitButton = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(360.0);
        setPrefWidth(288.0);

        singlePlayerButton.setLayoutX(300);
        singlePlayerButton.setLayoutY(100.0);
        singlePlayerButton.setMnemonicParsing(false);
        singlePlayerButton.setPrefHeight(32.0);
        singlePlayerButton.setPrefWidth(300.0);
        singlePlayerButton.setText("Single Player");
        singlePlayerButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                MangePaneShow.viewGameBoardPane();
            }
                });
        multiPlayerButton.setLayoutX(300);
        multiPlayerButton.setLayoutY(200.0);
        multiPlayerButton.setMnemonicParsing(false);
        multiPlayerButton.setPrefHeight(32.0);
        multiPlayerButton.setPrefWidth(300.0);
        multiPlayerButton.setText("Two Players");
        multiPlayerButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                    MangePaneShow.viewGameBoard2Pane();
                    connectServer.startTwoPlayersGame();
            }
                });

        highestScoreButton.setLayoutX(300);
        highestScoreButton.setLayoutY(300.0);
        highestScoreButton.setMnemonicParsing(false);
        highestScoreButton.setPrefHeight(32.0);
        highestScoreButton.setPrefWidth(300.0);
        highestScoreButton.setText("Highest Score");
        highestScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
               try{ 
                connectServer.getHigestScore();
                connectServer.replyMsg = connectServer.readDataFromServer.readLine();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if(connectServer.replyMsg.matches(regex)){
                 str = "The higest score is "+connectServer.replyMsg ;
                }
                else{
                str = "No replays available currently";
                }
                alert.setTitle("Highest Score");
                alert.setHeaderText("Tic Tac Toe");
                alert.setContentText(str);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("DialogCss.css").toExternalForm());
                dialogPane.getStyleClass().add("myDialog");
                Image image = new Image(getClass().getResource("1200px-Tic_tac_toe.svg.png").toExternalForm());
                ImageView imageView = new ImageView(image);
                alert.setGraphic(imageView);
                alert.showAndWait();} 
               catch (IllegalArgumentException | NullPointerException e) {
                        e.printStackTrace();
                    }
               catch (FileNotFoundException ex) {
                    Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
        );

        showReplays.setLayoutX(300);
        showReplays.setLayoutY(400.0);
        showReplays.setMnemonicParsing(false);
        showReplays.setPrefHeight(32.0);
        showReplays.setPrefWidth(300.0);
        showReplays.setText("Show Replays");
        showReplays.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                connectServer.saveLoggedName();
                connectServer.showReplays();
                    
                MangePaneShow.viewReplays();
            }
        });
        
        aboutButton.setLayoutX(300);
        aboutButton.setLayoutY(500.0);
        aboutButton.setMnemonicParsing(false);
        aboutButton.setPrefHeight(32.0);
        aboutButton.setPrefWidth(300.0);
        aboutButton.setText("About");
        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
               try{ 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                String str = "Tic-tac-toe, or X and O "
                        + "is a game for two players, X and O, who take turns marking the spaces in a 3×3 grid."
                        + " The player who succeeds in placing three of their marks in a diagonal, horizontal, or vertical row is the winner. \n\n"
                    +"Prepared by Esraa,Kareem,Mayada,Hala \n"
                    + "If you have any comments, ideas.. just let us know\n"
                    + "Email:   \n"
                    + "esraagaber2397@gmail.com\n"
                    + "kareemsayed15@gmail.com\n"
                    + "mayadatarek803@gmail.com\n"
                    + "hala.meto1997@gmail.com\n";
                alert.setTitle("About");
                alert.setHeaderText("Tic Tac Toe");
                alert.setContentText(str);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("DialogCss.css").toExternalForm());
                dialogPane.getStyleClass().add("myDialog");
                Image image = new Image(getClass().getResource("1200px-Tic_tac_toe.svg.png").toExternalForm());
                ImageView imageView = new ImageView(image);
                alert.setGraphic(imageView);
                alert.showAndWait();} 
               catch (IllegalArgumentException | NullPointerException e) {
                        e.printStackTrace();
                    }

                }
            }
        );
        exitButton.setLayoutX(300);
        exitButton.setLayoutY(600.0);
        exitButton.setMnemonicParsing(false);
        exitButton.setPrefHeight(32.0);
        exitButton.setPrefWidth(300.0);
        exitButton.setText("Exit");
        exitButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                Platform.exit();
            }
                });

        getChildren().add(singlePlayerButton);
        getChildren().add(multiPlayerButton);
        getChildren().add(highestScoreButton);
        getChildren().add(showReplays);
        getChildren().add(aboutButton);
        getChildren().add(exitButton);

    }
}
