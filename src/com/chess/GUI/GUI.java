
package com.chess.GUI;


import com.chess.engine.board.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUI extends Application {
    
    @Override
    public void start(Stage stage) {
      BoardPane pane=new BoardPane();
       Scene scene=new Scene(pane);
        stage.setTitle("lol Chess");
        stage.setMinHeight(680);
        stage.setMinWidth(660);
        stage.setScene(scene);
        stage.show();
    }
     public static void main(String[] args) {
      
        launch(args);
    }
    
}
