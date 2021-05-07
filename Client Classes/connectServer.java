package guiforgame;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;

public class connectServer {

    static Socket mySocket;
    static DataInputStream readDataFromServer;
    static PrintStream sendDataToServer;
    static String replyMsg;

    public static void connect() {
        try {
            mySocket = new Socket(InetAddress.getLocalHost(), 5005);
            readDataFromServer = new DataInputStream(mySocket.getInputStream());
            sendDataToServer = new PrintStream(mySocket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void startTwoPlayersGame() {
        sendDataToServer.println("startMultiPlaying");
        new startThread();
    }

    public static void endConnection() {
        try {
            mySocket.close();
        } catch (IOException ex) {
            Logger.getLogger(connectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getHigestScore() {
        sendDataToServer.println("getHigestScore");
    }

    public static void StartSignIn() {
        sendDataToServer.println("StartSignIn");
        sendDataToServer.println(SinglePlayerPage.playerName);
    }

    public static void StartSignUp() {
        sendDataToServer.println("StartSignUp");
        sendDataToServer.println(SinglePlayerPage.playerName);
    }

    public static void sendButtonIndex(String str) {
        sendDataToServer.println(str);
    }

    //Added code here
    public static void saveLoggedName() {
        sendDataToServer.println("Name");
        sendDataToServer.println(SinglePlayerPage.playerName);
    }

    public static void saveReplay(Vector <Integer> recordedMoves) {
        sendDataToServer.println("Record");
        for (int i = 0; i < recordedMoves.size(); i++) {
            sendDataToServer.println(recordedMoves.get(i).toString());
        }
        sendDataToServer.println("End");
    }

    public static void showReplays() {
        sendDataToServer.println("Replay");
        ReplayList.receiveReplays(mySocket);

    }
public static void selectReplay(String date,String selectedId){
    sendDataToServer.println("Select");
    sendDataToServer.println(date);
    sendDataToServer.println(selectedId);
}
}

class startThread extends Thread {

    public startThread() {
        start();
    }

    @Override
    public void run() {
        try {
            connectServer.replyMsg = connectServer.readDataFromServer.readLine();
            System.out.println(connectServer.replyMsg);
            if (connectServer.replyMsg.equals("one")) {
                GameBoard2.xoFlag = true; // This is player1 X  
                GameBoard2.turn = 1;      // turn for player1
                if(GameBoard2.recordStatus)
                   connectServer.sendDataToServer.println("record");
                else
                   connectServer.sendDataToServer.println("norecord");
                connectServer.sendDataToServer.println(SinglePlayerPage.playerName);
                    
            } else {
                GameBoard2.xoFlag = false; // This is player2 O
                GameBoard2.turn = 0;
                 if(GameBoard2.recordStatus)
                   connectServer.sendDataToServer.println("record");
                else
                   connectServer.sendDataToServer.println("norecord");
                connectServer.sendDataToServer.println(SinglePlayerPage.playerName);
            }
            //connectServer.sendDataToServer.println("2");     
        } catch (SocketException e) {
            //break;
        } catch (IOException ex) {
            Logger.getLogger(GameBoard2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(GameBoard2.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                if (GameBoard2.turn == 1 && GameBoard2.xoFlag) //PlayerX Turn
                {
                    connectServer.replyMsg = connectServer.readDataFromServer.readLine();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (connectServer.replyMsg) {
                                case "lose": {
                                    GameBoard2.showAlertLose();
                                    connectServer.sendDataToServer.println(SinglePlayerPage.playerName);
                                    break;
                                }
                                case "nowon":
                                    GameBoard2.showAlertNoWon();
                                    break;
                                case "9":
                                    GameBoard2.DrawX(GameBoard2.btn9);
                                    break;
                                case "8":
                                    GameBoard2.DrawX(GameBoard2.btn8);
                                    break;
                                case "7":
                                    GameBoard2.DrawX(GameBoard2.btn7);
                                    break;
                                case "6":
                                    GameBoard2.DrawX(GameBoard2.btn6);
                                    break;
                                case "5":
                                    GameBoard2.DrawX(GameBoard2.btn5);
                                    break;
                                case "4":
                                    GameBoard2.DrawX(GameBoard2.btn4);
                                    break;
                                case "3":
                                    GameBoard2.DrawX(GameBoard2.btn3);
                                    break;
                                case "2":
                                    GameBoard2.DrawX(GameBoard2.btn2);
                                    break;
                                case "1":
                                    GameBoard2.DrawX(GameBoard2.btn1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    GameBoard2.turn = 0;
                } else if (GameBoard2.turn == 1 && !GameBoard2.xoFlag) // //PlayerO Turn
                {
                    connectServer.replyMsg = connectServer.readDataFromServer.readLine();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (connectServer.replyMsg) {
                                case "lose": {
                                    GameBoard2.showAlertLose();
                                    connectServer.sendDataToServer.println(SinglePlayerPage.playerName);
                                    break;
                                }
                                case "nowon":
                                    GameBoard2.showAlertNoWon();
                                    break;
                                case "9":
                                    GameBoard2.DrawO(GameBoard2.btn9);
                                    break;
                                case "8":
                                    GameBoard2.DrawO(GameBoard2.btn8);
                                    break;
                                case "7":
                                    GameBoard2.DrawO(GameBoard2.btn7);
                                    break;
                                case "6":
                                    GameBoard2.DrawO(GameBoard2.btn6);
                                    break;
                                case "5":
                                    GameBoard2.DrawO(GameBoard2.btn5);
                                    break;
                                case "4":
                                    GameBoard2.DrawO(GameBoard2.btn4);
                                    break;
                                case "3":
                                    GameBoard2.DrawO(GameBoard2.btn3);
                                    break;
                                case "2":
                                    GameBoard2.DrawO(GameBoard2.btn2);
                                    break;
                                case "1":
                                    GameBoard2.DrawO(GameBoard2.btn1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    GameBoard2.turn = 0;
                }

                if (GameBoard2.turn == 0 && !GameBoard2.xoFlag) //PlayerO //Turn PlayerX
                {
                    connectServer.replyMsg = connectServer.readDataFromServer.readLine();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (connectServer.replyMsg) {
                                case "lose": {
                                    GameBoard2.showAlertLose();
                                    connectServer.sendDataToServer.println(SinglePlayerPage.playerName);
                                    break;
                                }
                                case "nowon":
                                    GameBoard2.showAlertNoWon();
                                    break;
                                case "9":
                                    GameBoard2.DrawX(GameBoard2.btn9);
                                    break;
                                case "8":
                                    GameBoard2.DrawX(GameBoard2.btn8);
                                    break;
                                case "7":
                                    GameBoard2.DrawX(GameBoard2.btn7);
                                    break;
                                case "6":
                                    GameBoard2.DrawX(GameBoard2.btn6);
                                    break;
                                case "5":
                                    GameBoard2.DrawX(GameBoard2.btn5);
                                    break;
                                case "4":
                                    GameBoard2.DrawX(GameBoard2.btn4);
                                    break;
                                case "3":
                                    GameBoard2.DrawX(GameBoard2.btn3);
                                    break;
                                case "2":
                                    GameBoard2.DrawX(GameBoard2.btn2);
                                    break;
                                case "1":
                                    GameBoard2.DrawX(GameBoard2.btn1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    GameBoard2.turn = 1;
                } else if (GameBoard2.turn == 0 && GameBoard2.xoFlag) //PlayerX //Turn PlayerO
                {
                    connectServer.replyMsg = connectServer.readDataFromServer.readLine();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            switch (connectServer.replyMsg) {
                                case "lose": {
                                    GameBoard2.showAlertLose();
                                    connectServer.sendDataToServer.println(SinglePlayerPage.playerName);
                                    break;
                                }
                                case "nowon":
                                    GameBoard2.showAlertNoWon();
                                    break;
                                case "9":
                                    GameBoard2.DrawO(GameBoard2.btn9);
                                    break;
                                case "8":
                                    GameBoard2.DrawO(GameBoard2.btn8);
                                    break;
                                case "7":
                                    GameBoard2.DrawO(GameBoard2.btn7);
                                    break;
                                case "6":
                                    GameBoard2.DrawO(GameBoard2.btn6);
                                    break;
                                case "5":
                                    GameBoard2.DrawO(GameBoard2.btn5);
                                    break;
                                case "4":
                                    GameBoard2.DrawO(GameBoard2.btn4);
                                    break;
                                case "3":
                                    GameBoard2.DrawO(GameBoard2.btn3);
                                    break;
                                case "2":
                                    GameBoard2.DrawO(GameBoard2.btn2);
                                    break;
                                case "1":
                                    GameBoard2.DrawO(GameBoard2.btn1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    GameBoard2.turn = 1;
                }
            } catch (SocketException e) {
                break;
            } catch (IOException ex) {
                Logger.getLogger(GameBoard2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                Logger.getLogger(GameBoard2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException e) {

            } catch (Exception e) {

            }
        }
    }

}
