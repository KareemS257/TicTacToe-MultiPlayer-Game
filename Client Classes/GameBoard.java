package guiforgame;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameBoard extends GridPane {

    protected final GridPane gridPane;
    protected final Pane pane;
    protected final ColumnConstraints col1;
    protected final ColumnConstraints col2;
    protected final ColumnConstraints col3;
    protected final RowConstraints row1;
    protected final RowConstraints row2;
    protected final RowConstraints row3;
    protected final Button btn1;
    protected final Button btn2;
    protected final Button btn3;
    protected final Button btn4;
    protected final Button btn5;
    protected final Button btn6;
    protected final Button btn7;
    protected final Button btn8;
    protected final Button btn9;
    protected final Button recordBtn;
    boolean computerTurn = false;
    boolean humanTurn = true;
    boolean winFlag = false;
    boolean record = false;
    boolean drawFlag = false;
    boolean sceneFlag1 = true;
    int clickedX = 0;
    int clickedY = 0;
    int compMoveX = 0;
    int compMoveY = 0;
    int remainingTiles = 9;
    int[] rowH = new int[3];
    int[] colH = new int[3];
    int[] rowC = new int[3];
    int[] colC = new int[3];
    int diagonalTilesH = 0;
    int diagonalTilesRvrsH = 0;
    int diagonalTilesC = 0;
    int diagonalTilesRvrsC = 0;
    Button btn;
    static Vector<Integer> recordedMoves = new Vector<Integer>();
    int movesIndex = 0;
    Optional<ButtonType> result;
    Alert alert;
    Font font;
    Socket mySocket;
    DataInputStream readDataFromServer;
    PrintStream sendDataToServer;
    java.net.URL resource = getClass().getResource("uhoh.mp3");
    static AudioClip sound1;
    public GameBoard() {
        gridPane = new GridPane();
        gridPane.setId("grid");
        font = new Font("Merienda", 35);
        pane = new Pane();
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
        Random random = new Random();
//        sound1 = new AudioClip(resource.toString());
        pane.setLayoutY(6.0);
        pane.setMaxHeight(Double.MAX_VALUE);
        pane.setMaxWidth(Double.MAX_VALUE);
        pane.setMinHeight(USE_PREF_SIZE);
        pane.setMinWidth(USE_PREF_SIZE);
        pane.setPrefHeight(600.0);
        pane.setPrefWidth(800.0);

        gridPane.setLayoutY(6.0);
        gridPane.setMaxHeight(Double.MAX_VALUE);
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.setMinHeight(USE_PREF_SIZE);
        gridPane.setMinWidth(USE_PREF_SIZE);
        gridPane.setPrefHeight(600.0);
        gridPane.setPrefWidth(700.0);

        pane.getChildren().add(gridPane);
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

        recordBtn.setMaxHeight(Double.MAX_VALUE);
        recordBtn.setMaxWidth(Double.MAX_VALUE);
        recordBtn.setMnemonicParsing(false);
        recordBtn.setPrefHeight(50);
        recordBtn.setPrefWidth(180);
        recordBtn.setText("Record");
        recordBtn.setLayoutX(720);

        pane.getChildren().add(recordBtn);

        btn1.setMaxHeight(Double.MAX_VALUE);
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn1.setMnemonicParsing(false);
        btn1.setPrefHeight(130.0);
        btn1.setPrefWidth(196.0);
        btn1.setText("");
        btn1.setId("blank");
        GridPane.setRowIndex(btn1, 0);
        GridPane.setColumnIndex(btn1, 0);

        btn2.setMaxHeight(Double.MAX_VALUE);
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn2.setMnemonicParsing(false);
        btn2.setPrefHeight(128.0);
        btn2.setPrefWidth(197.0);
        btn2.setText("");
        btn2.setId("blank");
        GridPane.setRowIndex(btn2, 0);
        GridPane.setColumnIndex(btn2, 1);

        btn3.setMaxHeight(Double.MAX_VALUE);
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn3.setMnemonicParsing(false);
        btn3.setPrefHeight(130.0);
        btn3.setPrefWidth(197.0);
        btn3.setText("");
        btn3.setId("blank");
        GridPane.setRowIndex(btn3, 0);
        GridPane.setColumnIndex(btn3, 2);

        btn4.setMaxHeight(Double.MAX_VALUE);
        btn4.setMaxWidth(Double.MAX_VALUE);
        btn4.setMnemonicParsing(false);
        btn4.setPrefHeight(126.0);
        btn4.setPrefWidth(197.0);
        btn4.setText("");
        btn4.setId("blank");
        GridPane.setRowIndex(btn4, 1);
        GridPane.setColumnIndex(btn4, 0);

        btn5.setMaxHeight(Double.MAX_VALUE);
        btn5.setMaxWidth(Double.MAX_VALUE);
        btn5.setMnemonicParsing(false);
        btn5.setPrefHeight(129.0);
        btn5.setPrefWidth(197.0);
        btn5.setText("");
        btn5.setId("blank");
        GridPane.setRowIndex(btn5, 1);
        GridPane.setColumnIndex(btn5, 1);

        btn6.setMaxHeight(Double.MAX_VALUE);
        btn6.setMaxWidth(Double.MAX_VALUE);
        btn6.setMnemonicParsing(false);
        btn6.setPrefHeight(129.0);
        btn6.setPrefWidth(196.0);
        btn6.setText("");
        btn6.setId("blank");
        GridPane.setRowIndex(btn6, 1);
        GridPane.setColumnIndex(btn6, 2);

        btn7.setMaxHeight(Double.MAX_VALUE);
        btn7.setMaxWidth(Double.MAX_VALUE);
        btn7.setMnemonicParsing(false);
        btn7.setPrefHeight(128.0);
        btn7.setPrefWidth(197.0);
        btn7.setText("");
        btn7.setId("blank");
        GridPane.setRowIndex(btn7, 2);
        GridPane.setColumnIndex(btn7, 0);

        btn8.setMaxHeight(Double.MAX_VALUE);
        btn8.setMaxWidth(Double.MAX_VALUE);
        btn8.setMnemonicParsing(false);
        btn8.setPrefHeight(130.0);
        btn8.setPrefWidth(198.0);
        btn8.setText("");
        btn8.setId("blank");
        GridPane.setRowIndex(btn8, 2);
        GridPane.setColumnIndex(btn8, 1);

        btn9.setMaxHeight(Double.MAX_VALUE);
        btn9.setMaxWidth(Double.MAX_VALUE);
        btn9.setMnemonicParsing(false);
        btn9.setPrefHeight(128.0);
        btn9.setPrefWidth(199.0);
        btn9.setText("");
        btn9.setId("blank");
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
        getChildren().add(pane);

        //event handlers
        recordBtn.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            int saveStatus = 0;
            if (remainingTiles != 9) {
                alert = new Alert(Alert.AlertType.NONE, "Cannot record after game has started", ButtonType.OK);
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    alert.close();

                }
            } else {

                record = true;
            }
        });

        gridPane.getChildren().forEach(tile -> {

            tile.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {

                int index = 0;
                clickedY = GridPane.getRowIndex(tile);
                clickedX = GridPane.getColumnIndex(tile);
                //Player starts
                if (!winFlag && humanTurn && !((Button) tile).getText().equals("O")) {
                    ((Button) tile).setText("X");
                    ((Button) tile).setId("X");
                    ((Button) tile).setDisable(true);
//                    ((Button) tile).setStyle("-fx-background-color: RED");
//                    ((Button) tile).setStyle("-fx-font-size: 10em");
                    //((Button) tile).setFont(Font.font("sans-serif", FontWeight.BOLD, 150));
                    recordedMoves.add(gridPane.getChildren().indexOf(tile));
                    remainingTiles--;
                    checkTiles();
                }
                //ComputerTurn
                if (!winFlag && computerTurn) {
                    index = random.nextInt(9);

                    btn = (Button) gridPane.getChildren().get(index);
                    if (btn.getText().equals("")) {
                        btn.setText("O");
//                       btn.setStyle("-fx-background-color: RED");
//                   btn.setStyle("-fx-font-size: 10em");

                    } else {
                        while (!btn.getText().equals("") && remainingTiles > 1) {
                            index = random.nextInt(9);

                            btn = (Button) gridPane.getChildren().get(index);
                        }
                        btn.setText("O");
                    }
                    compMoveX = GridPane.getRowIndex(btn);
                    compMoveY = GridPane.getColumnIndex(btn);
                    remainingTiles--;
                    recordedMoves.add(gridPane.getChildren().indexOf(btn));
                    checkTiles();
                }

            });

        });

    }

    public void checkTiles() {

        //checking if human has won
        if (humanTurn) {
            if (clickedY == 0) {
                colH[0]++;
                if (clickedX == 0) {
                    diagonalTilesH++;
                } else if (clickedX == 2) {
                    diagonalTilesRvrsH++;
                }
            } else if (clickedY == 1) {
                colH[1]++;
                if (clickedX == 1) {
                    diagonalTilesH++;
                    diagonalTilesRvrsH++;
                }
            } else if (clickedY == 2) {
                colH[2]++;
                if (clickedX == 0) {
                    diagonalTilesRvrsH++;

                } else if (clickedX == 2) {
                    diagonalTilesH++;
                }

            }
            if (clickedX == 0) {
                rowH[0]++;
            } else if (clickedX == 1) {
                rowH[1]++;
            } else if (clickedX == 2) {
                rowH[2]++;
            }

            for (int i = 0; i < 3; i++) {
                if (rowH[i] == 3 || colH[i] == 3) {
                    winFlag = true;
                }

            }
            if (diagonalTilesH == 3 || diagonalTilesRvrsH == 3) {
                winFlag = true;
            }

            if (!winFlag) {
                computerTurn = true;
                humanTurn = false;
            } 
            else {
                computerTurn = false;
                humanTurn = false;
                if (record) {
                saveReplay();     
                }
                Timeline tl = new Timeline();
                tl.setCycleCount(Animation.INDEFINITE);
                KeyFrame kf = new KeyFrame(Duration.seconds(6),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                       if (sceneFlag1){
                           MainClass.window.setScene(MainClass.scene2);
                          // MainClass.mediaPlayer.seek(Duration.ZERO);
                           MainClass.mediaPlayer.play();
                           sceneFlag1 = false;
                       }
                       else
                       {
                           MainClass.mediaPlayer.stop();
                           clearBoard();
                           sceneFlag1 = true;
                           MangePaneShow.viewStartPane();
                           MainClass.window.setScene(MainClass.scene1);
                           //connectServer.endConnection();
                           //connectServer.connect();
                           tl.stop(); 
                       }   
                    }  
        });

        tl.getKeyFrames().add(kf);
        tl.play();
            }
        } else {
            if (compMoveY == 0) {
                colC[0]++;
                if (compMoveX == 0) {
                    diagonalTilesC++;
                } else if (compMoveX == 2) {
                    diagonalTilesRvrsC++;
                }
            } else if (compMoveY == 1) {
                colC[1]++;
                if (compMoveX == 1) {
                    diagonalTilesC++;
                    diagonalTilesRvrsC++;
                }
            } else if (compMoveY == 2) {
                colC[2]++;
                if (compMoveX == 0) {
                    diagonalTilesRvrsC++;

                } else if (compMoveX == 2) {
                    diagonalTilesC++;
                }

            }
            if (compMoveX == 0) {
                rowC[0]++;
            } else if (compMoveX == 1) {
                rowC[1]++;
            } else if (compMoveX == 2) {
                rowC[2]++;
            }

            for (int i = 0; i < 3; i++) {
                if (rowC[i] == 3 || colC[i] == 3) {
                    winFlag = true;
                }

            }
            if (diagonalTilesC == 3 || diagonalTilesRvrsC == 3) {
                winFlag = true;
            }

            if (!winFlag) {
                computerTurn = false;
                humanTurn = true;
            } 
            else {
//                alert = new Alert(Alert.AlertType.NONE, "Computer Wins!", ButtonType.OK);
//                result = alert.showAndWait();
//                if (result.isPresent() && result.get() == ButtonType.OK) {
//                    alert.close();
//                    clearBoard();
//                    // showReplay();
                    computerTurn = false;
                    humanTurn = false;
                    if (record) {
                        saveReplay();
                }
                            Timeline tl = new Timeline();
                            tl.setCycleCount(Animation.INDEFINITE);
                            KeyFrame kf = new KeyFrame(Duration.seconds(3),
                            new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                            if (sceneFlag1){
                            MainClass.window.setScene(MainClass.scene3);
                           sceneFlag1 = false;
                           }
                           else
                           {
                           sceneFlag1 = true;
                           MangePaneShow.viewStartPane();
                           MainClass.window.setScene(MainClass.scene1);
                           //connectServer.endConnection();
                           //connectServer.connect();
                           clearBoard();
                           tl.stop(); 
        }
                           
                    }  
        });
        tl.getKeyFrames().add(kf);
        tl.play();

            }
        }

        if (!winFlag && remainingTiles == 0) {
            drawFlag = true;
            if (record) {
                saveReplay();
            }
        }
        if (drawFlag && remainingTiles == 0) {
                computerTurn = false;
                humanTurn = false;
                Timeline tl = new Timeline();
                tl.setCycleCount(Animation.INDEFINITE);
                KeyFrame kf = new KeyFrame(Duration.seconds(3),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                       if (sceneFlag1){
                           MainClass.window.setScene(MainClass.scene4);
                           sceneFlag1 = false;
                       }
                       else
                       {
                           sceneFlag1 = true;
                           MangePaneShow.viewStartPane();
                           MainClass.window.setScene(MainClass.scene1);
                           //connectServer.endConnection();
                          // connectServer.connect();
                           clearBoard();
//                           gridPane.getChildren().forEach(tile -> {
//                           tile.setDisable(false);
//                           ((Button) tile).setText("");
//                           }); 
                           tl.stop(); 
                       }
                           
                    }  
        });
        tl.getKeyFrames().add(kf);
        tl.play();     
        }
    }

    void clearBoard() {
    computerTurn = false;
    humanTurn = true;
    winFlag = false;
    record = false;
    drawFlag = false;
    sceneFlag1 = true;
    clickedX = 0;
    clickedY = 0;
    compMoveX = 0;
    compMoveY = 0;
    remainingTiles = 9;
    diagonalTilesH = 0;
    diagonalTilesRvrsH = 0;
    diagonalTilesC = 0;
    diagonalTilesRvrsC = 0;
    recordedMoves.clear();
    movesIndex = 0;
    for(int i=0; i<3 ; i++)
    {
    rowH[i] = 0;
    colH[i] = 0;
    rowC[i] = 0;
    colC[i] = 0;
    }
        int index = 0;
        Button btn = new Button();
        for (index = 0; index < 9; index++) {
            btn = (Button) gridPane.getChildren().get(index);
            btn.setText("");
            btn.setDisable(false);    
        }
    }

    void saveReplay() {
        connectServer.saveReplay(recordedMoves);

    }

}
