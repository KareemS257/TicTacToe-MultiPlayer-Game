
package guiforgame;

import guiforgame.MangePaneShow;
import javafx.scene.text.*;
import java.lang.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class signPage extends AnchorPane {

    protected final Pane pane;
    protected final Button signIn;
    protected final Button signUp;
    protected final Label label;
    static boolean inUpFlag;
    public signPage() {

        pane = new Pane();
        signIn = new Button();
        signUp = new Button();
        label = new Label();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        pane.setLayoutX(168.0);
        pane.setLayoutY(18.0);
        pane.setPrefHeight(326.0);
        pane.setPrefWidth(227.0);

        signIn.setLayoutX(168.0);
        signIn.setLayoutY(217.0);
        signIn.setMnemonicParsing(false);
        signIn.setPrefHeight(55.0);
        signIn.setPrefWidth(200.0);
        signIn.setText("SignIn");
        signIn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                inUpFlag = true;
                MangePaneShow.viewSinglePlayerPane();
            }
                });

        signUp.setLayoutX(168.0);
        signUp.setLayoutY(386.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(55.0);
        signUp.setPrefWidth(200.0);
        signUp.setText("SignUp");
        signUp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
                inUpFlag = false;
                MangePaneShow.viewSinglePlayerPane();
            }
                });

        label.setLayoutX(170.0);
        label.setLayoutY(41.0);
        label.setPrefHeight(46.0);
        label.setPrefWidth(249.0);
        label.setText("Tic Tac Toe");
        label.setFont(new Font(35.0));

        pane.getChildren().add(signIn);
        pane.getChildren().add(signUp);
        pane.getChildren().add(label);
        getChildren().add(pane);

    }
}