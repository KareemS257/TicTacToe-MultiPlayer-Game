/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiforgame;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Optional;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sun.rmi.runtime.Log;

public class GameBoard2 extends GridPane {

    java.net.URL resource = getClass().getResource("uhoh.mp3");
    static AudioClip sound;
    protected static GridPane gridPane;
    protected static ColumnConstraints col1;
    protected static ColumnConstraints col2;
    protected static ColumnConstraints col3;
    protected static RowConstraints row1;
    protected static RowConstraints row2;
    protected static RowConstraints row3;
    protected static Button btn1;
    protected static Button btn2;
    protected static Button btn3;
    protected static Button btn4;
    protected static Button btn5;
    protected static Button btn6;
    protected static Button btn7;
    protected static Button btn8;
    protected static Button btn9;
    protected final Button recordBtn;
    static Vector<Integer> recordedMovesMulti = new Vector<Integer>();

    Pane pane;
    //Button btn;
    static Alert alert;
    static Font font;
    static boolean xoFlag;
    static int turn;
    static boolean recordStatus = false;
    static boolean sceneFlag = true;

    public GameBoard2() {
        pane = new Pane();
        sound = new AudioClip(resource.toString());
        font = new Font("Merienda", 35);
        gridPane = new GridPane();
        gridPane.setId("grid");
        col1 = new ColumnConstraints();
        col2 = new ColumnConstraints();
        col3 = new ColumnConstraints();
        row1 = new RowConstraints();
        row2 = new RowConstraints();
        row3 = new RowConstraints();
        btn1 = new Button();
        btn2 = new Button();
        btn3 = new Button();
        btn4 = new Button();
        btn5 = new Button();
        btn6 = new Button();
        btn7 = new Button();
        btn8 = new Button();
        btn9 = new Button();

        recordBtn = new Button();
        recordBtn.setMaxHeight(Double.MAX_VALUE);
        recordBtn.setMaxWidth(Double.MAX_VALUE);
        recordBtn.setMnemonicParsing(false);
        recordBtn.setPrefHeight(50);
        recordBtn.setPrefWidth(180);
        recordBtn.setText("Record");
        recordBtn.setLayoutX(720);
        recordBtn.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            recordStatus = true;
        });

        gridPane.setLayoutY(6.0);
        gridPane.setMaxHeight(Double.MAX_VALUE);
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.setMinHeight(USE_PREF_SIZE);
        gridPane.setMinWidth(USE_PREF_SIZE);
        gridPane.setPrefHeight(600.0);
        gridPane.setPrefWidth(700);

        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMinWidth(10.0);
        col1.setPrefWidth(100.0);

        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPrefWidth(100.0);

        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(10.0);
        col3.setPrefWidth(100.0);

        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        row2.setMinHeight(10.0);
        row2.setPrefHeight(30.0);
        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        row3.setMinHeight(10.0);
        row3.setPrefHeight(30.0);
        row3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        btn1.setMaxHeight(Double.MAX_VALUE);
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn1.setMnemonicParsing(false);
        btn1.setPrefHeight(130.0);
        btn1.setPrefWidth(196.0);
        btn1.setText("");
        btn1.setId("blank");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("1");
                }
            }
        });
        GridPane.setRowIndex(btn1, 0);
        GridPane.setColumnIndex(btn1, 0);

        btn2.setMaxHeight(Double.MAX_VALUE);
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn2.setMnemonicParsing(false);
        btn2.setPrefHeight(128.0);
        btn2.setPrefWidth(197.0);
        btn2.setText("");
        btn2.setId("blank");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("2");
                }
            }
        });
        GridPane.setRowIndex(btn2, 0);
        GridPane.setColumnIndex(btn2, 1);

        btn3.setMaxHeight(Double.MAX_VALUE);
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn3.setMnemonicParsing(false);
        btn3.setPrefHeight(130.0);
        btn3.setPrefWidth(197.0);
        btn3.setText("");
        btn3.setId("blank");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("3");
                }
            }
        });
        GridPane.setRowIndex(btn3, 0);
        GridPane.setColumnIndex(btn3, 2);

        btn4.setMaxHeight(Double.MAX_VALUE);
        btn4.setMaxWidth(Double.MAX_VALUE);
        btn4.setMnemonicParsing(false);
        btn4.setPrefHeight(126.0);
        btn4.setPrefWidth(197.0);
        btn4.setText("");
        btn4.setId("blank");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("4");
                }
            }
        });
        GridPane.setRowIndex(btn4, 1);
        GridPane.setColumnIndex(btn4, 0);

        btn5.setMaxHeight(Double.MAX_VALUE);
        btn5.setMaxWidth(Double.MAX_VALUE);
        btn5.setMnemonicParsing(false);
        btn5.setPrefHeight(129.0);
        btn5.setPrefWidth(197.0);
        btn5.setText("");
        btn5.setId("blank");
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("5");
                }
            }
        });
        GridPane.setRowIndex(btn5, 1);
        GridPane.setColumnIndex(btn5, 1);

        btn6.setMaxHeight(Double.MAX_VALUE);
        btn6.setMaxWidth(Double.MAX_VALUE);
        btn6.setMnemonicParsing(false);
        btn6.setPrefHeight(129.0);
        btn6.setPrefWidth(196.0);
        btn6.setText("");
        btn6.setId("blank");
        btn6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("6");
                }
            }
        });
        GridPane.setRowIndex(btn6, 1);
        GridPane.setColumnIndex(btn6, 2);

        btn7.setMaxHeight(Double.MAX_VALUE);
        btn7.setMaxWidth(Double.MAX_VALUE);
        btn7.setMnemonicParsing(false);
        btn7.setPrefHeight(128.0);
        btn7.setPrefWidth(197.0);
        btn7.setText("");
        btn7.setId("blank");
        btn7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("7");
                }
            }
        });
        GridPane.setRowIndex(btn7, 2);
        GridPane.setColumnIndex(btn7, 0);

        btn8.setMaxHeight(Double.MAX_VALUE);
        btn8.setMaxWidth(Double.MAX_VALUE);
        btn8.setMnemonicParsing(false);
        btn8.setPrefHeight(130.0);
        btn8.setPrefWidth(198.0);
        btn8.setText("");
        btn8.setId("blank");
        btn8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("8");
                }
            }
        });
        GridPane.setRowIndex(btn8, 2);
        GridPane.setColumnIndex(btn8, 1);

        btn9.setMaxHeight(Double.MAX_VALUE);
        btn9.setMaxWidth(Double.MAX_VALUE);
        btn9.setMnemonicParsing(false);
        btn9.setPrefHeight(128.0);
        btn9.setPrefWidth(199.0);
        btn9.setText("");
        btn9.setId("blank");
        btn9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                if (turn == 1) {
                    connectServer.sendButtonIndex("9");
                }
            }
        });
        GridPane.setRowIndex(btn9, 2);
        GridPane.setColumnIndex(btn9, 2);

        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col2);
        gridPane.getColumnConstraints().add(col3);
        gridPane.getRowConstraints().add(row1);
        gridPane.getRowConstraints().add(row2);
        gridPane.getRowConstraints().add(row3);
        gridPane.getChildren().add(btn1);
        gridPane.getChildren().add(btn2);
        gridPane.getChildren().add(btn3);
        gridPane.getChildren().add(btn4);
        gridPane.getChildren().add(btn5);
        gridPane.getChildren().add(btn6);
        gridPane.getChildren().add(btn7);
        gridPane.getChildren().add(btn8);
        gridPane.getChildren().add(btn9);

        pane.setLayoutY(6.0);
        pane.setMaxHeight(Double.MAX_VALUE);
        pane.setMaxWidth(Double.MAX_VALUE);
        pane.setMinHeight(USE_PREF_SIZE);
        pane.setMinWidth(USE_PREF_SIZE);
        pane.setPrefHeight(600.0);
        pane.setPrefWidth(800);
        pane.getChildren().add(recordBtn);
        pane.getChildren().add(gridPane);
        getChildren().add(pane);
    }

    static void DrawX(Button btn) {
        btn.setText("X");
        btn.setDisable(true);
        btn.setFont(font);
        btn.setStyle("-fx-background-color: #330066");
        checkTiles2();
        if (recordStatus) {
            recordedMovesMulti.add(gridPane.getChildren().indexOf(btn));

        }
    }

    static void DrawO(Button btn) {
        btn.setText("O");
        btn.setDisable(true);
//                btn.setFont(font);
//                btn.setStyle("-fx-color:crimson");

        btn.setStyle("-fx-background-color: #330066");
        checkTiles2();
        if (recordStatus) {
            recordedMovesMulti.add(gridPane.getChildren().indexOf(btn));
        }
    }

    static void checkTiles2() {
        if (turn == 0 && !xoFlag) //PlayerO check won
        {
            if ((btn1.getText().equals("O") && btn2.getText().equals("O") && btn3.getText().equals("O"))
                    || (btn4.getText().equals("O") && btn5.getText().equals("O") && btn6.getText().equals("O"))
                    || (btn7.getText().equals("O") && btn8.getText().equals("O") && btn9.getText().equals("O"))
                    || (btn1.getText().equals("O") && btn4.getText().equals("O") && btn7.getText().equals("O"))
                    || (btn2.getText().equals("O") && btn5.getText().equals("O") && btn8.getText().equals("O"))
                    || (btn3.getText().equals("O") && btn6.getText().equals("O") && btn9.getText().equals("O"))
                    || (btn1.getText().equals("O") && btn5.getText().equals("O") && btn9.getText().equals("O"))
                    || (btn3.getText().equals("O") && btn5.getText().equals("O") && btn7.getText().equals("O"))) {
                showAlertWon();
            } else {
                noPlayerWon();
            }
        } else if (turn == 0 && xoFlag) // PlayerX check won
        {
            if ((btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText() == "X")
                    || (btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText() == "X")
                    || (btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText() == "X")
                    || (btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText() == "X")
                    || (btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText() == "X")
                    || (btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText() == "X")
                    || (btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText() == "X")
                    || (btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText() == "X")) {
                showAlertWon();
            } else {
                noPlayerWon();
            }
        }
    }

    static void showAlertLose() {

        gridPane.getChildren().forEach(tile -> {
            tile.setDisable(true);
        });
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(3),
                new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (sceneFlag) {
                    MainClass.window.setScene(MainClass.scene3);
                    sceneFlag = false;
                } else {
                    sceneFlag = true;
                    MangePaneShow.viewStartPane();
                    MainClass.window.setScene(MainClass.scene1);

                    connectServer.endConnection();
                    connectServer.connect();
                    if (recordStatus) {
                        connectServer.saveLoggedName();
                        connectServer.saveReplay(recordedMovesMulti);
                        recordedMovesMulti.clear();
                    }
                    gridPane.getChildren().forEach(tile -> {
                        tile.setDisable(false);
                        ((Button) tile).setText("");
                    });
                    tl.stop();
                }

            }
        });
        tl.getKeyFrames().add(kf);
        tl.play();
    }

    static void showAlertWon() {

        connectServer.sendDataToServer.println("won");
        connectServer.sendDataToServer.println(SinglePlayerPage.playerName);
        gridPane.getChildren().forEach(tile -> {
            tile.setDisable(true);
        });
        //MainClass.mediaPlayer.seek(Duration.ZERO);
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(6),
                new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                if (sceneFlag) {
                    MainClass.window.setScene(MainClass.scene2);
                    MainClass.mediaPlayer.play();
                    sceneFlag = false;
                } else {
                    MainClass.mediaPlayer.stop();
                    sceneFlag = true;
                    MangePaneShow.viewStartPane();
                    MainClass.window.setScene(MainClass.scene1);

                    connectServer.endConnection();

                    connectServer.connect();
                    if (recordStatus) {
                        connectServer.saveLoggedName();
                        connectServer.saveReplay(recordedMovesMulti);
                        recordedMovesMulti.clear();
                    }
                    gridPane.getChildren().forEach(tile -> {
                        tile.setDisable(false);
                        ((Button) tile).setText("");
                    });
                    tl.stop();

                }

            }
        });

        tl.getKeyFrames().add(kf);
        tl.play();

    }

    static void noPlayerWon() {
        if ((!(btn1.getText().equals("")) && !(btn2.getText().equals("")) && !(btn3.getText().equals(""))
                && !(btn4.getText().equals("")) && !(btn5.getText().equals("")) && !(btn6.getText().equals(""))
                && !(btn7.getText().equals("")) && !(btn8.getText()).equals("") && !(btn9.getText().equals("")))) {
            showAlertNoWon();
        } else {
            connectServer.sendDataToServer.println("stilPlaying");
        }
    }

    static void showAlertNoWon() {

        connectServer.sendDataToServer.println("fair");
        connectServer.sendDataToServer.println(SinglePlayerPage.playerName);
        gridPane.getChildren().forEach(tile -> {
            tile.setDisable(true);
        });
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(3),
                new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                if (sceneFlag) {
                    MainClass.window.setScene(MainClass.scene4);
                    sceneFlag = false;
                } else {
                    sceneFlag = true;
                    MangePaneShow.viewStartPane();
                    MainClass.window.setScene(MainClass.scene1);

                    connectServer.endConnection();

                    connectServer.connect();
                    if (recordStatus) {
                        connectServer.saveLoggedName();
                        connectServer.saveReplay(recordedMovesMulti);
                        recordedMovesMulti.clear();
                    }
                    gridPane.getChildren().forEach(tile -> {
                        tile.setDisable(false);
                        ((Button) tile).setText("");
                    });
                    tl.stop();
                }

            }
        });
        tl.getKeyFrames().add(kf);
        tl.play();
    }
}
