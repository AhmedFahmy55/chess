
package com.chess.GUI;
import java.awt.Label;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class End extends Application {
    Stage s2;
     String winner;
      Image i=null;
    @Override
    public void start(Stage stage) {
        s2=stage;
        try {
             i=new Image(new FileInputStream("art\\setting.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(End.class.getName()).log(Level.SEVERE, null, ex);
        }
        Button b1 = new Button("Exit");
        Button  b2 = new Button("New Game");
        b1.setMaxSize(80, 80);
          b2.setMaxSize(80, 80);
        b1.setMinSize (80, 80);
        b2.setMinSize(80, 80);
        Text t=new Text(50,50,winner+"Win");
        HBox pane=new HBox(20);
        pane.getChildren().add(t);
         b1.setOnAction(e ->{
         System.exit(0);
         });
         b2.setOnAction(e1 ->{
         GUI gui=new GUI();
         gui.start(new Stage());
           s2.close();
           
         });
        pane.getChildren().addAll(b1,b2);
        pane.setAlignment(Pos.CENTER);
        Scene scene=new Scene(pane,300,200);
        stage.getIcons().add(i);
        stage.setScene(scene);
        stage.setTitle(winner+" Win");
       
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.show();
    }
}
