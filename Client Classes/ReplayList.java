package guiforgame;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ReplayList extends Pane {

    protected final Label label;
    protected static ListView listView;
    static ObservableList<String> list;
    static Vector<String> receivedReplays = new Vector<String>();
    static Vector<Character> moves = new Vector<Character>();
    static Character[] movesChar;
    static Vector<String> movesString = new Vector<String>();
    static int[] tiles;
    static String lastViewedReplay = "";
    static Vector <Integer> listOfIds = new Vector<>();

    public ReplayList() {

        Button backButton = new Button();
        backButton.setLayoutX(370.0);
        backButton.setLayoutY(384.0);
        backButton.setMnemonicParsing(false);
        backButton.setPrefHeight(32.0);
        backButton.setPrefWidth(121.0);
        backButton.setText("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                list.clear();
                receivedReplays.clear();
                listOfIds.clear();
                MangePaneShow.viewStartPane();

            }
        });
        label = new Label();
        listView = new ListView();

        setPrefHeight(600.0);
        setPrefWidth(800.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutX(113.0);
        label.setLayoutY(49.0);
        label.setPrefHeight(83.0);
        label.setPrefWidth(619.0);
        label.setText("Replays");
        label.setFont(new Font(40.0));

        listView.setLayoutX(210.0);
        listView.setLayoutY(166.0);
        listView.setPrefHeight(200.0);
        listView.setPrefWidth(454.0);

        getChildren().add(label);
        getChildren().add(listView);
        getChildren().add(backButton);

    }

    public static void receiveReplays(Socket player) {
        PrintStream sendToServer;
        DataInputStream readReplay;
        String receivedMsg = "";
        int id = 0;
        int i = 0;
        boolean replayEnd = false;
        boolean idEnd = false;

        try {

            readReplay = new DataInputStream(player.getInputStream());

            receivedMsg = readReplay.readLine();

            if (receivedMsg.equals("empty")) {

                System.out.print("None");
                receivedReplays.add("No Replays to show");
                list = FXCollections.observableArrayList(receivedReplays);
                listView = new ListView<String>(list);
            } else {
                while (true) {

                    if (receivedMsg.equals("End")) {
                        break;
                    }
                    if (!receivedMsg.equals("ids") && !receivedMsg.equals("End") && !replayEnd) {

                        receivedReplays.add(receivedMsg);

                        list = FXCollections.observableArrayList(receivedReplays);


                    }
                    if (receivedMsg.equals("ids")) {
                        replayEnd = true;
                        receivedMsg = readReplay.readLine();
                    }
                    if (replayEnd && !receivedMsg.equals("End")) {

                        listOfIds.add(Integer.parseInt(receivedMsg));
                    }
                    receivedMsg = readReplay.readLine();

                }

                listView.setItems(list);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int selectedReplayIndex = 0;
                String selectedReplayId = "";
                StringTokenizer s;
                DataInputStream readReplay;
                String trimmedString = "";
                String chosenReplay;
                String receivedMesg = "";
                chosenReplay = listView.getSelectionModel().getSelectedItem().toString();
                selectedReplayIndex = listView.getSelectionModel().getSelectedIndex();

                selectedReplayId = Integer.toString(listOfIds.get(selectedReplayIndex));

                connectServer.selectReplay(chosenReplay, selectedReplayId);

                try {

                    int it = 0;
                    readReplay = new DataInputStream(player.getInputStream());
                    receivedMesg = readReplay.readLine();
                    System.out.println(receivedMesg);
                    trimmedString = receivedMesg.replaceAll("[^0-9?!\\.,]", "");
                    s = new StringTokenizer(trimmedString, ",");
                    tiles = new int[s.countTokens()];
                    while (s.hasMoreTokens()) {
                        tiles[it] = Integer.parseInt(s.nextToken());

                        it++;
                    }

                    ReplayBoard.showReplay(tiles);

                    MangePaneShow.viewReplayBoard();

                } catch (IOException ev) {
                    ev.printStackTrace();
                }

            }

        });

    }

}
