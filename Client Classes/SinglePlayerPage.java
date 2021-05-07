package guiforgame;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SinglePlayerPage extends Pane {

    protected final Button startButton;
    protected final Button backButton;
    protected final TextField namePlace;
    static String playerName = "";
    boolean goNextPage;
    public SinglePlayerPage() {

        
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Text label = new Text();
        label.setEffect(ds);
        label.setCache(true);
        label.setLayoutX(380);
        label.setLayoutY(110);
        label.setFill(Color.WHITE);
        label.setText("Player Name");
        label.setFont(Font.font(null, FontWeight.BOLD, 30));
        getChildren().add(label);
        
        
        startButton = new Button();
        backButton = new Button();
        namePlace = new TextField();
       

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(360.0);
        setPrefWidth(288.0);
        
        startButton.setLayoutX(370.0);
        startButton.setLayoutY(235.0);
        startButton.setMnemonicParsing(false);
        startButton.setPrefHeight(32.0);
        startButton.setPrefWidth(200.0);
        startButton.setText("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                if(signPage.inUpFlag)
                {
                     playerName = namePlace.getText();
                    try {
                        connectServer.StartSignIn();
                        connectServer.replyMsg = connectServer.readDataFromServer.readLine();
                        if(connectServer.replyMsg.equals("right")){
                           connectServer.saveLoggedName();
                            goNextPage = true;
                            
                        }
                        else
                        {
                            goNextPage = false;
                            namePlace.setText("");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            String str = "Wrong user name, Please Try Again" ;
                            alert.setTitle("Error Message");
                            alert.setHeaderText("Tic Tac Toe");
                            alert.setContentText(str);
                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("DialogCss.css").toExternalForm());
                            dialogPane.getStyleClass().add("myDialog");
                            Image image;
                            image = new Image(getClass().getResource("1200px-Tic_tac_toe.svg.png").toExternalForm());
                            ImageView imageView = new ImageView(image);
                            alert.setGraphic(imageView);
                            alert.showAndWait();
                        } 
                    
                        }
                     
                    catch (IOException ex) {
                        Logger.getLogger(SinglePlayerPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                else
                   {
                     playerName = namePlace.getText();
                    try {
                        connectServer.StartSignUp();
                        connectServer.replyMsg = connectServer.readDataFromServer.readLine();
                        if(connectServer.replyMsg.equals("right")){
                            connectServer.saveLoggedName();
                            goNextPage = true;
                        }
                        else
                        {
                            namePlace.setText("");
                            goNextPage = false;
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            String str = "Existing user name, Please Try Again" ;
                            alert.setTitle("Error Message");
                            alert.setHeaderText("Tic Tac Toe");
                            alert.setContentText(str);
                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("DialogCss.css").toExternalForm());
                            dialogPane.getStyleClass().add("myDialog");
                            Image image;
                            image = new Image(getClass().getResource("1200px-Tic_tac_toe.svg.png").toExternalForm());
                            ImageView imageView = new ImageView(image);
                            alert.setGraphic(imageView);
                            alert.showAndWait();
                        }
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(SinglePlayerPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(goNextPage && !(playerName.equals("")))
                    MangePaneShow.viewStartPane();
            }
                });
        backButton.setLayoutX(370.0);
        backButton.setLayoutY(384.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(32.0);
        backButton.setPrefWidth(200.0);
        backButton.setText("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                MangePaneShow.signPage();
            }
                });

        namePlace.setLayoutX(400.0);
        namePlace.setLayoutY(150.0);

        getChildren().add(startButton);
        getChildren().add(backButton);
        getChildren().add(namePlace);

    }
}
