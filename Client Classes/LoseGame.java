
package guiforgame;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoseGame extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try{
        Text t = new Text();
        t.setText("Good Luck");
        t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        t.setFill(Color.BLACK);
        t.setX(120); 
        t.setY(350);
        
        java.net.URL resource = getClass().getResource("uhoh.mp3");
        AudioClip sound = new AudioClip(resource.toString());
        Image image = new Image(getClass().getResource("sad.jpg").toExternalForm());
        HBox hbox = new HBox(t); 
        
        final Duration SEC_2 = Duration.millis(2000);
        final Duration SEC_3 = Duration.millis(3000);
        
        FadeTransition ft = new FadeTransition(SEC_3);
        ft.setFromValue(1.0f);
        ft.setToValue(0.3f);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        
        ScaleTransition st = new ScaleTransition(SEC_2);
        st.setByX(1.5f);
        st.setByY(1.5f);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        
        ParallelTransition pt = new ParallelTransition(t,ft,st);
        pt.play();
        sound.play();  
        hbox.setPadding(new Insets(250, 0, 0, 120));
        Scene scene = new Scene(hbox, 350, 350);
        BackgroundImage backgroundimage = new BackgroundImage(image,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundPosition.DEFAULT,  
                                             new BackgroundSize(1.0, 1.0, true, true, false, false)); 
        Background background = new Background(backgroundimage); 
            hbox.setBackground(background);
        
        primaryStage.setTitle("Result");
        primaryStage.setScene(scene);
        primaryStage.show();
         }
    catch (Exception e) { 
  
            System.out.println(e.getMessage()); 
        } 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
