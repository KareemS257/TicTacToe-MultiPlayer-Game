package guiforgame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MainClass extends Application {
    static Stage window;
    static Scene scene1;
    static Scene scene2;
    static Scene scene3;
    static Scene scene4;
    static MediaPlayer mediaPlayer;
    static MediaPlayer mediaPlayer1;
    static MediaPlayer mediaPlayer2;
    static  Pane root;
    @Override
    public void start(Stage primaryStage) {
  
        window = primaryStage;
        
        root = new Pane();
        root.getChildren().add(MangePaneShow.start);
        root.getChildren().add(MangePaneShow.singleplayer);
        root.getChildren().add(MangePaneShow.gameBoard);
        root.getChildren().add(MangePaneShow.gameBoard2);
        root.getChildren().add(MangePaneShow.signInUp);
        root.getChildren().add(MangePaneShow.list);
        root.getChildren().add(MangePaneShow.replayBoard);
        
        root.setId("background");
        MangePaneShow.signPage();
        scene1 = new Scene(root, 900,600 );
        String style = getClass().getResource("buttonsCss.css").toExternalForm();
        scene1.getStylesheets().addAll(style);
        
        java.net.URL resource = getClass().getResource("won.mp4");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(600);
        mediaView.setFitWidth(900);
        scene2 = new Scene(new Pane(mediaView), 900, 600);

    
        Image image = new Image(getClass().getResource("giphy.gif").toExternalForm());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(600); 
        imageView.setFitWidth(900); 
        Pane root1 = new Pane(imageView);
        scene3 = new Scene(root1, 900, 600);
   
        
        Image image1 = new Image(getClass().getResource("nowon.gif").toExternalForm());
        ImageView imageView1 = new ImageView();
        imageView1.setImage(image1);
        imageView1.setFitHeight(600); 
        imageView1.setFitWidth(900); 
        Pane root2 = new Pane(imageView1);
        scene4 = new Scene(root2, 900, 600);
//        scene4 = new Scene(new Pane(mediaView), 400, 600);
        
        primaryStage.setTitle("Tic Tac Teo");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LoseGame();
        launch(args);
        
    }
    
}
