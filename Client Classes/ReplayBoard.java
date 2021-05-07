package guiforgame;

import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class ReplayBoard extends GridPane {

    protected static GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected RowConstraints rowConstraints0;
    protected RowConstraints rowConstraints1;
    protected static Button btn1;
    protected static Button btn2;
    protected static Button btn3;
    protected static Button btn4;
    protected static Button btn5;
    protected static Button btn6;
    protected static Button btn7;
    protected static Button btn8;
    protected static Button btn9;
    static Button btn;
    static int[] movesArray;
    static int tiles = 0;

    public ReplayBoard() {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        btn1 = new Button();
        btn2 = new Button();
        btn3 = new Button();
        btn4 = new Button();
        btn5 = new Button();
        btn6 = new Button();
        btn7 = new Button();
        btn8 = new Button();
        btn9 = new Button();
        btn = new Button();

        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        gridPane.setLayoutX(10.0);
        gridPane.setLayoutY(38.0);
        gridPane.setMaxHeight(Double.MAX_VALUE);
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.setMinHeight(USE_PREF_SIZE);
        gridPane.setMinWidth(USE_PREF_SIZE);
        gridPane.setPrefHeight(578.0);
        gridPane.setPrefWidth(740.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        btn1.setMaxHeight(Double.MAX_VALUE);
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn1.setMnemonicParsing(false);
        btn1.setPrefHeight(130.0);
        btn1.setPrefWidth(196.0);
        btn1.setText("");
        btn1.setFont(new Font(40.0));
        btn1.setDisable(true);
        btn1.setId("blank");
        GridPane.setRowIndex(btn1, 0);
        GridPane.setColumnIndex(btn1, 0);

        btn2.setMaxHeight(Double.MAX_VALUE);
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn2.setMnemonicParsing(false);
        btn2.setPrefHeight(128.0);
        btn2.setPrefWidth(197.0);
        btn2.setText("");
        btn2.setFont(new Font(40.0));
        btn2.setDisable(true);
        btn2.setId("blank");
        GridPane.setRowIndex(btn2, 0);
        GridPane.setColumnIndex(btn2, 1);

        btn3.setMaxHeight(Double.MAX_VALUE);
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn3.setMnemonicParsing(false);
        btn3.setPrefHeight(224.0);
        btn3.setPrefWidth(264.0);
        btn3.setText("");
        btn3.setFont(new Font(41.0));
        btn3.setDisable(true);
        btn3.setId("blank");
        GridPane.setRowIndex(btn3, 0);
        GridPane.setColumnIndex(btn3, 2);

        btn4.setMaxHeight(Double.MAX_VALUE);
        btn4.setMaxWidth(Double.MAX_VALUE);
        btn4.setMnemonicParsing(false);
        btn4.setPrefHeight(126.0);
        btn4.setPrefWidth(197.0);
        btn4.setText("");
        btn4.setFont(new Font(40.0));
        btn4.setDisable(true);
        btn4.setId("blank");
        GridPane.setRowIndex(btn4, 1);
        GridPane.setColumnIndex(btn4, 0);

        btn5.setMaxHeight(Double.MAX_VALUE);
        btn5.setMaxWidth(Double.MAX_VALUE);
        btn5.setMnemonicParsing(false);
        btn5.setPrefHeight(129.0);
        btn5.setPrefWidth(197.0);
        btn5.setText("");
        btn5.setFont(new Font(41.0));
        btn5.setDisable(true);
        btn5.setId("blank");
        GridPane.setRowIndex(btn5, 1);
        GridPane.setColumnIndex(btn5, 1);

        btn6.setMaxHeight(Double.MAX_VALUE);
        btn6.setMaxWidth(Double.MAX_VALUE);
        btn6.setMnemonicParsing(false);
        btn6.setPrefHeight(129.0);
        btn6.setPrefWidth(196.0);
        btn6.setText("");
        btn6.setFont(new Font(40.0));
        btn6.setDisable(true);
        btn6.setId("blank");
        GridPane.setRowIndex(btn6, 1);
        GridPane.setColumnIndex(btn6, 2);

        btn7.setMaxHeight(Double.MAX_VALUE);
        btn7.setMaxWidth(Double.MAX_VALUE);
        btn7.setMnemonicParsing(false);
        btn7.setPrefHeight(128.0);
        btn7.setPrefWidth(197.0);
        btn7.setText("");
        btn7.setFont(new Font(40.0));
        btn7.setDisable(true);
        btn7.setId("blank");
        GridPane.setRowIndex(btn7, 2);
        GridPane.setColumnIndex(btn7, 0);

        btn8.setMaxHeight(Double.MAX_VALUE);
        btn8.setMaxWidth(Double.MAX_VALUE);
        btn8.setMnemonicParsing(false);
        btn8.setPrefHeight(130.0);
        btn8.setPrefWidth(198.0);
        btn8.setText("");
        btn8.setFont(new Font(39.0));
        btn8.setDisable(true);
        btn8.setId("blank");
        GridPane.setRowIndex(btn8, 2);
        GridPane.setColumnIndex(btn8, 1);

        btn9.setMaxHeight(Double.MAX_VALUE);
        btn9.setMaxWidth(Double.MAX_VALUE);
        btn9.setMnemonicParsing(false);
        btn9.setPrefHeight(128.0);
        btn9.setPrefWidth(199.0);
        btn9.setText("");
        btn9.setFont(new Font(40.0));
        btn9.setDisable(true);
        btn9.setId("blank");
        GridPane.setRowIndex(btn9, 2);
        GridPane.setColumnIndex(btn9, 2);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(btn1);
        gridPane.getChildren().add(btn2);
        gridPane.getChildren().add(btn3);
        gridPane.getChildren().add(btn4);
        gridPane.getChildren().add(btn5);
        gridPane.getChildren().add(btn6);
        gridPane.getChildren().add(btn7);
        gridPane.getChildren().add(btn8);
        gridPane.getChildren().add(btn9);
        getChildren().add(gridPane);

    }

    public static void showReplay(int[] moves) {

        Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                
                // whatever you need to do every 2 seconds
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        
                        if (tiles < moves.length) {

                            int index = moves[tiles];

                            if (tiles % 2 == 0) {

                                ((Button) gridPane.getChildren().get(index)).setText("X");

                            } else {
                                ((Button) gridPane.getChildren().get(index)).setText("O");
                            }
                            tiles++;

                        } else {
                            timer.cancel();
                            clearBoard();
                            MangePaneShow.viewReplays();
                            
                        }
                    }

                });

            }
        };
        try {
            timer.schedule(myTask, 2000, 2000);
        } catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    static void clearBoard() {

        for (int i = 0; i < 9; i++) {
            ((Button) gridPane.getChildren().get(i)).setText("");
        }
        tiles=0;
    }

}
