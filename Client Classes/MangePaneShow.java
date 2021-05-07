package guiforgame;

public class MangePaneShow {

    static StartPage start = new StartPage();
    static SinglePlayerPage singleplayer = new SinglePlayerPage();
    static GameBoard gameBoard = new GameBoard();
    static GameBoard2 gameBoard2 = new GameBoard2();
    //static WinnerAlert winnerAlert = new WinnerAlert();
    static signPage signInUp = new signPage();
    static ReplayList list = new ReplayList();
    static ReplayBoard replayBoard = new ReplayBoard();

    public static void signPage() {
        start.setVisible(false);
        singleplayer.setVisible(false);
        gameBoard.setVisible(false);
        gameBoard2.setVisible(false);
        //winnerAlert.setVisible(false);
        signInUp.setVisible(true);
        list.setVisible(false);
        replayBoard.setVisible(false);
    }
//    public static void viewWinnerAlert() {
//        start.setVisible(false);
//        singleplayer.setVisible(false);
//        gameBoard.setVisible(false);
//        gameBoard2.setVisible(true);
//        winnerAlert.setVisible(true);
//        signInUp.setVisible(false);
//    }

    public static void viewStartPane() {
        start.setVisible(true);
        singleplayer.setVisible(false);
        gameBoard.setVisible(false);
        gameBoard2.setVisible(false);
        // winnerAlert.setVisible(false);
        signInUp.setVisible(false);
        list.setVisible(false);
        replayBoard.setVisible(false);
    }

    public static void viewSinglePlayerPane() {
        start.setVisible(false);
        singleplayer.setVisible(true);
        gameBoard.setVisible(false);
        gameBoard2.setVisible(false);
        //winnerAlert.setVisible(false);
        signInUp.setVisible(false);
        list.setVisible(false);
        replayBoard.setVisible(false);
    }

    public static void viewMultiPlayerPane() {
        start.setVisible(false);
        singleplayer.setVisible(false);
        gameBoard.setVisible(false);
        gameBoard2.setVisible(false);
        //winnerAlert.setVisible(false);
        signInUp.setVisible(false);
        list.setVisible(false);
        replayBoard.setVisible(false);
    }

    public static void viewGameBoardPane() {
        gameBoard.setVisible(true);
        start.setVisible(false);
        singleplayer.setVisible(false);
        gameBoard2.setVisible(false);
        //winnerAlert.setVisible(false);
        signInUp.setVisible(false);
        list.setVisible(false);
        replayBoard.setVisible(false);
    }

    public static void viewGameBoard2Pane() {
        start.setVisible(false);
        singleplayer.setVisible(false);
        gameBoard.setVisible(false);
        gameBoard2.setVisible(true);
        //winnerAlert.setVisible(false);
        signInUp.setVisible(false);
        list.setVisible(false);
        replayBoard.setVisible(false);
    }

    public static void viewReplays() {
        list.setVisible(true);
        start.setVisible(false);
        singleplayer.setVisible(false);
        gameBoard2.setVisible(false);
        gameBoard.setVisible(false);
        // gameBoard2.setVisible(true);
        // winnerAlert.setVisible(false);
replayBoard.setVisible(false);
    }

    public static void viewReplayBoard() {
        list.setVisible(false);
        start.setVisible(false);
        singleplayer.setVisible(false);
        gameBoard2.setVisible(false);
        gameBoard.setVisible(false);
        // gameBoard2.setVisible(true);
        // winnerAlert.setVisible(false);
        replayBoard.setVisible(true);
    }

}
