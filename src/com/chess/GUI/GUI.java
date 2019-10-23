
package com.chess.GUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
public class GUI extends Application {
    Stage s1;
    @Override
    public void start(Stage stage) {
        s1=stage;
      BoardPane pane=new BoardPane();
       Scene scene=new Scene(pane);
       Image s_ic=null;
      
        try {
              s_ic =new Image(new FileInputStream("art\\game_ic1.jpg"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setTitle("league of chess");
        stage.setMinHeight(680);
        stage.setMinWidth(660);
        stage.setMaxHeight(680);
        stage.setMaxWidth(660);
        stage.setScene(scene);
        stage.getIcons().add(s_ic);
        BG_Music();
        stage.show();
    }
     public static void BG_Music(){
         String id="audio\\awaken.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
                if (!mediaPlayer.isPlaying()){
                mediaPlayer.play();
                }
    }
     
    public static void main(String[] args) {
        launch(args);
    }
    
}
