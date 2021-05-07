package gameserverswing;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer {

    ServerSocket myServer;
    volatile static Vector<Socket> vectorOfPlayers = new Vector<Socket>();
    volatile static Vector<String> loggedPlayers = new Vector<String>();
    volatile static int sessionsNumber = 0;
    volatile static int signedInPlayers = 0;
    DataInputStream readDataFromPlayer;
    static String Msg = "";
    
    volatile static int totalRecords = 0;
    GameServer() {
        System.out.println("Server Started at:");
        System.out.println(new Date());
        totalRecords = DataBaseManagment.getTotalrecords();
        try {
            myServer = new ServerSocket(5005);

            while (true) {
                //sessionsNumber++;
                System.out.println("wailing players.........................");
                Socket player = myServer.accept();
                new playerInput(player);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new GameServer();
    }
}

class playerInput extends Thread {

    Socket player;
    DataInputStream readDataFromPlayer;
    PrintStream sendDataToPlayer;
    String message;
    static String [] listOfIds;
    boolean checkExistName;
    String currentPlayerName="";
    public playerInput(Socket player) {
        try {
            readDataFromPlayer = new DataInputStream(player.getInputStream());
            sendDataToPlayer = new PrintStream(player.getOutputStream());

            this.player = player;

            start();

        } catch (IOException ex) {
            Logger.getLogger(playerInput.class.getName()).log(Level.SEVERE, null, ex);
        }
System.out.print(GameServer.signedInPlayers);
    }

    @Override
    public void run() {
        while (true) {
            try {
                try {
                    message = readDataFromPlayer.readLine();
                } catch (SocketException ex) {
                    System.out.println("player one log out");
                    break;
                }
                if (message.equals("startMultiPlaying")) {
                    perpareSession(player);
                    stop();
                }
                if (message.equals("getHigestScore")) {
                    String max = DataBaseManagment.maxscore();
                    sendDataToPlayer.println(max);
                }
                if (message.equals("StartSignIn")) {
                    message = readDataFromPlayer.readLine();
                    System.out.println(message);
                    checkExistName = DataBaseManagment.signin(message);
                    System.out.println(checkExistName);
                    if (checkExistName) {
                        sendDataToPlayer.println("right");
                    } else {
                        sendDataToPlayer.println("wrong");
                    }
                }
                if (message.equals("StartSignUp")) {
                    message = readDataFromPlayer.readLine();
                    System.out.println(message);
                    checkExistName = DataBaseManagment.signup(message);
                    System.out.println(checkExistName);
                    if (checkExistName) {
                        sendDataToPlayer.println("right");
                    } else {
                        sendDataToPlayer.println("wrong");
                    }
                }
                //Added Code here
                if (message.equals("Name")) {
                    message = readDataFromPlayer.readLine();
                    currentPlayerName=message;
                }

                if (message.equals("Record")) {

                    recordGame(player, currentPlayerName);
                }
                if (message.equals("Replay")) {
                    replayFetch(player,currentPlayerName);
                }
                if(message.equals("Select")){
                    replaySelect(player);
                }

            } catch (IOException ex) {
                Logger.getLogger(playerInput.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                //ex.printStackTrace();
            }
        }

    }
//Added Code here

    public void recordGame(Socket cs, String playerName) {
        Vector<Integer> replay = new Vector<Integer>();
        DataInputStream readDataFromPlayer;
        int[] replayArr;
        String receivedMessage = "";
        boolean connectionUp = true;

        while (true) {

            try {
                readDataFromPlayer = new DataInputStream(player.getInputStream());
                receivedMessage = readDataFromPlayer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                connectionUp = false;
            }
            if (receivedMessage.equals("End") == false) {

                replay.add(Integer.parseInt(receivedMessage));
            } else {
                replayArr = new int[replay.size()];
                for (int i = 0; i < replay.size(); i++) {
                    replayArr[i] = replay.get(i);
                }
                DataBaseManagment.saveReplay(replayArr, playerName);
                break;
            }

            if (!connectionUp) {
                break;
            }

        }

    }

    public void replayFetch(Socket s, String pName) {
        PrintStream sendReplays;
        String message = "";

        Vector<String> fetchedReplays = new Vector<String>();

        try {
            sendReplays = new PrintStream(s.getOutputStream());

            fetchedReplays = DataBaseManagment.fetchReplays(pName);
            
            for (String replay : fetchedReplays) {

                sendReplays.println(replay);

            }
            if(listOfIds.length >0){
                
            sendReplays.println("ids");
            for (String id : listOfIds) {
                System.out.print(id);
                sendReplays.println(id);

            }
            }
            sendReplays.println("End");
            fetchedReplays.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
   public void replaySelect(Socket s) {
        DataInputStream readFromPlayer;
        PrintStream sendToPlayer;
        String date;
        String id;
        Vector<String> selectedReplay = new Vector<String>();
        try {
            readFromPlayer = new DataInputStream(s.getInputStream());
            sendToPlayer = new PrintStream(s.getOutputStream());
            date = readFromPlayer.readLine();
            id=readFromPlayer.readLine();
            //here
            selectedReplay = DataBaseManagment.selectReplays(date,id);
            
            
            for (String replay : selectedReplay) {

                sendToPlayer.println(replay);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void perpareSession(Socket cs) {
        if (GameServer.vectorOfPlayers.size() == 1) {
            GameServer.vectorOfPlayers.add(cs);
            System.out.println("PlayerO connected...");
            GameServer.sessionsNumber++;
            new PlayersHandler(GameServer.vectorOfPlayers.get(0), GameServer.vectorOfPlayers.get(1), GameServer.sessionsNumber);
            System.out.println("Session no " + GameServer.sessionsNumber + " started........................");
            GameServer.vectorOfPlayers.clear();

        } else {
            System.out.println("PlayerX connected...");
            System.out.println("waiting player two.......");
            GameServer.vectorOfPlayers.add(cs);
        }
    }

}

class PlayersHandler extends Thread {

    DataInputStream readDataFromPlayer1;
    DataInputStream readDataFromPlayer2;
    //BufferedReader readDataFromPlayer1;
    // BufferedReader readDataFromPlayer2;
    PrintStream sendDataToPlayer1;
    PrintStream sendDataToPlayer2;
    Vector<String> vectorOfRecords = new Vector<String>();
    int sessionNumber = 0;
    String message1;
    String message2;
    String checkBuffer = null;
    boolean orderFlag = true;
    boolean loseFlag1 = false;
    boolean loseFlag2 = false;
    boolean connectionFlag1 = true;
    boolean connectionFlag2 = true;

    public PlayersHandler(Socket player1, Socket player2, int sessionsNumber) {
        try {
            readDataFromPlayer1 = new DataInputStream(player1.getInputStream());
            // BufferedReader readDataFromPlayer1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            readDataFromPlayer2 = new DataInputStream(player2.getInputStream());
            //BufferedReader readDataFromPlayer2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            sendDataToPlayer1 = new PrintStream(player1.getOutputStream());
            sendDataToPlayer2 = new PrintStream(player2.getOutputStream());
            sessionNumber = sessionsNumber;
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            //ex.printStackTrace();
        }

    }

    public void run() {
        sendDataToPlayer1.println("one"); //Tell Player1 "you are player 1"
        sendDataToPlayer2.println("two"); //Tell Player2 "you are player 2"
        while (true) {
            try {
                readPlayer1();
                readPlayer2();
                if (!connectionFlag1 || !connectionFlag2) {
                    System.out.println("Session no " + sessionNumber + " endeded.................");
                    break;
                }
            } catch (NullPointerException ex) {

            }
        }
    }

    void sendMessageToAll(String str) {
        try {
            vectorOfRecords.add(str);
            sendDataToPlayer1.println(str);
            sendDataToPlayer2.println(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void readPlayer1() {
        try {
            if (loseFlag1) {
                message1 = readDataFromPlayer1.readLine();
                DataBaseManagment.increaseLoses(message1);
            } else {
                message1 = readDataFromPlayer1.readLine();
                sendMessageToAll(message1);
                message1 = readDataFromPlayer1.readLine();
                if (message1.equals("won")) {
                    message1 = readDataFromPlayer1.readLine();
                    DataBaseManagment.increaseScore(message1);
                    sendDataToPlayer2.println("lose");
                    loseFlag2 = true;
                } else if (message1.equals("fair")) {
                    sendDataToPlayer2.println("nowon");
                }
            }
        } catch (SocketException ex) {
            //client.stopConnection();
            for (int i = 0; i < vectorOfRecords.size(); i++) {
                System.out.println(vectorOfRecords.get(i));
            }
            System.out.println("player one log out");
            connectionFlag1 = false;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            //ex.printStackTrace();
        }
    }

    void readPlayer2() {
        try {
            if (loseFlag2) {
                message2 = readDataFromPlayer2.readLine();
                DataBaseManagment.increaseLoses(message2);
            } else {
                message2 = readDataFromPlayer2.readLine();
                sendMessageToAll(message2);
                message2 = readDataFromPlayer2.readLine();
                if (message2.equals("won")) {
                    message2 = readDataFromPlayer2.readLine();
                    DataBaseManagment.increaseScore(message2);
                    sendDataToPlayer1.println("lose");
                    loseFlag1 = true;
                } else if (message2.equals("fair")) {
                    sendDataToPlayer1.println("nowon");
                }
            }
        } catch (SocketException ex) {
            System.out.println("player two log out");
            connectionFlag2 = false;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
